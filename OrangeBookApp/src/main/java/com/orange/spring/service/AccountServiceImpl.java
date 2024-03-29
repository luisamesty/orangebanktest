package com.orange.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.spring.dao.AccountDao;
import com.orange.spring.model.Account;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService  {

	@Autowired
	private AccountDao accountDAO;

	public void setAccountDAO(AccountDao accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	@Transactional
	public void addAccount(Account account) {
		this.accountDAO.addAccount(account);
	}

	@Override
	@Transactional
	public void updateAccount(int id, Account account) {
		this.accountDAO.updateAccount(id, account);
	}

	@Override
	@Transactional
	public List<Account> listAccount() {
		return this.accountDAO.listAccount();
	}
	
	@Override
	@Transactional
	public Account getAccountById(int id) {
		return this.accountDAO.getAccountById(id);
	}
	
	@Override
	@Transactional
	public void deleteAccount(int id) {
		this.accountDAO.deleteAccount(id);
	}

	@Override
	@Transactional
	public Account getByIBAN(String account_iban) {
		return this.accountDAO.getByIBAN(account_iban);
	}




}
