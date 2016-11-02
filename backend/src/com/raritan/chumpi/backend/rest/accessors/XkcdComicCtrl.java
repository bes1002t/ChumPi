package com.raritan.chumpi.backend.rest.accessors;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Path("/xkcd")
public class XkcdComicCtrl {
	@GET
	@Path("/random")
	public String getRandom() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/latest")
	public String getLatest() {
		try {
			Document html = Jsoup.connect("http://www.xkcd.com").get();
			Element comicdiv = html.select("#comic").first();
			Element a = comicdiv.child(0);
			String u = "http:" + a.attr("src");
			return u;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/image")
	public String getImage(@QueryParam("date") String date) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}