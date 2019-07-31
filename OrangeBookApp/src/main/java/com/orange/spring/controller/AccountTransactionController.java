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
	
	  /*---get all account transactions---*/
	   @GetMapping("/accounttransaction")
	   public ResponseEntity<List<AccountTransaction>> list() {
	      List<AccountTransaction> accounttransactions = accounttransactionService.list();
	      return ResponseEntity.ok().body(accounttransactions);
	   }
	   
	   /*---Add new account transaction---*/
	   @PostMapping("/accounttransaction")
	   public ResponseEntity<?> save(@RequestBody AccountTransaction accounttransaction) {
		  System.out.println("the json value of Account Transaction is :::::: "+accounttransaction);
	      long id = accounttransactionService.save(accounttransaction);
	      return ResponseEntity.ok().body("New Account Transaction has been saved with ID:" + id);
	  }
	   
	  /*---Get an account transaction by id---*/
	  @GetMapping("/accounttransaction/{id}")
	  public ResponseEntity<AccountTransaction> get(@PathVariable("id") long id) {
		  AccountTransaction accounttransaction = accounttransactionService.get(id);
	     return ResponseEntity.ok().body(accounttransaction);
	  }
	  
	   /*---Update an account transaction by id---*/
	   @PutMapping("/accounttransaction/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody AccountTransaction accounttransaction) {
		   accounttransactionService.update(id, accounttransaction);
	      return ResponseEntity.ok().body("Account transaction has been updated successfully.");
	   }
	   
	   /*---Delete an account transaction id---*/
	   @DeleteMapping("/accounttransaction/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") long id) {
		   accounttransactionService.delete(id);
	      return ResponseEntity.ok().body("Account Transaction has been deleted successfully.");
	   }
}
