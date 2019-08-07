package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionService {

	int addTransaction(AccountTransaction accounttransaction);

	AccountTransaction getTransaction(int id);

	List<AccountTransaction> listTransaction();

	void updateTransaction(int id, AccountTransaction accounttransaction);

	void deleteTransaction(int id);
	
}
