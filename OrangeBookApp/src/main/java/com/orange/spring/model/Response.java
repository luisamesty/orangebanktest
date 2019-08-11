package com.orange.spring.model;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public class Response {

	String rseference;
	BigDecimal rsamount;
	BigDecimal rsfee;
	String rsstatus;
	String rsfecha;
	
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


	public String getRsfecha() {
		return rsfecha;
	}


	public void setRsfecha(String rsfecha) {
		this.rsfecha = rsfecha;
	}


	@Override
	public String toString() {
		return "Response [rseference=" + rseference + ", rsamount=" + rsamount + ", rsfee=" + rsfee + ", rsstatus="
				+ rsstatus + ", rsfecha=" + rsfecha + "]";
	}


	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseInvalid( Response response) {
		JSONObject json = new JSONObject();
		json.put("reference", response.getRseference());
		json.put("status", "INVALID");
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseReferenceOnly( Response response) {
		

		JSONObject json = new JSONObject();
		json.put("reference", response.getRseference());
		json.put("status", response.getRsstatus());
		// message = json.toString();
		// System.out.println(message);
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseCLIENT( Response response, String channel, int dateCondition) {
		
		JSONObject json = new JSONObject();
		BigDecimal netAmount = BigDecimal.ZERO;
		json.put("reference", response.getRseference());
		if (response.getRsamount().compareTo(BigDecimal.ZERO) >=0 ) {
			netAmount = response.getRsamount();
			netAmount = netAmount.subtract(response.getRsfee());
		} else {
			netAmount = response.getRsamount().negate();
			netAmount = netAmount.add(response.getRsfee());
		}
		// Verify Date Condition
		if (dateCondition < 0) {
			json.put("status","SETTLED");
		} else if ( dateCondition == 0 ) {
			json.put("status","PENDING");
		}  else if ( dateCondition > 0 ) {
			json.put("status","FUTURE");
		}
		json.put("amount", netAmount);
		// System.out.println(json.toString());
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseATM( Response response, String channel, int dateCondition) {
		
		JSONObject json = new JSONObject();
		BigDecimal netAmount = BigDecimal.ZERO;
		json.put("reference", response.getRseference());
		if (response.getRsamount().compareTo(BigDecimal.ZERO) >=0 ) {
			netAmount = response.getRsamount();
			netAmount = netAmount.subtract(response.getRsfee());
		} else {
			netAmount = response.getRsamount().negate();
			netAmount = netAmount.add(response.getRsfee());
		}
		// 
		json.put("reference", response.getRseference());
		if (dateCondition < 0) {
			json.put("status","SETTLED");
		} else if ( dateCondition == 0 ) {
			json.put("status","PENDING");
		}  else if ( dateCondition > 0 ) {
			json.put("status","PENDING");
		}
		json.put("amount", netAmount);
		// System.out.println(json.toString());
		return json;
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject  retJsonResponseINTERNAL( Response response, String channel, int dateCondition) {
		
		JSONObject json = new JSONObject();
		BigDecimal netAmount = BigDecimal.ZERO;
		if (response.getRsamount().compareTo(BigDecimal.ZERO) >=0 ) {
			netAmount = response.getRsamount();
		} else {
			netAmount = response.getRsamount().negate();
		}
		json.put("reference", response.getRseference());
		if (dateCondition < 0) {
			json.put("status","SETTLED");
		} else if ( dateCondition == 0 ) {
			json.put("status","PENDING");
		}  else if ( dateCondition > 0 ) {
			json.put("status","FUTURE");
		}
		json.put("amount", netAmount);
	 	json.put("fee", response.getRsfee());
		// System.out.println(json.toString());
		return json;
		
	}

//	@SuppressWarnings("unchecked")
//	public JSONObject  retJsonResponseReferenceAmount( Response response, String channel) {
//		
//
//		JSONObject json = new JSONObject();
//		json.put("reference", response.getRseference());
//		switch (channel) {
//			case "ALL" :
//			 	json.put("status","SETTLED");
//				json.put("amount", response.getRsamount());
//			 	json.put("fee", response.getRsfee());
//				break;		
//			case "CLIENT" :
//			 	json.put("status","SETTLED");
//				json.put("amount", response.getRsamount());
//			 	json.put("fee", response.getRsfee());
//				break;
//			case "ATM" :
//			 	json.put("status","SETTLED");
//				json.put("amount", response.getRsamount());
//			 	json.put("fee", response.getRsfee());
//				break;
//			case "INTERNAL" :
//			 	json.put("status","SETTLED");
//				json.put("amount", response.getRsamount());
//			 	json.put("fee", response.getRsfee());
//				break;
//		}
//		// System.out.println(json.toString());
//		return json;
//		
//	}
//	
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
