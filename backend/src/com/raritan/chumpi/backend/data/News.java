package com.raritan.chumpi.backend.data;

public class News {

	private final String origin;
	private final String headline;
	private final String teaser;
	
	public News(String origin, String headline, String teaser) {
		this.origin = origin;
		this.headline = headline;
		this.teaser = teaser;
	}
	
	public String getOrigin() {
		return origin;
	}

	public String getHeadline() {
		return headline;
	}

	public String getTeaser() {
		return teaser;
	}

}
