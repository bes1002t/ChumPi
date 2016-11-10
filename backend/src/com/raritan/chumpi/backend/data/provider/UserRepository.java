package com.raritan.chumpi.backend.data.provider;

import java.time.LocalDate;

import com.raritan.chumpi.backend.data.User;
import com.raritan.chumpi.backend.exceptions.UserNotFoundException;

public class UserRepository extends AbstractRepository<User> {
	
	public final static UserRepository INSTANCE = new UserRepository();
	
	private UserRepository() {
		super();
	}
	
	public User getUserByName(String name) throws UserNotFoundException {
		for (User u : cache) {
			if (u.getName().equals(name))
				return u;
		}
		throw new UserNotFoundException();
	}
	
	public User getUser(int id) throws UserNotFoundException {
		for (User u : cache) {
			if (u.getId() == id)
				return u;
		}
		throw new UserNotFoundException();
	}
	
	public User createNewUser(String name, LocalDate birthDay) {
		User u = new User(name, birthDay);
		cache.add(u);
		persist(u);
		return u;
	}
	
	public User editUser(int id, User p) {
		// TODO implement edit user
		throw new UnsupportedOperationException("Edit user not yet implemented");
	}

	public Boolean removeUser(int id) {
		for (User u : cache) {
			if (u.getId() == id) {
				cache.remove(u);
				return true;
			}
		}

		return false;
	}

	@Override
	protected Class<User> getRepoType() {
		return User.class;
	}

}
