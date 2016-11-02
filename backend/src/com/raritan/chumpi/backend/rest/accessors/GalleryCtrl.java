package com.raritan.chumpi.backend.rest.accessors;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.Gallery;
import com.raritan.chumpi.backend.data.Image;

@Path("/gallery")
public class GalleryCtrl {

	private Gallery gallery;

	@GET
	@Path("/image")
	public Image getImageByIndex(@QueryParam("index") int index) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/overview")
	public List<String> getGalleryOverview() {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
