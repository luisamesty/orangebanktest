package com.orange.spring.model;

import java.math.BigDecimal;

public class PayloadTransaction {

	String reference;
	String account_iban;
	String date;
	BigDecimal amount;
	BigDecimal fee;
	String description;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getAccount_iban() {
		return account_iban;
	}
	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "PayloadTransaction [reference=" + reference + ", account_iban=" + account_iban + ", date=" + date
				+ ", amount=" + amount + ", fee=" + fee + ", description=" + description + "]";
	}

	
}
