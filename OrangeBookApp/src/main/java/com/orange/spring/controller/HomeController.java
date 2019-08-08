package com.orange.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orange.spring.model.Account;
import com.orange.spring.service.AccountService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {

		List<Account> accounts = accountService.listAccount();
		System.out.println("traza:"+accounts.toString());
		model.addAttribute("cuentas", accounts);
		model.addAttribute("cuenta", new Account());
	    return "cuentas";
	}
	
//	@RequestMapping("/addAccount")
//	  public String addAccount(@ModelAttribute("account") Account account) {
//		  account.setId(account.getId());
//		  accountService.save(account);
//		  return "redirect:/";
//	}
}

