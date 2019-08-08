package com.orange.spring.model;

import java.math.BigDecimal;
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
	String trchannel;
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
	public String getTreference() {
		return treference;
	}
	public void setTreference(String treference) {
		this.treference = treference;
	}
	public String getTrfecha() {
		return trfecha;
	}
	public void setTrfecha(String trfecha) {
		this.trfecha = trfecha;
	}
	public BigDecimal getTramount() {
		return tramount;
	}
	public void setTramount(BigDecimal tramount) {
		this.tramount = tramount;
	}
	public BigDecimal getTrfee() {
		return trfee;
	}
	public void setTrfee(BigDecimal trfee) {
		this.trfee = trfee;
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
	public String getTrchannel() {
		return trchannel;
	}
	public void setTrchannel(String trchannel) {
		this.trchannel = trchannel;
	}
	@Override
	public String toString() {
		return "AccountTransaction [id=" + id + ", account_iban=" + account_iban + ", treference=" + treference
				+ ", trfecha=" + trfecha + ", tramount=" + tramount + ", trfee=" + trfee + ", trdescription="
				+ trdescription + ", trstatus=" + trstatus + ", trchannel=" + trchannel + "]";
	}
	


}
