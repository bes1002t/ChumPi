package com.raritan.chumpi.backend.rest.server;

import org.glassfish.jersey.server.ResourceConfig;

import com.raritan.chumpi.backend.rest.accessors.MotdCtrl;

public class RestConfigurator extends ResourceConfig {

	private MotdCtrl motdCtrl = new MotdCtrl();

	public RestConfigurator() {
		register(GsonWriter.class);
		register(GsonReader.class);
		register(motdCtrl);
	}
}
