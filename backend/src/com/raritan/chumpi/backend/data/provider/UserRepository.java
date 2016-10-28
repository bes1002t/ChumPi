package com.raritan.chumpi.backend.data.provider;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.google.gson.JsonSyntaxException;
import com.raritan.chumpi.backend.data.User;
import com.raritan.chumpi.backend.exceptions.UserNotFoundException;
import com.raritan.chumpi.backend.rest.server.GsonCreator;

public class UserRepository extends GsonCreator {
	
	public final static UserRepository INSTANCE = new UserRepository();
	
	private final File dataStoreLocation = new File("datastore/users/");
	private final String userExtension = ".json";
	private final Set<User> userCache = new HashSet<>();
	
	private UserRepository() {
		createGson();
	}
	
	// only for testing
	public void printContent() {
		for (User u : userCache) {
			System.out.println(u);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void reloadUsers() {
		System.out.println("Reload user profiles");
		userCache.clear();
		for (File f : (List<File>) FileUtils.listFiles(dataStoreLocation, 
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
			try {
				User u = getGson().fromJson(FileUtils.readFileToString(f), User.class);
				userCache.add(u);
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
	
	public void persistUsers() {
		for (User u : userCache) {
			persistUser(u);
		}
	}
	
	private void persistUser(User u) {
		File uFile = new File(dataStoreLocation + "/" + u.toString().replaceAll("\\s", "_") + userExtension);
		String uJson = getGson().toJson(u);
		try {
			FileUtils.writeStringToFile(uFile, uJson);
			System.out.println("persist user successfully to " + uFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Could not persist user " + u.toString() + " to data store!");
			e.printStackTrace();
		}
	}

	public User getUserByName(String name) throws UserNotFoundException {
		for (User u : userCache) {
			if (u.getName().equals(name))
				return u;
		}
		throw new UserNotFoundException();
	}
	
	public User getUser(int id) throws UserNotFoundException {
		for (User u : userCache) {
			if (u.getId() == id)
				return u;
		}
		throw new UserNotFoundException();
	}
	
	public User createNewUser(String name, LocalDate birthDay) {
		User u = new User(name, birthDay);
		userCache.add(u);
		persistUser(u);
		return u;
	}
	
	public User editUser(int id, User p) {
		// TODO implement edit user
		throw new UnsupportedOperationException("Edit user not yet implemented");
	}

}
