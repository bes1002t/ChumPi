package com.raritan.chumpi.backend.rest.server;

public class Main {
	public static void main(String[] args) {
		String configPath = System.getProperty("user.dir");

		RestApiServer server = new RestApiServer(new ServerConfig(configPath), new RestConfigurator());
		server.start();
	}
}
