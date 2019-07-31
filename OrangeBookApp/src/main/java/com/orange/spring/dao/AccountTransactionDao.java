package com.orange.spring.dao;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionDao {

	// Save the record
    long save(AccountTransaction accounttransaction);
 
    // Get a single record
    AccountTransaction get(long id);
 
    // Get all the records
    List<AccountTransaction> list();
 
    // Update the record
    void update(long id, AccountTransaction accounttransaction);
 
    // Delete a record
    void delete(long id);
    
}
