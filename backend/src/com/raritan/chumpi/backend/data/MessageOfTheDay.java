package com.raritan.chumpi.backend.data;

public class MessageOfTheDay {

	private final String headline;
	private final String message;
	
	public MessageOfTheDay(String headline, String msg) {
		this.headline = headline;
		message = msg;
	}
	
	public String getHeadline() {
		return headline;
	}

	public String getMessage() {
		return message;
	}
	
}
