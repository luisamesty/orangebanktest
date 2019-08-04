package com.orange.spring.dao;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionDao {

	// Save the record
    long addTransaction(AccountTransaction accounttransaction);
 
    // Get a single record
    AccountTransaction getTransaction(long id);
 
    // Get all the records
    List<AccountTransaction> listTransaction();
 
    // Update the record
    void updateTransaction(long id, AccountTransaction accounttransaction);
 
    // Delete a record
    void deleteTransaction(long id);
    
}
