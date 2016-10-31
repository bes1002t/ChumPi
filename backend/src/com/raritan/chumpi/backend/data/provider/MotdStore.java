package com.raritan.chumpi.backend.data.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.raritan.chumpi.backend.data.MessageOfTheDay;
import com.raritan.chumpi.backend.rest.server.GsonCreator;

public class MotdStore extends GsonCreator {

	public static MotdStore INSTANCE = new MotdStore();
	private static final String FILENAME = "datastore/motd.json";

	private MotdStore() {
		createGson();
	}

	public MessageOfTheDay get() {
		File file = new File(FILENAME);
		try {
			String json = FileUtils.readFileToString(file);
			return getGson().fromJson(json, MessageOfTheDay.class);
		} catch (FileNotFoundException e) {
			// OK
		} catch (IOException e) {
			System.err.println("Could not read MOTD!");
			e.printStackTrace();
		}
		return null;
	}

	public void set(MessageOfTheDay motd) {
		File file = new File(FILENAME);
		String json = getGson().toJson(motd);
		try {
			FileUtils.writeStringToFile(file, json);
		} catch (IOException e) {
			System.err.println("Could not persist MOTD!");
			e.printStackTrace();
		}
	}

}
