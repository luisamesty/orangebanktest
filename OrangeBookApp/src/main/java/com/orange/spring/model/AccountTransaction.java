package com.orange.spring.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AccountTransaction")
@Table(name = "accounttransaction")
public class AccountTransaction {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		Long id;
		String account_iban;
		String treference;
		String trdate;
		BigDecimal amount;
		BigDecimal fee;
		String trdescription;
		String trstatus;

		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getAccount_iban() {
			return account_iban;
		}


		public void setAccount_iban(String account_iban) {
			this.account_iban = account_iban;
		}


		public String getReference() {
			return treference;
		}


		public void setReference(String treference) {
			this.treference = treference;
		}


		public String getDate() {
			return trdate;
		}


		public void setDate(String trdate) {
			this.trdate = trdate;
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
			return "AccountTransaction [id=" + id + ", account_iban=" + account_iban + ", reference=" + treference + ", trdate="
					+ trdate + ", amount=" + amount + ", fee=" + fee + ", trdescription=" + trdescription + ", trstatus="
					+ trstatus + "]";
		}

}
