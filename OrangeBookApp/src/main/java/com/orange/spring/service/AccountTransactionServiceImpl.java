package com.orange.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.orange.spring.dao.AccountTransactionDao;
import com.orange.spring.model.AccountTransaction;

public class AccountTransactionServiceImpl implements AccountTransactionService  {

	@Autowired
	private AccountTransactionDao accounttransactionDao;
	
	@Transactional
	@Override
	public long save(AccountTransaction accounttransaction) {
		return accounttransactionDao.save(accounttransaction);
	}

	@Override
	public AccountTransaction get(long id) {
		return accounttransactionDao.get(id);
	}

	@Override
	public List<AccountTransaction> list() {
		return accounttransactionDao.list();
	}

	@Transactional
	@Override
	public void update(long id, AccountTransaction accounttransaction) {
		accounttransactionDao.update(id, accounttransaction);
	}

	@Transactional
	@Override
	public void delete(long id) {
		accounttransactionDao.delete(id);
	}

}
