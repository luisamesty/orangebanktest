package com.orange.spring.model;

public class Payload {

	String reference;
	String channel;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Override
	public String toString() {
		return "Payload [reference=" + reference + ", channel=" + channel + "]";
	}
	
	

	
}
