package com.orange.spring.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AccountTransaction")
@Table(name = "accounttransaction")
public class AccountTransaction {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long id;
		Long account_id;
		String reference;
		String account_iban;
		Timestamp date;
		BigDecimal amount;
		BigDecimal fee;
		String description;
		String status;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getAccount_id() {
			return account_id;
		}
		public void setAccount_id(Long account_id) {
			this.account_id = account_id;
		}
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
		public Timestamp getDate() {
			return date;
		}
		public void setDate(Timestamp date) {
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "AccountTransaction [id=" + id + ", account_id=" + account_id + ", reference=" + reference
					+ ", account_iban=" + account_iban + ", date=" + date + ", amount=" + amount + ", fee=" + fee
					+ ", description=" + description + ", status=" + status + "]";
		}

}
