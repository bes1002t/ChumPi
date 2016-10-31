package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.raritan.chumpi.backend.data.MessageOfTheDay;
import com.raritan.chumpi.backend.data.provider.MotdStore;

@Path("/motd")
public class MotdCtrl {

	@GET
	@Path("/get")
	public MessageOfTheDay getMotd() {
		return MotdStore.INSTANCE.get();
	}

	@POST
	@Path("/set")
	public void setMotd(@FormParam("headline") String headline,
	                    @FormParam("message") String message)
	{
		MotdStore.INSTANCE.set(new MessageOfTheDay(headline, message));
	}

	@POST
	@Path("/clear")
	public void clearMotd() {
		MotdStore.INSTANCE.set(null);
	}

}
