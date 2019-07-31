package com.orange.spring.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.orange.spring.model.Account;
import com.orange.spring.model.AccountTransaction;

public interface AccountTransactionDao {

	// Save the record
    long save(AccountTransaction accounttransaction);
 
    // Get a single record
    AccountTransaction get(long id);
 
    // Get all the records
    List<AccountTransaction> list();
 
    // Update the record
    //void update(long id, Account account_id, String reference, String account_iban, Timestamp date, BigDecimal amount, BigDecimal fee, String description);
    void update(long id, AccountTransaction accounttransaction);
 
    // Delete a record
    void delete(long id);
    
}
