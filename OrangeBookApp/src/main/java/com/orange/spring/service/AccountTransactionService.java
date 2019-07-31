package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionService {

	long save(AccountTransaction accounttransaction);

	AccountTransaction get(long id);

	List<AccountTransaction> list();

	void update(long id, AccountTransaction accounttransaction);

	void delete(long id);
	
}
