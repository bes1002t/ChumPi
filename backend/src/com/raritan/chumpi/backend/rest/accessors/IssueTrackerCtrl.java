package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
	public Boolean getLastTickets(@FormParam("count") int count) {

		return true;
	}

	@GET
	@Path("/ticket-by-id")
	public Boolean getTicketById(@FormParam("id") int id) {

		return true;
	}
}
