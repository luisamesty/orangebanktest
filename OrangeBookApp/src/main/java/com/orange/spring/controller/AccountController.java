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

//import com.orange.spring.service.AccountService;

@CrossOrigin(origins = "*")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	  /*---get all accounts---*/
	   @GetMapping("/account")
	   public ResponseEntity<List<Account>> list() {
	      List<Account> accounts = accountService.list();
	      return ResponseEntity.ok().body(accounts);
	   }
	   
	  /*---Add new account---*/
	   @PostMapping("/account")
	   public ResponseEntity<?> save(@RequestBody Account account) {
		  System.out.println("the json value of Account is :::::: "+account);
	      long id = accountService.save(account);
	      return ResponseEntity.ok().body("New Account has been saved with ID:" + id);
	  }
	  
	  /*---Get an account by id---*/
	  @GetMapping("/account/{id}")
	  public ResponseEntity<Account> get(@PathVariable("id") long id) {
	     Account account = accountService.get(id);
	     return ResponseEntity.ok().body(account);
	  }

	   /*---Update an account by id---*/
	   @PutMapping("/account/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Account account) {
		   accountService.update(id, account);
	      return ResponseEntity.ok().body("Account has been updated successfully.");
	   }
	   
	   /*---Delete an account id---*/
	   @DeleteMapping("/account/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") long id) {
		   accountService.delete(id);
	      return ResponseEntity.ok().body("Account has been deleted successfully.");
	   }
}
