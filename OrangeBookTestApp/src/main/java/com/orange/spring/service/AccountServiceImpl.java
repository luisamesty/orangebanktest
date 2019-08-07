package com.orange.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.spring.dao.AccountDao;
import com.orange.spring.model.Account;

@Service
public class AccountServiceImpl implements AccountService {

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
	public void updateAccount(Account account) {
		this.accountDAO.updateAccount(account);
	}
	@Override
	@Transactional
	public List<Account> listAccount() {
		return this.accountDAO.listAccount();
	}

	@Override
	@Transactional
	public Account getAccountById(int id) {
		this.accountDAO.getAccountById(id);
		return null;
	}

	@Override
	@Transactional
	public void deleteAccount(int id) {
		this.accountDAO.deleteAccount(id);
	}

	@Override
	@Transactional
	public Account getByIBAN(String account_iban) {
		// TODO Auto-generated method stub
		return null;
	}



}
