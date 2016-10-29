package com.raritan.chumpi.backend.rest.accessors;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.raritan.chumpi.backend.data.provider.CalEvent;
import com.raritan.chumpi.backend.data.provider.Calendar;

@Path("/cal")
public class CalendarCtrl {

	private Calendar cal = new Calendar();

	@GET
	@Path("/events")
	public List<CalEvent> getCalEvents(@FormParam("from") Date from, @FormParam("to") Date to) {
		return cal.getCalEvents(from, to);
	}
}
