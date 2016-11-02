package com.raritan.chumpi.backend.rest.accessors;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.provider.CalEvent;
import com.raritan.chumpi.backend.data.provider.Calendar;

@Path("/cal")
public class CalendarCtrl {

	private Calendar cal = new Calendar();

	@GET
	@Path("/events")
	public List<CalEvent> getCalEvents(@QueryParam("from") String from, @QueryParam("to") String to) {
		try {
			return cal.getCalEvents(null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
