package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.raritan.chumpi.backend.data.MessageOfTheDay;

@Path("/motd")
public class MotdCtrl {

	private MessageOfTheDay motd;

	public MotdCtrl() {
		String headline = "Raritan Hackaton!";
		String msg = "lorem ipsum";

		motd = new MessageOfTheDay(headline, msg);
	}

	@GET
	@Path("/get")
	public MessageOfTheDay getMotd() {
		return motd;
	}
}
