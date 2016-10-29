package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/issue-tracker")
public class IssueTrackerCtrl {

	private URI baseUri;

	@GET
	@Path("/last-ticket")
	public Boolean getLastTicket() {

		return true;
	}

	@GET
	@Path("/last-tickets")
	public Boolean getLastTickets(@QueryParam("count") int count) {

		return true;
	}

	@GET
	@Path("/ticket")
	public Boolean getTicketById(@QueryParam("id") int id) {

		return true;
	}
}
