package com.orange.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import java.math.BigDecimal;

@Entity(name = "Account")
@Table(name = "account")

public class Account {
	@OrderBy 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true)
	int id;
	
	@Column(name="NAME", nullable = false)
	String name;
	
	String account_iban;
	
	BigDecimal balance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
