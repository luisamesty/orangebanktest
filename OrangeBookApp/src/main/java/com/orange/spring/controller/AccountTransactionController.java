package com.orange.spring.controller;

import java.util.ArrayList;
import java.util.List;

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
		
	   /*--- (POST) Add new account transaction---*/
	   @PostMapping(value="/transaction/add" , consumes = "application/json", produces = "application/json")
	   public ResponseEntity<?> addTransaction(@RequestBody AccountTransaction accounttransaction) {
		  System.out.println("the json value of Account Transaction is :::::: "+accounttransaction);
		  int id = accounttransactionService.addTransaction(accounttransaction);
	      return ResponseEntity.ok().body("New Account Transaction has been saved with ID:" + id +"  Status:"+ accounttransaction.getTrstatus());
	  }

	  /*--- (POST Transaction Status ) Transaction Status ---*/
	  @SuppressWarnings("null")
	  @PostMapping(value="/transaction/getstatus", consumes = "application/json", produces = "application/json")
	  public ResponseEntity<?> getTransactionStatus(@RequestBody Payload payload) {
		  String plReference = payload.getPlreference();
		  String plChannel = payload.getPlchannel();
		  Response resp = new  Response();
		  AccountTransaction acctr = new AccountTransaction();
		  List<Response> responses =  new ArrayList<Response>(); 
		  // Check Null or Empty
		  if (plReference == null || plReference.isEmpty() || plChannel==null || plChannel .isEmpty()) {
			  // Invalid Reference
			  resp = new  Response();
			  resp.setRseference("XXXXXXXXX");
			  resp.setRsstatus("IVALID");
			  responses.clear();
			  responses.add(resp);
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
						  resp.setRsamount(acctr.getTramount());
						  resp.setRsstatus(acctr.getTrstatus());
						  responses.add(resp);
					  }
				  } else {
					  resp = new  Response();
					  resp.setRseference(plReference);
					  resp.setRsstatus("IVALID");
					  responses.clear();
					  responses.add(resp);
				  }
			  } else {
				  resp = new  Response();
				  resp.setRseference(plReference);
				  resp.setRsstatus("IVALID");
				  responses.clear();
				  responses.add(resp);
			  }
		  }
	     return ResponseEntity.ok().body(responses);
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
