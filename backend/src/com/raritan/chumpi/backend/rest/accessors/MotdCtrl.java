package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.Path;

import com.raritan.chumpi.backend.data.MessageOfTheDay;

public class MotdCtrl {

	private MessageOfTheDay motd;

	public MotdCtrl() {
		String headline = "Raritan Hackaton!";
		String msg = "lorem ipsum";

		motd = new MessageOfTheDay(headline, msg);
	}

	@Path("/motd")
	public MessageOfTheDay getMotd() {
		return motd;
	}
}
