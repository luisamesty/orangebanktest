package com.orange.spring.dao;

import java.util.List;

import com.orange.spring.model.AccountTransaction;
import com.orange.spring.model.TransactionId;

public interface AccountTransactionDao {

	// Save the record
	int addTransaction(AccountTransaction accounttransaction);
 
    // Get a single record
    AccountTransaction getTransaction(int id);
 
    // Get all the records
    List<AccountTransaction> listTransaction();
 
    // Update the record
    void updateTransaction(int id, AccountTransaction accounttransaction);
 
    // Delete a record
    void deleteTransaction(int id);
    
}
