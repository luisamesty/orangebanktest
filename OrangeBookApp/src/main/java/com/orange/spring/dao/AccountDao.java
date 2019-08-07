package com.orange.spring.dao;

import java.util.List;

import com.orange.spring.model.Account;

public interface AccountDao {

	// Save the record
    int save(Account account);
 
    // Get a single record
    Account get(int id);
 
    // Get all the records
    List<Account> list();
 
    // Update the record
    void update(int id, Account account);
    
    // Delete a record
    void delete(int id);
 
    // Get a single record where account_iban
    Account getByIBAN(String account_iban);
}
