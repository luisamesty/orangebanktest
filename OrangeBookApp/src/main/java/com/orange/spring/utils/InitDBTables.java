package com.orange.spring.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orange.spring.model.Account;
import com.orange.spring.service.AccountServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class InitDBTables {
	
    public static Session session = null;
    
    public static Transaction transaction = null;
    public static ArrayList<Account> accounts = new ArrayList(); 

    @Autowired
	public static AccountServiceImpl accountService;
	@PersistenceContext
	static EntityManager em;
	
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
    	// Init DB Tables
    	initDBTables();
    	
    	// Accounts File
		File jsonFile = new File("json"+File.separatorChar+"Account.json");
		System.out.println(jsonFile.getAbsolutePath());
		readAccountFile(jsonFile);
		// Display Array to Console
		displayAccountArray();
		// Saves Account Array
		saveAccountArray();

	}
	
    /**
     * readAccountFile
     * Reads Json Account File 
     * @param accountJsonFile
     */
    private static void readAccountFile(File accountJsonFile) {
    

		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		// SHOW RECORDS
    	try (FileReader reader = new FileReader(accountJsonFile))
    	{
			//Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray trList = (JSONArray) obj;
            System.out.println(trList);

            //Iterate over account array
            
            trList.forEach( acct -> parseAccountObject( (JSONObject) acct ) );
            
    	} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    	
    }
    
    /**
     * parseAccountObject
     * Parse Json Objects and convert to public Arrray accounts
     * @param acct
     */
	private static void parseAccountObject(JSONObject acct) 
	{
		
		//Get transaction object within list
		JSONObject trObject = (JSONObject) acct.get("account");

		//Get id 
		Long id = (Long) trObject.get("id");	
		System.out.println("id="+id);
		//Get name 
		String name = (String) trObject.get("name");	
		System.out.println("name="+name);
		//Get account_iban 
		String account_iban = (String) trObject.get("account_iban");	
		System.out.println("account_iban="+account_iban);
		//Get balance 
		double balance = (double) trObject.get("balance");	
		BigDecimal BDbalance = new BigDecimal(balance);
		BDbalance = BDbalance.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("balance="+BDbalance);

		// Account 
		@SuppressWarnings("null")
		Account account = new Account();
		account.setId(id);
		account.setAccount_iban(account_iban);
		account.setBalance(BDbalance);
		account.setName(name);
		// ADD TO ARRAY
		accounts.add(account);
	}

	/**
	 * displayAccountArray
	 * Shows Account Array
	 */
	private static void displayAccountArray() {
    	// CHECK ARRAY
    	System.out.println("Arreglo de cuentas .....");
    	for (int i=0 ; i < accounts.size(); i++) {
    		Account account = accounts.get(i);
    		System.out.println(accounts.toString());
    	}
	}

	/**
	 * saveAccountArray2
	 * Saves to DB Account Array (FUTURE INCLUDE VALIDATIONS by IBAN AND ID)
	 */
	private static void saveAccountArray2() {
		
		Account account= null;
		Account account2 = null;
		boolean isError = false;
		String account_iban = "";
		String account_iban2 = "";
		long account_id = 0;
		List<Account> accts = null;
		// HIBERNATE 
        try {
        	// UtilConfig - Hibernate Conection 
        	UtilConfig uconf = new UtilConfig();
        	Properties props = uconf.getProperties();
        	session = uconf.getSessionFactory().openSession();
        	// RECORDS Account Array
        	System.out.println("GRABANDO ARREGLO DE CUENTAS .....");
        	for (int i=0 ; i < accounts.size(); i++) {
        		// Get i Account
        		account = accounts.get(i);
                // Verify IF exists 
                isError = false;
  			  	// Verify if ID exists
            	System.out.println(account.toString());
        		transaction = session.beginTransaction();
        		// Account Verify account id
                if (account.getAccount_iban() != null || !account.getAccount_iban().isEmpty()) {
                	account_iban  = account.getAccount_iban();
                	System.out.println("account_iban="+account_iban);
            		CriteriaBuilder cb = session.getCriteriaBuilder();
            		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
          	      	Root<Account> root = cq.from(Account.class);
          	      	cq.select(root);
          	      	//  OJO WHERE Clause
          	      	cq.where(em.getCriteriaBuilder().equal(root.get("account_iban"), account_iban ));
          	      	//  OJO WHERE Clause
          	      	Query<Account> query = session.createQuery(cq);
          	      	accts = query.getResultList();
          	      	if (accts.size() > 0)
          	      		isError = true ;
                }
        		// Account Verify account_iban
                if (account.getAccount_iban() != null || !account.getAccount_iban().isEmpty()) {
                	account_id  = account.getId();
            		CriteriaBuilder cb = session.getCriteriaBuilder();
            		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
          	      	Root<Account> root = cq.from(Account.class);
          	      	cq.select(root);
          	      	//  OJO WHERE Clause
          	      	cq.where(em.getCriteriaBuilder().equal(root.get("id"), account_id ));
          	      	//  OJO WHERE Clause
          	      	Query<Account> query = session.createQuery(cq);
          	      	accts = query.getResultList();
          	      	if (accts.size() > 0)
          	      		isError = true ;
                }
                if (!isError) {
	        		//transaction.begin();
	                session.save(account);
	            	transaction.commit();
                }
        	}
        	//uconf.shutdown();
			// Account 
        } catch (Exception e) {
            if (transaction != null) {
              transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
              session.close();
            }
        }
		
	}
	/**
	 * saveAccountArray
	 * Saves to DB Account Array
	 */
	private static void saveAccountArray() {
		
    	// HIBERNATE 
        try {
        	// UtilConfig - Hibernate Conection 
        	UtilConfig uconf = new UtilConfig();
        	Properties props = uconf.getProperties();
        	session = uconf.getSessionFactory().openSession();
            // RECORDS Account Array
        	System.out.println("GRABANDO ARREGLO DE CUENTAS .....");
        	for (int i=0 ; i < accounts.size(); i++) {
        		transaction = session.beginTransaction();
        		//transaction.begin();
        		// Get i Account
        		Account account = accounts.get(i);
                System.out.println(account.toString());
                // Save account Future Try Catch
                //try {
                	session.save(account);
                	transaction.commit();
                //} catch( javax.persistence.PersistenceException  ex) {
                //	if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
                //		System.out.println("** DUPLICATE ** "+account.toString());
                //}
        	}
        	//uconf.shutdown();
			// Account 
        } catch (Exception e) {
            if (transaction != null) {
              transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
              session.close();
            }
        }

	}

	
	private static void  initDBTables() {
		
		
    	// HIBERNATE 
        try {
        	// UtilConfig - Hibernate Conection 
        	UtilConfig uconf = new UtilConfig();
        	Properties props = uconf.getProperties();
        	//System.out.println( "postgresql.driver:"+props.getProperty("postgresql.driver"));
        	session = uconf.getSessionFactory().openSession();
        	transaction = session.beginTransaction();
        	
        	// FUTURE INIT IMPROVEMETS
        	// DELETE RECORD FROM accounttransaction table 
        	//Query<?> query1 = session.createQuery("DELETE FROM AccountTransaction");
            //query1.executeUpdate();
            //session.flush();
            
        	// DELETE RECORD FROM account table 
        	Query<?> query2 = session.createQuery("DELETE FROM Account");
            query2.executeUpdate();
            session.flush();
            
            // FUTURE INIT IMPROVEMETS
            // UPDATE account_id_seq SEQUENCE to 1
        	//Query<?> query11 = session.createQuery("SELECT setval('account_id_seq', 1, true)");
            //query11.executeUpdate();
            //session.flush();
            // UPDATE account_id_seq SEQUENCE to 1
        	//Query<?> query21 = session.createQuery("SELECT setval('account_id_seq', 1, true)");
            //query21.executeUpdate();
            //session.flush();
            
            // transaction.commit();
            //uconf.shutdown();

        } catch (Exception e) {
            if (transaction != null) {
              transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
              session.close();
            }
        }
		
	}
	
}
