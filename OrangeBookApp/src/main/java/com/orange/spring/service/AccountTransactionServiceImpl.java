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

	@Override
	public int addTransaction(AccountTransaction accounttransaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountTransaction getTransaction(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountTransaction> listTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountTransaction> listTransactionsByIBAN(String account_iban, String ASC_DESC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountTransaction> listTransactionsByREF(String treference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTransaction(AccountTransaction accounttransaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransaction(int id) {
		// TODO Auto-generated method stub
		
	}
	


}
