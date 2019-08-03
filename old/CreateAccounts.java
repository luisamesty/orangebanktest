package com.orange.spring.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orange.spring.model.Account;

@CrossOrigin(origins = "*")
@RestController
public class CreateAccounts {
	
    public static Session session = null;
    public static Transaction transaction = null;
    public static ArrayList<Account> accounts = null;
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		File jsonFile = new File("json"+File.separatorChar+"Account.json");
		System.out.println(jsonFile.getAbsolutePath());
		
		// SHOW RECORDS
    	try (FileReader reader = new FileReader(jsonFile))
    	{
			//Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray trList = (JSONArray) obj;
            System.out.println(trList);

            //Iterate over account array
            accounts = new ArrayList();
            trList.forEach( acct -> parseAccountObject( (JSONObject) acct ) );
            
    	} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    	
    	// CHECHA ARRAY
    	System.out.println("CHEQUEO ARREGLO .....");
    	for (int i=0 ; i < accounts.size(); i++) {
    		Account account = accounts.get(i);
    		System.out.println(account.toString());
    	}
    	// HIBERNATE 
        try {
        	// UtilConfig - Hibernate Conection 
        	UtilConfig uconf = new UtilConfig();
        	Properties props = uconf.getProperties();
        	//System.out.println( "postgresql.driver:"+props.getProperty("postgresql.driver"));
        	session = uconf.getSessionFactory().openSession();
            // GRABA ARREGLO
        	System.out.println("GRABA ARREGLO .....");
        	for (int i=0 ; i < accounts.size(); i++) {
        		transaction = session.beginTransaction();
        		//transaction.begin();
        		// Get i Account
        		Account account = accounts.get(i);
                System.out.println(account.toString());
                // Save account
                session.save(account);
                transaction.commit();
        	}
        	uconf.shutdown();
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
}
