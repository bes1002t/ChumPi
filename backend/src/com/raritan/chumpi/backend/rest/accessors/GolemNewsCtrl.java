package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.News;

@Path("/news-golem")
public class GolemNewsCtrl {

	private URI baseUri;

	@GET
	@Path("/random")
	public News getRandom() {
		return null;
	}

	@GET
	@Path("/list")
	public List<String> getNewsList() {
		return null;
	}

	@GET
	@Path("/news")
	public News getNewsByIndex(@QueryParam("index") int index) {
		return null;
	}
}
