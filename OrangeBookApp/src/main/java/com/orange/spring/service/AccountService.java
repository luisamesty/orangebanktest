package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.Account;

public interface AccountService {

	// Save the record
	long save(Account account);

	// Get a single record
	Account get(long id);

	// Get all the records
	List<Account> list();

	// Update the record
	void update(long id, Account account);

	// Delete a record
	void delete(long id);
	
	// Get a single record where account_iban
	Account getByIBAN(String account_iban );
	
}
