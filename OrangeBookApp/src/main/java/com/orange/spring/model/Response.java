package com.orange.spring.model;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

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

	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseReferenceOnly( Response response) {
		

		String message;
		JSONObject json = new JSONObject();
		json.put("reference", response.getRseference());
		json.put("status", response.getRsstatus());
		// message = json.toString();
		// System.out.println(message);
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseReferenceAmount( Response response) {
		

		String message;
		JSONObject json = new JSONObject();
		json.put("reference", response.getRseference());
		json.put("status", response.getRsstatus());
		json.put("amount", response.getRsamount());
	 	if (response.getRsfee().compareTo(BigDecimal.ZERO) > 0) {
	 		json.put("amount", response.getRsfee());
	 	}
		// message = json.toString();
		// System.out.println(message);
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	
		Response test = new Response();
		test.setRseference("XXXXXXXXX");
		test.setRsstatus("INVALID");
		test.setRsamount(BigDecimal.ZERO);
		test.setRsfee(BigDecimal.ZERO);
		
		JSONObject json = new JSONObject();
		json.put("reference", test.getRseference());
		json.put("status", test.getRsstatus());
		String message = json.toString();
		System.out.println(message);
	
	}
}
