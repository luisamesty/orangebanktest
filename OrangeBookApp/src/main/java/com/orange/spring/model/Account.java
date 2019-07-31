package com.orange.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String account_iban;
	BigDecimal balance;
	
	/* Constructor */
	public Account(Long id, String name, String account_iban, BigDecimal balance) {
		super();
		this.id = id;
		this.name = name;
		this.account_iban = account_iban;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount_iban() {
		return account_iban;
	}
	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", account_iban=" + account_iban + ", balance=" + balance + "]";
	}

}