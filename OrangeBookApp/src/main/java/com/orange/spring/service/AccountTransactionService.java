package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionService {

	Long addTransaction(AccountTransaction accounttransaction);

	AccountTransaction getTransaction(long id);

	List<AccountTransaction> listTransaction();

	void updateTransaction(long id, AccountTransaction accounttransaction);

	void deleteTransaction(long id);
	
}
