package com.orange.spring.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.orange.spring.model.Account;
import com.orange.spring.model.AccountTransaction;
import com.orange.spring.utils.HibernateUtil;

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
		long longid = (long) trObject.get("id");
		int id = (int) longid;
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
		BDamount = BDamount.setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println("tramount="+BDamount);
		//Get fee 
		double trfee = (double) trObject.get("trfee");	
		BigDecimal BDfee = new BigDecimal(trfee);
		BDfee = BDfee.setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println("trfee="+BDfee);
		//Get description 
		String trdescription = (String) trObject.get("trdescription");	
		System.out.println("trdescription="+trdescription);
		// status
		String trstatus = "** OK ***";
		// tr channel
		String trchannel = (String) trObject.get("trchannel");
		// AccountTransaction 
		@SuppressWarnings("null")
		AccountTransaction acctr = new AccountTransaction();
		acctr.setId(id);
		acctr.setAccount_iban(account_iban);
		acctr.setTreference(treference);
		acctr.setTrfecha(trfecha);
		acctr.setTramount(BDamount);
		acctr.setTrfee(BDfee);
		acctr.setTrdescription(trdescription);
		acctr.setTrstatus(trstatus);
		acctr.setTrchannel(trchannel);
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
    	Account account = null;
    	boolean isError = false;
    	BigDecimal newBalance = BigDecimal.ZERO;
    	BigDecimal netTRAmount = BigDecimal.ZERO;
    	String treference = "";
    	String trstatus = "";
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
			// Init Vars
			newBalance = BigDecimal.ZERO;
			treference = "";
			errorMessage="";
			trstatus = "";
    		// Get i Account Transaction
       		AccountTransaction acctr = acctransactions.get(i);
       		// Get Account 
       		// Verify Account BY IBAN
       		account = getAccountByIBAN(acctr.getAccount_iban(), session);
       		// Verify is OK
			if (account != null) {
	            // Account VALID and Balance OK
				// ADDITIONS
				if (acctr.getTramount().compareTo(BigDecimal.ZERO) >= 0) {
					netTRAmount = acctr.getTramount().subtract(acctr.getTrfee());
					// Update Balance
					newBalance = account.getBalance().add(netTRAmount);
					// 
					trstatus ="OK";
				// DEDUCTIONS
				} else {
					netTRAmount = acctr.getTramount().negate();
					netTRAmount = netTRAmount.add(acctr.getTrfee());
					System.out.println("** TRANSACTION netTRAmount="+netTRAmount+" Account Balance "+account.getBalance() );
					if (account.getBalance().compareTo(netTRAmount) >= 0) {
						// Update Balance
						newBalance = account.getBalance().subtract(netTRAmount);
						// 
						trstatus ="OK";
					} else {
						// TRANSACTION INVALID
						isError = true;
						trstatus ="INVALID";
		        		System.out.println("** TRANSACTION ERROR - BELLOW BALANCE ** "+errorMessage+" ID:"+acctr.getId()+" REF:"+acctr.getTreference()+"  IBAN:"+acctr.getAccount_iban());
					}					
				}
				// Final Account Updates
				account.setBalance(newBalance);
				//
			} else {
				// ACCOUNT INVALID
				isError = true;
				trstatus ="INVALID";
        		System.out.println("** TRANSACTION ERROR ACOUNT INVALID** "+errorMessage+" ID:"+acctr.getId()+" REF:"+acctr.getTreference()+"  IBAN:"+acctr.getAccount_iban());
			}
			if (!isError) {
				// Verify reference if not  put TR ID
				treference = acctr.getTreference();
				if (treference == null || treference.isEmpty() )
					treference = String.format ("%10s", acctr.getId());
				// Final Transaction Updates
				acctr.setTrstatus(trstatus);
				acctr.setTreference(treference);
				// TR Before Write to table
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
	            		System.out.println("** TRANSACTION WRITE ERROR ** "+errorMessage+" ID:"+acctr.getId()+" REF:"+acctr.getTreference()+"  IBAN:"+acctr.getAccount_iban());
	            }
	            // UPDATE BALANCE
	            if (!isError) {
	            	// Update Account Balance STATUS REFERENCE
	            	updateAccount(account, session);
	            }
			}
       	}
		// Session close
        if (session != null) {
          session.close();
        }
	}
	
	public static Account getAccountByIBAN(String account_iban, Session session) {
		List<Account> accts = null;
		Account retAccount = null;
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      CriteriaQuery<Account> query = builder.createQuery(Account.class);
	      Root<Account> root = query.from(Account.class);
	      query.select(root).where(builder.equal(root.get("account_iban"), account_iban));;
	      Query<Account> queryacct = session.createQuery(query);
	      try {
	    	  retAccount = queryacct.getSingleResult();
	      } catch (Exception e) {
  	  		System.out.println("** ERROR ** INVALID Account  IBAN:"+account_iban);
	      }
	      return retAccount;
	}
	
	public static void updateAccount(Account account, Session session) {
	      boolean isError = false;
	      String errorMessage="";
          try {
      		transaction = session.beginTransaction();
      		//transaction.begin();
      		session.save(account);
          	transaction.commit();
          	isError = false;
          } catch (Exception e) {
              if (transaction != null) {
                transaction.rollback();
              }
              errorMessage=e.getLocalizedMessage(); //e.getMessage();
              isError = true;
          } finally {
        	  	if(isError) {
        	  		if (account!= null)
        	  			System.out.println("** Write ERROR ** "+errorMessage+"Account ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
        	  		else
        	  			System.out.println("** Write ERROR ** "+errorMessage+"Account IBAN INVALID");
        	  	}
          }
	}
}
