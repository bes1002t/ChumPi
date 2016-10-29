package com.raritan.chumpi.backend.rest.accessors;

import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.News;
import com.raritan.chumpi.backend.data.provider.NewsProvider;

/**
 * Get the API-Keys from here: http://api.golem.de/apimanage/index.php. API
 * reference can be found here: http://api.golem.de/doc/reference.php. See also
 * {@link WeatherCtrl}
 *
 */
@Path("/news-golem")
public class GolemNewsCtrl {

	private String apiKey = "fbb062cb080b7e30e4275c40c4693317";

	@GET
	@Path("/random")
	public News getRandom() {
		return new NewsProvider().parse(new RestApiCaller()
				.call("http://api.golem.de/api/article/meta/"
						+ new Random(System.currentTimeMillis())
								.nextInt(120000) + "/?key=" + apiKey));
	}

	@GET
	@Path("/list")
	public List<News> getNewsList(@QueryParam("amount") int amount) {
		if (amount == 0)
			amount = 10; // set to default value
		List<News> list = new NewsProvider().parseMany(new RestApiCaller()
				.call("http://api.golem.de/api/article/latest/" + amount
						+ "/?key=" + apiKey + "&format=json"));
		return list;
	}

	@GET
	@Path("/news")
	public News getNewsByIndex(@QueryParam("index") int index) {
		return null;
	}
}
