package com.orange.spring.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CreateAccounts {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		File jsonFile = new File("json"+File.separatorChar+"Account.json");
		System.out.println(jsonFile.getAbsolutePath());
			
    	try (FileReader reader = new FileReader(jsonFile))
    	{
			//Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray trList = (JSONArray) obj;
            System.out.println(trList);
            
            //Iterate over transaction array
            trList.forEach( trans -> parseAccountObject( (JSONObject) trans ) );

    	} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private static void parseAccountObject(JSONObject trans) 
	{
		
	
		//Get transaction object within list
		JSONObject trObject = (JSONObject) trans.get("account");

		//Get id 
		Long id = (Long) trObject.get("id");	
		System.out.println(id);
		
		//Get name 
		String name = (String) trObject.get("name");	
		System.out.println(name);
		
		//Get account_iban 
		String account_iban = (String) trObject.get("account_iban");	
		System.out.println(account_iban);

		//Get balance 
		double balance = (double) trObject.get("balance");	
		BigDecimal BDbalance = new BigDecimal(balance);
		BDbalance = BDbalance.setScale(2, BigDecimal.ROUND_UP);
		System.out.println(BDbalance);

	}
}
