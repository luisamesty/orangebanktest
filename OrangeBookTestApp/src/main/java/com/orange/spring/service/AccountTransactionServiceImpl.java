package com.orange.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.spring.dao.AccountDao;
import com.orange.spring.dao.AccountTransactionDao;
import com.orange.spring.model.AccountTransaction;

@Service
@Transactional(readOnly = true)
public class AccountTransactionServiceImpl implements AccountTransactionService  {

	private AccountTransactionDao accounttransactionDAO;

	public AccountTransactionDao getAccounttransactionDAO() {
		return accounttransactionDAO;
	}

	public void setAccounttransactionDAO(AccountTransactionDao accounttransactionDAO) {
		this.accounttransactionDAO = accounttransactionDAO;
	}

	@Override
	@Transactional
	public int addTransaction(AccountTransaction accounttransaction) {
		return accounttransactionDAO.addTransaction(accounttransaction);
	}

	@Override
	@Transactional
	public AccountTransaction getTransaction(int id) {
		return accounttransactionDAO.getTransactionById(id);
	}

	@Override
	@Transactional
	public List<AccountTransaction> listTransaction() {
		return accounttransactionDAO.listTransaction();
	}

	@Override
	@Transactional
	public void deleteTransaction(int id) {
		accounttransactionDAO.deleteTransaction(id);
	}

	@Override
	@Transactional
	public void updateTransaction(AccountTransaction accounttransaction) {
		// TODO Auto-generated method stub
		
	}

}
