package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.UserSettings;
import com.raritan.chumpi.backend.data.provider.UserRepository;
import com.raritan.chumpi.backend.exceptions.UserNotFoundException;

@Path("user-settings")
public class UserSettingsCtrl {

	private UserRepository repo = UserRepository.INSTANCE;

	@GET
	@Path("/get")
	public UserSettings getUserSettings(@QueryParam("userid") int userId) {
		try {
			return repo.getUser(userId).getUserSettings();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/set")
	public Boolean setUserSettings(@QueryParam("userid") int userId) {
		try {
			repo.getUser(userId).setUserSettings(null);
			return true;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
