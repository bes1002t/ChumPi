package com.raritan.chumpi.backend.rest.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ServerConfig {

	private final static String PROPS_FILE = "properties.cfg";

	private final static String BASE_DIR_PROP = "base_dir";
	private final static String WELCOME_PAGE = "welcome_page";
	private final static String ERROR_PAGE = "error_page";

	private String baseDir;
	private String errorPagePath;
	private String welcomePagePath;

	public ServerConfig(String path) {
		File configFile = new File(path + "/" + PROPS_FILE);

		if (!configFile.exists()) {
			throw new RuntimeException("No Property file found under " + path);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
			String currLine;

			while ((currLine = br.readLine()) != null) {
				String[] prop = currLine.split("=");
				String propName = prop[0].trim();
				String propVal = prop[1].replace("/\"", "");

				switch(propName) {
					case BASE_DIR_PROP: baseDir = propVal;
					break;
					case WELCOME_PAGE: welcomePagePath = propVal;
					break;
					case ERROR_PAGE: errorPagePath = propVal;
					break;
					default: throw new RuntimeException("Unknown Property: " + propName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBaseDir() {
		return baseDir;
	}

	public String getErrorPagePath() {
		return errorPagePath;
	}

	public String getWelcomePagePath() {
		return welcomePagePath;
	}

}
