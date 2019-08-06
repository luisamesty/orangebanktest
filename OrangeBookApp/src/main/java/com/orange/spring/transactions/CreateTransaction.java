package com.orange.spring.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orange.spring.dao.AccountDao;
import com.orange.spring.dao.AccountDaoImpl;
import com.orange.spring.model.Account;
import com.orange.spring.model.AccountTransaction;
import com.orange.spring.service.AccountService;
import com.orange.spring.service.AccountServiceImpl;
import com.orange.spring.utils.HibernateUtil;
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
		//Get account_iban (Actually added for Test in JSON File)
		String account_iban =  (String) trObject.get("account_iban");	
		System.out.println("account_iban="+account_iban);
		//Get treference 
		String treference = (String) trObject.get("treference");	
		System.out.println("treference="+treference);
		// Get transaction trfecha
		String trfecha = (String) trObject.get("trfecha");	
		System.out.println("trdate="+trfecha);
		//System.out.println("Temporary String trfecha="+date.substring(0,10)+" "+trdate.substring(11,23));
		Timestamp TSdate = Timestamp.valueOf(trfecha.substring(0,10)+" "+trfecha.substring(11,23));
		System.out.println("trdate="+TSdate);
		//Get amount 
		double tramount = (double) trObject.get("tramount");	
		BigDecimal BDamount = new BigDecimal(tramount);
		BDamount = BDamount.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("tramount="+BDamount);
		//Get fee 
		double trfee = (double) trObject.get("trfee");	
		BigDecimal BDfee = new BigDecimal(trfee);
		BDamount = BDfee.setScale(2, BigDecimal.ROUND_UP);
		System.out.println("trfee="+BDfee);
		//Get description 
		String trdescription = (String) trObject.get("trdescription");	
		System.out.println("trdescription="+trdescription);
		// status
		String trstatus = "** Por revisar ***";
		// AccountTransaction 
		@SuppressWarnings("null")
		AccountTransaction acctr = new AccountTransaction();
		acctr.setId(id);
		acctr.setAccount_iban(account_iban);
		acctr.setTReference(treference);
		acctr.setTrFecha(trfecha);
		acctr.setTrAmount(BDamount);
		acctr.setTrFee(BDfee);
		acctr.setTrdescription(trdescription);
		acctr.setTrstatus(trstatus);
		
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
		
    	// UtilConfig - HIB CONN 
    	UtilConfig uconf = new UtilConfig();
    	AccountDao accountDao = new AccountDaoImpl();
    	Account account = null;
    	boolean isError = false;
    	String errorMessage="";
    	// Session open
    	try {
			//session = uconf.getSessionFactoryB().openSession();
			session = HibernateUtil.getSessionFactory().openSession();
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}
        // RECORDS Account Array
    	System.out.println("GRABANDO ARREGLO DE TRANSACCIONES .....");
       	for (int i=0 ; i < acctransactions.size(); i++) {
    		// Get i Account Transaction
       		AccountTransaction acctr = acctransactions.get(i);
       		// Get Account 
       		System.out.println("Traza cuenta TR ...:"+acctr.getAccount_iban());
//			account = accountDao.getByIBAN(acctr.getAccount_iban());
//       		if (account != null)
//       			System.out.println("Traza cuenta BANCO ...:"+account.getAccount_iban());
            // Verify IF exists 
        	System.out.println(acctr.toString());
    		// HIBERNATE 
            try {
        		transaction = session.beginTransaction();
        		//transaction.begin();
        		session.save(acctr);
            	transaction.commit();
            	isError = false;
            } catch (Exception e) {
                if (transaction != null) {
                  transaction.rollback();
                }
                errorMessage=e.getLocalizedMessage(); //e.getMessage();
                isError = true;
            } finally {
            	if(isError)
            		System.out.println("** ERROR ** "+errorMessage+" ID:"+acctr.getId()+" REF:"+acctr.getTReference()+"  IBAN:"+acctr.getAccount_iban());
            }
        }
       	// Session close
        if (session != null) {
          session.close();
        }
	}
}
