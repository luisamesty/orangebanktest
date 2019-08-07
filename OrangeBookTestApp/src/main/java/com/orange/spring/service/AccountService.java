package com.orange.spring.service;

import java.util.List;

import com.orange.spring.model.Account;

public interface AccountService {

	// Save the record
    public void addAccount(Account account);
 
    // Update the record
    public void updateAccount(Account account);

    // Get all the records
    public List<Account> listAccount();

    // Get a single record
    public Account getAccountById(int id);
 
    // Delete a record
    public void deleteAccount(int id);
 
    // Get a single record where account_iban
    public Account getByIBAN(String account_iban);
}
