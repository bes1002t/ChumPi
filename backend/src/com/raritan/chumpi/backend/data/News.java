package com.raritan.chumpi.backend.data;

public class News {

	private final int articleid;
	private final String url;
	private final String headline;
	private final String abstracttext;
	
	public News(int id, String url, String headline, String teaser) {
		this.articleid = id;
		this.url = url;
		this.headline = headline;
		this.abstracttext = teaser;
	}
	
	public String getUrl() {
		return url;
	}

	public String getHeadline() {
		return headline;
	}

	public String getAbstractText() {
		return abstracttext;
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return String.format("%s(articleid=%d, url=%s, headline=%s, abstracttext=%s)", className, articleid, url, headline, abstracttext);
	}
}
