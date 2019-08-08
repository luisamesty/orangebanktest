package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionService {

	int addTransaction(AccountTransaction accounttransaction);

	AccountTransaction getTransaction(int id);

	List<AccountTransaction> listTransaction();

	List<AccountTransaction> listTransactionsByIBAN(String account_iban, String ASC_DESC);
	
	List<AccountTransaction> listTransactionsByREF(String treference);
	
	void updateTransaction(AccountTransaction accounttransaction);

	void deleteTransaction(int id);
	
}
