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

import com.orange.spring.model.Account;
import com.orange.spring.service.AccountService;

@CrossOrigin(origins = "*")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	  /*--- (GET ALL) get all accounts---*/
	   @GetMapping("/account")
	   public ResponseEntity<List<Account>> listAccounts() {
	      List<Account> accounts = accountService.listAccount();
	      return ResponseEntity.ok().body(accounts);
	   }
	   
	  /*---(POST) Add new account---*/
	   @PostMapping("/account")
	   public ResponseEntity<?> saveAccount(@RequestBody Account account) {
		  // New Validation By ID
		  Account account2 = null;
		  if (account.getId() != 0) {
			  account2 = accountService.getAccountById(account.getId());
			  if (account2 != null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** EXIST Account. NO Account has been saved with ID:" + account.getId());
			  }
		  }
		  // New Validation by IBAN
		  if (account.getAccount_iban() == null || account.getAccount_iban().isEmpty()) {
			  return ResponseEntity.ok().body("** ERROR ** Account IBAN IS NOT VALID :" + account.getAccount_iban());
		  } else {
			  account2 = accountService.getByIBAN(account.getAccount_iban());
			  if (account2 != null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** EXIST Account IBAN:"+ account2.getAccount_iban() +". NO Account has been saved with ID:" + account.getId());
			  }
		  }
		  // OJO Validation
		 accountService.addAccount(account);
	      return ResponseEntity.ok().body("New Account has been saved with ID:" + account.getId());
	  }
	  
	  /*---(GET 1)Get an account by id---*/
	  @GetMapping("/account/{id}")
	  public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
		 int intid = id.intValue();
	     Account account = accountService.getAccountById(intid);
	     return ResponseEntity.ok().body(account);
	  }

	   /*---(PUT) Update an account by id---*/
	   @PutMapping("/account/{id}")
	   public ResponseEntity<?> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
		  // New Validation By ID
		  Account account2 = null;
		  if (account.getId() != 0) {
			  account2 = accountService.getAccountById(account.getId());
			  if (id != account2.getId()) {
				  return ResponseEntity.ok().body("** ERROR ** Account and ID Missmatch. NO Account has been updated with ID:" + (id) +" / "+account.getId()); 
			  }
			  account2 = accountService.getAccountById(account.getId());
			  if (account2 == null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** Account DO NOT EXIST. NO Account has been updated with ID:" + account.getId());
			  }
		  }
		  accountService.updateAccount(account2);
	      return ResponseEntity.ok().body("Account has been updated successfully.");
	   }
	   
	   /*---(DEL) Delete an account id---*/
	   @DeleteMapping("/account/{id}")
	   public ResponseEntity<?> deleteAccount(@PathVariable("id") int id) {
		  // New Validation By ID
		   if (accountService.getAccountById(id) == null) {
				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
				  return ResponseEntity.ok().body("** ERROR ** Account DO NOT EXIST. NO Account has been deleted with ID:" + id);
		  }
		  accountService.deleteAccount(id);
	      return ResponseEntity.ok().body("Account has been deleted successfully.");
	   }
}
