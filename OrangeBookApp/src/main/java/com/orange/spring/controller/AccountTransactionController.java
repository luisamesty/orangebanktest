package com.orange.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.spring.model.AccountTransaction;
import com.orange.spring.model.Payload;
import com.orange.spring.model.PayloadTransaction;
import com.orange.spring.model.Response;
import com.orange.spring.service.AccountTransactionService;

@CrossOrigin(origins = "*")
@RestController
public class AccountTransactionController {

	@Autowired
	private AccountTransactionService accounttransactionService;
	
	  /*--- (GET ALL) get all account transactions---*/
	   @GetMapping("/transaction/get")
	   public ResponseEntity<List<AccountTransaction>> listTransaction() {
		   System.out.println("get all account transactions...");
	      List<AccountTransaction> accounttransactions = accounttransactionService.listTransaction();
	      return ResponseEntity.ok().body(accounttransactions);
	   }
		

	   @PostMapping(value="/transaction/add" , consumes = "application/json", produces = "application/json")
	   public ResponseEntity<?> addTransaction(@RequestBody PayloadTransaction payloadTR) {
		  AccountTransaction accounttransaction = new AccountTransaction();
		  accounttransaction.setTreference(payloadTR.getReference());
		  accounttransaction.setAccount_iban(payloadTR.getAccount_iban());
		  accounttransaction.setTrfecha(payloadTR.getDate());
		  accounttransaction.setTramount(payloadTR.getAmount());
		  accounttransaction.setTrfee(payloadTR.getFee());
		  accounttransaction.setTrdescription(payloadTR.getDescription());
		  System.out.println("the json value of Account Transaction is :::::: "+accounttransaction);
		  int id = accounttransactionService.addTransaction(accounttransaction);
		  if (id > 0)
			  return ResponseEntity.ok().body("New Account Transaction has been saved with ID:" + id +"  Status:"+ accounttransaction.getTrstatus());
		  else
			  return ResponseEntity.ok().body("** ERROR Account Transaction not SAVED ***  Status:"+ accounttransaction.getTrstatus());
	   }
	   
	  /*--- (POST Transaction Status ) Transaction Status ---*/
	  @SuppressWarnings("null")
	  @PostMapping(value="/transaction/getstatus", consumes = "application/json", produces = "application/json")
	  public ResponseEntity<?> getTransactionStatus(@RequestBody Payload payload) {
		  String plReference = payload.getReference();
		  String plChannel = payload.getChannel();
		  Response resp = new  Response();
		  AccountTransaction acctr = new AccountTransaction();
		  List<Response> responses =  new ArrayList<Response>(); 
		  JSONObject retJsonObject = new JSONObject();
		  // Check Null or Empty
		  if (plReference == null || plReference.isEmpty()) {
			  // Invalid Reference
			  resp = new  Response();
			  resp.setRseference("XXXXXXXXX");
			  resp.setRsstatus("INVALID");
			  responses.clear();
			  responses.add(resp);
			  retJsonObject = resp.retJsonResponseReferenceOnly(resp);
		  } else {
			  // Return all Transactions that meet
			  List<AccountTransaction> acctrans = accounttransactionService.listTransactionsByREF(plReference);
			  if (acctrans != null) {
				  responses.clear();
				  if (acctrans.size() > 0) {
					  for (int iac=0; iac < acctrans.size() ; iac++ ) {
						  resp = new  Response();
						  acctr = acctrans.get(iac);
						  resp.setRsamount(acctr.getTramount());
						  resp.setRseference(acctr.getTreference());
						  resp.setRsfee(acctr.getTrfee());
						  resp.setRsstatus(acctr.getTrstatus());
						  resp.setRsfecha(acctr.getTrfecha());
						  responses.add(resp);
						  if ( plChannel.compareTo("CLIENT")== 0) {
							  retJsonObject = resp.retJsonResponseReferenceAmount(resp,plChannel );
						  }  else if (plChannel.compareTo("ATM")== 0) {
							  retJsonObject = resp.retJsonResponseReferenceAmount(resp, plChannel);
						  } else if (plChannel.compareTo("INTERNAL")== 0) {
							  retJsonObject = resp.retJsonResponseReferenceAmount(resp, plChannel);
						  } else {
							  plChannel ="ALL";
							  retJsonObject = resp.retJsonResponseReferenceAmount(resp, plChannel);
						  }
						}
				  } else {
					  resp = new  Response();
					  resp.setRseference(plReference);
					  resp.setRsstatus("INVALID");
					  responses.clear();
					  responses.add(resp);
					  retJsonObject = resp.retJsonResponseReferenceOnly(resp);
				  }
			  } else {
				  resp = new  Response();
				  resp.setRseference(plReference);
				  resp.setRsstatus("INVALID");
				  responses.clear();
				  responses.add(resp);
				  retJsonObject = resp.retJsonResponseReferenceOnly(resp);
			  }
		  }
	     return ResponseEntity.ok().body(retJsonObject);
	  }
	  
	  /*--- (GET 1) Get an account transaction by id---*/
	  @GetMapping("/transaction/get/{id}")
	  public ResponseEntity<AccountTransaction> getTransaction(@PathVariable("id") int id) {
		  AccountTransaction accounttransaction = accounttransactionService.getTransaction(id);
	     return ResponseEntity.ok().body(accounttransaction);
	  }

	   /*--- (PUT) Update an account transaction by id  (NOT USED)---*/
	   @PutMapping("/transaction/put/{id}")
	   public ResponseEntity<?> updateTransaction(@PathVariable("id") int id, @RequestBody AccountTransaction accounttransaction) {
		   System.out.println("the json value of Account Transaction is :::::: "+accounttransaction);
		   accounttransactionService.updateTransaction(accounttransaction);
	      return ResponseEntity.ok().body("Account transaction has been updated successfully.");
	   }
 
	   /*--- (DEL) Delete an account transaction id (NOT USED)---*/
	   @DeleteMapping("/transaction/del/{id}")
	   public ResponseEntity<?> deleteTransaction(@PathVariable("id") int id) {
		  // New Validation By ID
		  if (accounttransactionService.getTransaction(id) == null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** Account Transaction DO NOT EXIST. NO transaction has been deleted with ID:" + id);
		  }
		  accounttransactionService.deleteTransaction(id);
	      return ResponseEntity.ok().body("Account Transaction has been deleted successfully. ID="+id);
	   }
}
