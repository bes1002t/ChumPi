package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.News;

@Path("/news-heise")
public class HeiseNewsCtrl {

	private URI baseUri;

	@GET
	@Path("/random")
	public News getRandom() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/list")
	public List<String> getNewsList() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/news")
	public News getNewsByIndex(@QueryParam("index") int index) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
