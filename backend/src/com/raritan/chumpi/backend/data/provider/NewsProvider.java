package com.raritan.chumpi.backend.data.provider;

import java.util.ArrayList;
import java.util.List;

import com.raritan.chumpi.backend.data.News;
import com.raritan.chumpi.backend.rest.server.GsonCreator;

public class NewsProvider extends GsonCreator {

	public NewsProvider() {
		createGson();
	}
	
	public News parse(String json) {
		return getGson().fromJson(json, SingleNews.class).data;
	}

	public List<News> parseMany(String json) {
		return getGson().fromJson(json, NewsAggreation.class).data;
	}
	
	class SingleNews {
		News data;
	}

	class NewsAggreation {
		List<News> data = new ArrayList<>();
	}

}
