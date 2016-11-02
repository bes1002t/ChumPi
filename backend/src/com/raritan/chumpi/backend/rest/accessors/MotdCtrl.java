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
		try {
			return MotdStore.INSTANCE.get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/set")
	public void setMotd(@FormParam("headline") String headline,
	                    @FormParam("message") String message)
	{
		try {
			MotdStore.INSTANCE.set(new MessageOfTheDay(headline, message));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/clear")
	public void clearMotd() {
		try {
			MotdStore.INSTANCE.set(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
