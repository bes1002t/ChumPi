package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.glassfish.jersey.server.Uri;

import com.raritan.chumpi.backend.data.Image;

@Path("/dilbert")
public class DilbertComicCtrl {

	private Uri baseUri;

	@GET
	@Path("/random")
	public Image getRandom() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/latest")
	public Image getLatest() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/image")
	public Image getImage(@QueryParam("date") String date) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
