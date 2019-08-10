package com.orange.spring.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.NonUniqueResultException;
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
import com.orange.spring.utils.HibernateUtil;
@CrossOrigin(origins = "*")
@RestController
public class InitDBTables {
	
    public static Session initDBSession =  HibernateUtil.getSessionFactory().openSession();
    
    public static Transaction transaction = null;
    public static ArrayList<Account> accounts = new ArrayList<Account>(); 

    @Autowired
	public static AccountServiceImpl accountService;
	@PersistenceContext
	static EntityManager em;
	
    public static void main(String[] args) {
		
    	
    	// Initialize DB Tables
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
		long longid = (long) trObject.get("id");
		int id = (int) longid;
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
		//Get init balance (SAME BALANCE ON JSON FILE)
		double initbalance = (double) trObject.get("balance");	
		BigDecimal BDinitbalance = new BigDecimal(initbalance);
		BDbalance = BDinitbalance.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("initbalance="+BDinitbalance);
		// Account 
		@SuppressWarnings("null")
		Account account = new Account();
		account.setId(id);
		account.setAccount_iban(account_iban);
		account.setBalance(BDbalance);
		account.setInitbalance(BDinitbalance);
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
    		System.out.println(account.toString());
    	}
	}

	/**
	 * saveAccountArray2
	 * Saves to DB Account Array (FUTURE INCLUDE VALIDATIONS by IBAN AND ID)
	 */
	private static void saveAccountArray() {
		
		Account account= null;
		int id = 0;

    	// RECORDS Account Array
    	System.out.println("GRABANDO ARREGLO DE CUENTAS .....");
    	for (int i=0 ; i < accounts.size(); i++) {
    		// Get i Account
    		account = accounts.get(i);
    		id = account.getId();
        	boolean isFound = false;
        	isFound = getByIBAN(account.getAccount_iban());
            // Verify IF exists same account_iban
      		if (isFound) {
      			// Create a New Account
      			updateAccount(id, account);
      		} else {
      			// Create a New Account
        		saveAccount(account);		
      		}

    	}

	}
	
	/**
	 * updateAccount Update Name, IBAN and Initial Balance
	 * @param id
	 * @param account
	 */
	private static void updateAccount(int id, Account account) {
		
  	  boolean isError = false;
	      try {
	    	Session locSession = HibernateUtil.getSessionFactory().openSession();
	    	transaction = locSession.beginTransaction();	    	
	  		Account account2 = locSession.byId(Account.class).load(id);
	  		account2.setAccount_iban(account.getAccount_iban());
	  		account2.setName(account.getName());
	  		account2.setInitbalance(account.getInitbalance());
	  		account2.setBalance(account.getBalance());
	  		locSession.flush();
	  		transaction.commit();
	      	isError = false;
	      } catch (Exception e) {
	    	  isError = true;
	      } finally {
		      	if (isError )
		      		System.out.println("** updateAccount() ERROR ** ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
		      	else
		      		System.out.println("** updateAccount() Account SUCCESFULLY UPDATED ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
	      }
	}

	/**
	 * saveAccount Creates a new Account with initial values on JSON File
	 * @param account
	 */
	private static void saveAccount(Account account) {
		
    	  boolean isError = false;
	      try {
	    	Session locSession = HibernateUtil.getSessionFactory().openSession();
	  		transaction = locSession.beginTransaction();
	  		locSession.save(account);
	  		locSession.flush();
	      	transaction.commit();
	      	isError = false;
	      } catch (Exception e) {
	            isError = true;
	      } finally {
	      	if (isError )
	      		System.out.println("** saveAccount() ERROR ** ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
	      	else
	      		System.out.println("** saveAccount() Account SUCCESFULLY INCLUDED ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
	      }
	}
	
	/**
	 * getByIBAN Returns true if account exists with this IBAN
	 * @param account_iban
	 * @return
	 */
	private static boolean getByIBAN(String account_iban) {
		
		Account retAccount = null;
		if ( account_iban !=null && !account_iban.isEmpty() ) {
			Session locSession = HibernateUtil.getSessionFactory().openSession();
		    CriteriaBuilder builder = locSession.getCriteriaBuilder();
		    CriteriaQuery<Account> query = builder.createQuery(Account.class);
		    Root<Account> root = query.from(Account.class);
		    query.select(root).where(builder.equal(root.get("account_iban"), account_iban.trim()));;
		    Query<Account> queryacct = locSession.createQuery(query);
		    try {
		    	retAccount = queryacct.getSingleResult();
		    	
		    	List<Account> results = queryacct.getResultList();
		        if (results.isEmpty()) 
		        	retAccount = null;
		        else if (results.size() >= 1) 
		        	retAccount = results.get(0);
		    } catch (NoResultException e ) {
		    	retAccount = null;
		    } catch (NonUniqueResultException e2 ) {
		    	retAccount = null;	
		    } finally {
	      	// Session close
	          if (initDBSession != null) {
	        	  locSession.close();
	          }
		    }
		} 
		if (retAccount== null ) {
			System.out.println(account_iban+" *** NO ENCONTRO ***");
			return false;
		} else {
			System.out.println(account_iban+" *** SI ENCONTRO ***");
			return true;
		}
	}

	/**
	 * initDBTables : Updates Tables Models
	 */
	private static void  initDBTables() {
		
        	// FUTURE INIT IMPROVEMETS
        	// DELETE RECORD FROM accounttransaction table 
        	//Query<?> query1 = initDBSession.createQuery("DELETE FROM AccountTransaction");
            //query1.executeUpdate();
            //initDBSession.flush();
            
//        	// DELETE RECORD FROM account table 
//        	Query<?> query2 = initDBSession.createQuery("DELETE FROM Account");
//            query2.executeUpdate();
//            initDBSession.flush();
            
            // FUTURE INIT IMPROVEMETS
            // UPDATE account_id_seq SEQUENCE to 1
        	//Query<?> query11 = initDBSession.createQuery("SELECT setval('account_id_seq', 1, true)");
            //query11.executeUpdate();
            //initDBSession.flush();
            // UPDATE account_id_seq SEQUENCE to 1
        	//Query<?> query21 = initDBSession.createQuery("SELECT setval('account_id_seq', 1, true)");
            //query21.executeUpdate();
            //initDBSession.flush();
            
            // transaction.commit();
            //uconf.shutdown();

//        } catch (Exception e) {
//            if (transaction != null) {
//              transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (initDBSession != null) {
//              initDBSession.close();
//            }
//        }
		
	}

}