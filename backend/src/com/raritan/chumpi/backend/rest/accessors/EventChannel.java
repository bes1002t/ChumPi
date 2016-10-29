package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

@Path("/sse")
public class EventChannel {

	private static EventChannel instance;

	public static EventChannel getInstance() {
		if (instance == null) instance = new EventChannel();
		return instance;
	}

	private SseBroadcaster broadcaster;

	private EventChannel() {
		broadcaster = new SseBroadcaster();
	}

	@GET
	@Path("/listen")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput listenToBroadcast() {
		final EventOutput eventOutput = new EventOutput();
		broadcaster.add(eventOutput);

		return eventOutput;
	}

	public void send(String data) {
		OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
		OutboundEvent event = eventBuilder.name("message")
				.mediaType(MediaType.TEXT_PLAIN_TYPE)
				.data(String.class, data)
				.build();

		broadcaster.broadcast(event);
	}
}
