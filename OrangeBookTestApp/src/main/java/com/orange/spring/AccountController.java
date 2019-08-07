package com.orange.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orange.spring.model.Account;
import com.orange.spring.model.AccountTransaction;
import com.orange.spring.service.AccountService;
import com.orange.spring.service.AccountTransactionService;

@Controller

public class AccountController {
	
	
	private AccountService accountService;
	@Autowired(required=true)
	@Qualifier(value="accountService")	
	public void setAccountService(AccountService accser){
		this.accountService = accser;
	}
	
//	  /*---(POST) Add new account---*/
//	   @PostMapping("/account")
//	   public ResponseEntity<?> save(@RequestBody Account account) {
//		  // New Validation By ID
//		  Account account2 = null;
//		  if (account.getId() != 0) {
//			  account2 = accountService.get(account.getId());
//			  if (account2 != null) {
//				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
//				  return ResponseEntity.ok().body("** ERROR ** EXIST Account. NO Account has been saved with ID:" + account.getId());
//			  }
//		  }
//		  // New Validation by IBAN
//		  if (account.getAccount_iban() == null || account.getAccount_iban().isEmpty()) {
//			  return ResponseEntity.ok().body("** ERROR ** Account IBAN IS NOT VALID :" + account.getAccount_iban());
//		  } else {
//			  account2 = accountService.getByIBAN(account.getAccount_iban());
//			  if (account2 != null) {
//				  //System.out.println("** ERROR, EXIST Account ::::: "+account2.toString());
//				  return ResponseEntity.ok().body("** ERROR ** EXIST Account IBAN:"+ account2.getAccount_iban() +". NO Account has been saved with ID:" + account.getId());
//			  }
//		  }
//		  // OJO Validation
//		  int id = accountService.save(account);
//	      return ResponseEntity.ok().body("New Account has been saved with ID:" + id);
//	  }
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String listAccounts(Model model) {
		model.addAttribute("account", new Account());
		model.addAttribute("listAccounts", this.accountService.listAccount());
		return "account";
	}
	
	//For add and update account both
	@RequestMapping(value= "/account/add", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") Account ac){
		if(ac.getId() == 0){
			//new person, add it
			this.accountService.addAccount(ac);  
		}else{
			//existing person, call update
			this.accountService.updateAccount(ac);
		}
		
			return "redirect:/accounts";
	}
	
	@RequestMapping("/account/remove/{id}")
    public String removeAccount(@PathVariable("id") int id){
		
        this.accountService.deleteAccount(id);
        return "redirect:/accounts";
    }
	
    @RequestMapping("/account/edit/{id}")
    public String editAccount(@PathVariable("id") int id, Model model){
        model.addAttribute("account", this.accountService.getAccountById(id));
        model.addAttribute("listAccounts", this.accountService.listAccount());
        return "account";
    }
}