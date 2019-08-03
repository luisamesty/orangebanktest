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
	private AccountDao accountDao;
	
	@Transactional
	@Override
	public long save(Account account) {
		return accountDao.save(account);
	}

	@Override
	public Account get(long id) {
		return accountDao.get(id);
	}

	@Override
	public List<Account> list() {
		return accountDao.list();
	}

	@Transactional
	@Override
	public void update(long id, Account account) {
		accountDao.update(id, account);
	}

	@Transactional
	@Override
	public void delete(long id) {
		accountDao.delete(id);
	}

	@Override
	public Account getByIBAN(String account_iban) {
		return accountDao.getByIBAN(account_iban);
	}

}
