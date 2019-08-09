package com.orange.spring.model;

public class Payload {

	String plreference;
	String plchannel;
	
	
	public Payload() {
		this.plchannel="";
		this.plreference="";
	}
	
	public String getPlreference() {
		return plreference;
	}
	public void setPlreference(String plreference) {
		this.plreference = plreference;
	}
	public String getPlchannel() {
		return plchannel;
	}
	public void setPlchannel(String plchannel) {
		this.plchannel = plchannel;
	}
	@Override
	public String toString() {
		return "Payload [plreference=" + plreference + ", plchannel=" + plchannel + "]";
	}
	
	
}
