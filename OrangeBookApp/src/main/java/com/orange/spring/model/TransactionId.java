package com.orange.spring.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransactionId implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String account_iban;
	protected String reference;
	
	public TransactionId() {
		
	}

	public TransactionId(String account_iban, String reference) {
		super();
		this.account_iban = account_iban;
		this.reference = reference;
	}

	public String getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "TransactionId [account_iban=" + account_iban + ", reference=" + reference + "]";
	}
	

	
}
