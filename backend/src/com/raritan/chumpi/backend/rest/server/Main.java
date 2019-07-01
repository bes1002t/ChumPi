package com.raritan.chumpi.backend.rest.server;

import com.raritan.chumpi.backend.perihperal_devices.CoffeeWatcher;

public class Main {
	public static void main(String[] args) {
		//CoffeeWatcher.getInstance();

		String configPath = System.getProperty("user.dir");
		RestApiServer server = new RestApiServer(new ServerConfig(configPath), new RestConfigurator());
		server.start();
	}
}
