package com.orange.spring.dao;

import java.util.List;

import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionDao {

	// Save the record
	int addTransaction(AccountTransaction accounttransaction);
 
    // Get a single record
    AccountTransaction getTransactionById(int id);
 
    // Get all the records
    List<AccountTransaction> listTransaction();
 
	// Get records by IBAN 
    List<AccountTransaction> listTransactionsByIBAN(String account_iban, String ASC_DESC);
	
    // Get Records by Reference
	List<AccountTransaction> listTransactionsByREF(String treference);
	
    // Update the record
    void updateTransaction(AccountTransaction accounttransaction);
 
    // Delete a record
    void deleteTransaction(int id);
    
}
