package com.orange.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.spring.dao.AccountTransactionDao;
import com.orange.spring.model.AccountTransaction;
import com.orange.spring.model.TransactionId;

@Service
@Transactional(readOnly = true)
public class AccountTransactionServiceImpl implements AccountTransactionService  {

	@Autowired
	private AccountTransactionDao accounttransactionDao;
	
	@Transactional
	@Override
	public Long addTransaction(AccountTransaction accounttransaction) {
		return accounttransactionDao.addTransaction(accounttransaction);
	}

	@Override
	public AccountTransaction getTransaction(long id) {
		return accounttransactionDao.getTransaction(id);
	}

	@Override
	public List<AccountTransaction> listTransaction() {
		return accounttransactionDao.listTransaction();
	}

	@Transactional
	@Override
	public void updateTransaction(long id, AccountTransaction accounttransaction) {
		accounttransactionDao.updateTransaction(id, accounttransaction);
	}

	@Transactional
	@Override
	public void deleteTransaction(long id) {
		accounttransactionDao.deleteTransaction(id);
	}

}
