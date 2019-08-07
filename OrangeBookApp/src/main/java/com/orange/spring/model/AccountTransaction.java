package com.orange.spring.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity(name = "AccountTransaction")
@Table(name = "accounttransaction")
public class AccountTransaction {
	@OrderBy 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	String account_iban;
	String treference;
	String trfecha;
	BigDecimal tramount;
	BigDecimal trfee;
	String trdescription;
	String trstatus;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAccount_iban() {
		return account_iban;
	}


	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}


	public String getTReference() {
		return treference;
	}


	public void setTReference(String treference) {
		this.treference = treference;
	}


	public String getTrFecha() {
		return trfecha;
	}


	public void setTrFecha(String trfecha) {
		this.trfecha = trfecha;
	}


	public BigDecimal getTrAmount() {
		return tramount;
	}


	public void setTrAmount(BigDecimal tramount) {
		this.tramount = tramount;
	}


	public BigDecimal getTrFee() {
		return trfee;
	}


	public void setTrFee(BigDecimal fee) {
		this.trfee = fee;
	}


	public String getTrdescription() {
		return trdescription;
	}


	public void setTrdescription(String trdescription) {
		this.trdescription = trdescription;
	}


	public String getTrstatus() {
		return trstatus;
	}


	public void setTrstatus(String trstatus) {
		this.trstatus = trstatus;
	}


	@Override
	public String toString() {
		return "AccountTransaction [id=" + id + ", account_iban=" + account_iban + ", reference=" + treference + ", trfecha="
				+ trfecha + ", tramount=" + tramount + ", trfee=" + trfee + ", trdescription=" + trdescription + ", trstatus="
				+ trstatus + "]";
	}

}
