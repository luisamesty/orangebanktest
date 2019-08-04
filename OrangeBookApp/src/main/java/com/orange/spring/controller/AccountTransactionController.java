package com.orange.spring.controller;

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
import com.orange.spring.service.AccountTransactionService;

@CrossOrigin(origins = "*")
@RestController
public class AccountTransactionController {

	@Autowired
	private AccountTransactionService accounttransactionService;
	
	  /*--- (GET ALL) get all account transactions---*/
	   @GetMapping("/transaction")
	   public ResponseEntity<List<AccountTransaction>> listTransaction() {
		   System.out.println("get all account transactions...");
	      List<AccountTransaction> accounttransactions = accounttransactionService.listTransaction();
	      return ResponseEntity.ok().body(accounttransactions);
	   }
	   
	   /*--- (POST) Add new account transaction---*/
	   @PostMapping("/transaction")
	   public ResponseEntity<?> addTransaction(@RequestBody AccountTransaction accounttransaction) {
		  System.out.println("the json value of Account Transaction is :::::: "+accounttransaction);
	      long id = accounttransactionService.addTransaction(accounttransaction);
	      return ResponseEntity.ok().body("New Account Transaction has been saved with ID:" + id);
	  }
	   
	  /*--- (GET 1) Get an account transaction by id---*/
	  @GetMapping("/transaction/{id}")
	  public ResponseEntity<AccountTransaction> getTransaction(@PathVariable("id") long id) {
		  AccountTransaction accounttransaction = accounttransactionService.getTransaction(id);
	     return ResponseEntity.ok().body(accounttransaction);
	  }
	  
	   /*--- (PUT) Update an account transaction by id---*/
	   @PutMapping("/transaction/{id}")
	   public ResponseEntity<?> updateTransaction(@PathVariable("id") long id, @RequestBody AccountTransaction accounttransaction) {
		   accounttransactionService.updateTransaction(id, accounttransaction);
	      return ResponseEntity.ok().body("Account transaction has been updated successfully.");
	   }
	   
	   /*--- (DEL) Delete an account transaction id---*/
	   @DeleteMapping("/transaction/{id}")
	   public ResponseEntity<?> deleteTransaction(@PathVariable("id") long id) {
		  // New Validation By ID
		  if (accounttransactionService.getTransaction(id) == null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** Account Transaction DO NOT EXISTT. NO ransaction has been deleted with ID:" + id);
		  }
		  accounttransactionService.deleteTransaction(id);
	      return ResponseEntity.ok().body("Account Transaction has been deleted successfully.");
	   }
}
