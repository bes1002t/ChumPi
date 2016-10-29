package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.User;
import com.raritan.chumpi.backend.data.provider.UserRepository;
import com.raritan.chumpi.backend.exceptions.UserNotFoundException;

@Path("/user")
public class UserCtrl {

	private UserRepository repo = UserRepository.INSTANCE;

	@GET
	@Path("/get")
	public User getUser(@QueryParam("id") int id) {
		try {
			return repo.getUser(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/add")
	public Boolean addUser(@FormParam("name") String name, @FormParam("birthday") String birthday) {
		return false;
	}

	@POST
	@Path("/remove")
	public Boolean removeUser(@FormParam("id") int id) {
		return false;
	}

	@POST
	@Path("/edit")
	public Boolean editUser(@FormParam("name") String name, @FormParam("birthday") String birthday) {
		return false;
	}
}
