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
	//  **********************************************
	//  *************  ALL TRANSACTIONS **************
	//  **********************************************
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
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String listTransactions(Model model) {
		model.addAttribute("transaction", new AccountTransaction());
		model.addAttribute("listTransactions", this.accounttransactionService.listTransaction());
		return "transaction";
	}
	
	//  *********************************************************
	//  *************  SEARCH TRANSACTIONS BY IBAN **************
	//  *********************************************************
	//For add and update account transaction both
	@RequestMapping(value= "/transaction/search", method = RequestMethod.POST)
	public String searchTransaction(@ModelAttribute("transaction") AccountTransaction actr){

		return "redirect:/tr_search";
	}
	
	// tr_search By IBAN  ( listTransactionsByIBAN )
	@RequestMapping(value = "/tr_search", method = RequestMethod.GET)
	public String listTransactionsByIBAN(Model model) {
		String account_iban = "";
		String ASC_DES="A";	// A: ASCEND D: DESCEND
		model.addAttribute("transactionsrc", new AccountTransaction());
		model.addAttribute("listTransactionsByIBAN", this.accounttransactionService.listTransactionsByIBAN(account_iban, ASC_DES));
		return "tr_search";
	}
	
	//  **************************************************************
	//  *************  SEARCH TRANSACTIONS BY REFERENCE **************
	//  **************************************************************
	
	//For add and update account transaction both
	@RequestMapping(value= "/transaction/status", method = RequestMethod.POST)
	public String statusTransaction(@ModelAttribute("transaction") AccountTransaction actr){

		return "redirect:/tr_status";
	}
	
	// tr_status By REF  ( listTransactionsByREF )
	@RequestMapping(value = "/tr_status", method = RequestMethod.GET)
	public String llistTransactionsByREF(Model model) {
		model.addAttribute("transactionref", new AccountTransaction());
		String treference="12345A";
		model.addAttribute("listTransactionsByREF", this.accounttransactionService.listTransactionsByREF(treference));
		return "tr_status";
	}
	
}