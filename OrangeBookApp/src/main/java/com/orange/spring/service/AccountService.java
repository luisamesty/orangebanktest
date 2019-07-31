package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.Account;

public interface AccountService {

	long save(Account account);

	Account get(long id);

	List<Account> list();

	void update(long id, Account account);

	void delete(long id);
	
}
