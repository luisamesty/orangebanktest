package com.orange.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orange.spring.model.AccountTransaction;
import com.orange.spring.service.AccountTransactionService;

@Controller
public class AccountTransactionController {
	
	private AccountTransactionService accounttransactionService;
	@Autowired(required=true)
	@Qualifier(value="accounttransactionService")	
	public void setAccountTransactionService(AccountTransactionService acctrser){
		this.accounttransactionService = acctrser;
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String listAccounts(Model model) {
		model.addAttribute("transaction", new AccountTransaction());
		model.addAttribute("listTransactions", this.accounttransactionService.listTransaction());
		return "transaction";
	}
	
	//For add and update account transaction both
	@RequestMapping(value= "/transaction/add", method = RequestMethod.POST)
	public String addTransaction(@ModelAttribute("transaction") AccountTransaction actr){
		if(actr.getId() == 0){
			//new person, add it
			this.accounttransactionService.addTransaction(actr);
		}else{
			//existing person, call update
			this.accounttransactionService.updateTransaction(actr);
		}
		return "redirect:/transactions";
	}
}
