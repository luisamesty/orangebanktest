package com.orange.spring.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

import com.orange.spring.model.AccountTransaction;
import com.orange.spring.utils.UtilConfig;

@CrossOrigin(origins = "*")
@RestController
public class CreateTransaction {

    public static Session session = null;
    public static Transaction transaction = null;
    public static ArrayList<AccountTransaction> acctransactions = new ArrayList(); 
    
	public static void main(String[] args) {
		
		// Account Transactions File
		File jsonFile = new File("json"+File.separatorChar+"Transaction.json");
		System.out.println(jsonFile.getAbsolutePath());
		readTransactionFile(jsonFile);
		// Display Transaction Array to Console
		displayTransactionArray();
		// Saves Account Array
		saveTransactionArray();
	}
	
    /**
     * readTransactionFile
     * Reads Json Transaction File 
     * @param transJsonFile
     */
    private static void readTransactionFile(File transJsonFile) {
    

		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		// SHOW RECORDS
    	try (FileReader reader = new FileReader(transJsonFile))
    	{
			//Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray trList = (JSONArray) obj;
            //System.out.println(trList);

            //Iterate over account array
            
            trList.forEach( acct -> parseTransactionObject( (JSONObject) acct ) );
            
    	} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    	
    }
    
    /**
     * parseTransactionObject
     * Parse Json Objects and convert to public Arrray transactions
     * @param trans
     */
	private static void parseTransactionObject(JSONObject trans) 
	{
		
		//Get transaction object within list
		JSONObject trObject = (JSONObject) trans.get("transaction");

		//Get id (Actually added for Test in JSON File)
		Long id = (Long) trObject.get("id");	
		System.out.println("id="+id);
		//Get account_id (Actually added for Test in JSON File)
		Long account_id = (Long) trObject.get("account_id");	
		System.out.println("account_id="+account_id);
		//Get reference 
		String reference = (String) trObject.get("reference");	
		System.out.println("reference="+reference);
		//Get account_iban 
		String account_iban = (String) trObject.get("account_iban");	
		System.out.println("account_iban="+account_iban);
		// Get transaction date
		String date = (String) trObject.get("date");	
		System.out.println("date="+date);
		//System.out.println("Temp String date="+date.substring(0,10)+" "+date.substring(11,23));
		Timestamp TSdate = Timestamp.valueOf(date.substring(0,10)+" "+date.substring(11,23));
		System.out.println("TSdate="+TSdate);
		//Get amount 
		double amount = (double) trObject.get("amount");	
		BigDecimal BDamount = new BigDecimal(amount);
		BDamount = BDamount.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("balance="+BDamount);
		//Get fee 
		double fee = (double) trObject.get("fee");	
		BigDecimal BDfee = new BigDecimal(fee);
		BDamount = BDfee.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("balance="+BDfee);
		//Get description 
		String description = (String) trObject.get("description");	
		System.out.println("description="+description);
		// status
		String status = "** Por revisar ***";
		// AccountTransaction 
		@SuppressWarnings("null")
		AccountTransaction acctr = new AccountTransaction();
		acctr.setId(id);
		acctr.setAccount_id(account_id);
		acctr.setReference(reference);
		acctr.setDate(TSdate);
		acctr.setAccount_iban(account_iban);
		acctr.setAmount(BDamount);
		acctr.setFee(BDfee);
		acctr.setDescription(description);
		acctr.setStatus(status);
		
		// ADD TO ARRAY
		acctransactions.add(acctr);
	}
	
	/**
	 * displayTransactionArray
	 * Shows AccountTransaction Array
	 */
	private static void displayTransactionArray() {
    	// CHECK ARRAY
    	System.out.println("Arreglo de Transacciones .....");
    	for (int i=0 ; i < acctransactions.size(); i++) {
    		AccountTransaction acctr = acctransactions.get(i);
    		System.out.println(acctr.toString());
    	}
	}
	
	/**
	 * saveTransactionArray
	 * Saves to DB AccountTransaction Array
	 */
	
	private static void saveTransactionArray() {
		
	   	// HIBERNATE 
        try {
        	// UtilConfig - Hibernate Conection 
        	UtilConfig uconf = new UtilConfig();
        	//Properties props = uconf.getProperties();
        	//session = uconf.getSessionFactory().openSession();
        	session = uconf.getSessionFactoryB().openSession();
        	
            // RECORDS Account Array
        	System.out.println("GRABANDO ARREGLO DE TRANSACCIONES .....");
        	for (int i=0 ; i < acctransactions.size(); i++) {
        		transaction = session.beginTransaction();
        		//transaction.begin();
        		// Get i Account
        		AccountTransaction acctr = acctransactions.get(i);
                System.out.println("SAVING..."+acctr.toString());
                // Save account
                //try {
                	session.save(acctr);
                	transaction.commit();
                //} catch( javax.persistence.PersistenceException  ex) {
                //	if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
                //		System.out.println("** DUPLICATE ** "+acctransactions.toString());
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
}
