package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.CoffeeStatistic;

@Path("/coffee")
public class CoffeeStatsCtrl {

	@GET
	@Path("/stat")
	public CoffeeStatistic getAmountForUser(@QueryParam("userId")  int userId, @QueryParam("from") String from, @QueryParam("to") String to) {
		return null;
	}
}
