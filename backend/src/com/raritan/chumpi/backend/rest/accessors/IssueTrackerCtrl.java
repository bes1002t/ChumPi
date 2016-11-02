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
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/last-tickets")
	public Boolean getLastTickets(@QueryParam("count") int count) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/ticket")
	public Boolean getTicketById(@QueryParam("id") int id) {
		try {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
