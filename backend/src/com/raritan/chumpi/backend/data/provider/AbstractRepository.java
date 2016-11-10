package com.raritan.chumpi.backend.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.google.gson.JsonSyntaxException;
import com.raritan.chumpi.backend.rest.serialization.GsonCreator;

public abstract class AbstractRepository<T> extends GsonCreator {
	
	protected final Set<T> cache = new HashSet<>();
	private final File dataStoreLocation;
	private final String fileExtension = ".json";
	
	abstract protected Class<T> getRepoType();
	
	protected AbstractRepository() {
		createGson();
		dataStoreLocation = new File("datastore/" + getRepoType().getSimpleName() + "s/");
	}

	// only for testing
	public void printContent() {
		for (T t : cache) {
			System.out.println(t);
		}
	}

	@SuppressWarnings("unchecked")
	public void reloadCache() {
		if (dataStoreLocation.exists()) {
			System.out.println("Reload user profiles");
			cache.clear();

			for (File f : (List<File>) FileUtils.listFiles(dataStoreLocation, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
				try {
					T u = getGson().fromJson(FileUtils.readFileToString(f), getRepoType());
					cache.add(u);
					System.out.println("added " + u);
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void persistCache() {
		for (T u : cache) {
			persist(u);
		}
	}

	protected void persist(T u) {
		File uFile = new File(dataStoreLocation + "/" + u.toString().replaceAll("\\s", "_") + fileExtension);
		String uJson = getGson().toJson(u);
		try {
			FileUtils.writeStringToFile(uFile, uJson);
			System.out.println("persist user successfully to " + uFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Could not persist user " + u.toString() + " to data store!");
			e.printStackTrace();
		}
	}

	public Set<T> getAllInstances() {
		return Collections.unmodifiableSet(cache);
	}
}
