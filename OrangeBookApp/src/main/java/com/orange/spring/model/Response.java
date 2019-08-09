package com.orange.spring.model;

import java.math.BigDecimal;

public class Response {

	String rseference;
	BigDecimal rsamount;
	BigDecimal rsfee;
	String rsstatus;
	
	
	public Response() {
		super();
		this.rsamount= BigDecimal.ZERO;
		this.rsfee = BigDecimal.ZERO;
		this.rsstatus = "";
		this.rseference = "";

	}


	public String getRseference() {
		return rseference;
	}


	public void setRseference(String rseference) {
		this.rseference = rseference;
	}


	public BigDecimal getRsamount() {
		return rsamount;
	}


	public void setRsamount(BigDecimal rsamount) {
		this.rsamount = rsamount;
	}


	public BigDecimal getRsfee() {
		return rsfee;
	}


	public void setRsfee(BigDecimal rsfee) {
		this.rsfee = rsfee;
	}


	public String getRsstatus() {
		return rsstatus;
	}


	public void setRsstatus(String rsstatus) {
		this.rsstatus = rsstatus;
	}


	@Override
	public String toString() {
		return "Response [rseference=" + rseference + ", rsamount=" + rsamount + ", rsfee=" + rsfee + ", rsstatus="
				+ rsstatus + "]";
	}


	
}
