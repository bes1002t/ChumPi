package com.raritan.chumpi.backend.data;

import java.io.File;
import java.time.LocalDate;

import com.raritan.chumpi.backend.data.provider.OrderRepository;

public class User {

	private static int latestId = 0;
	private final int userId;
	private String name;
	private final LocalDate birthDay;
	private UserSettings settings;
	private File cvIdImage = null;

	public User(String name, LocalDate birthDay, UserSettings settings) {
		this(latestId++, name, birthDay, settings);
	}

	public User(int id, String name, LocalDate birthDay, UserSettings settings) {
		this.name = name;
		this.birthDay = birthDay;
		this.settings = settings;
		userId = id;
	}

	public User(String name, LocalDate birthDay) {
		this(name, birthDay, UserSettings.initialSetting());
	}

	public UserSettings getUserSettings() {
		return settings;
	}

	public void setUserSettings(UserSettings settings) {
		this.settings = settings;
	}

	public void addCoffeeOrder(CoffeeRecipe r) {
		OrderRepository.INSTANCE.createOrder(this, r);
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthDay;
	}

	public int getId() {
		return userId;
	}

	public void setCvId(File f) {
		// TODO check passed file
		cvIdImage = f;
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof User))
			return false;
		User other = (User) o;
		return other.name.equals(name) &&
				other.birthDay.equals(birthDay);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + birthDay.hashCode();
		return result;
	}

	public String getFullProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("\nID: " + userId);
		sb.append("\nBirthday: " + birthDay);
		sb.append("\nSettings: " + settings.toString());
		return sb.toString();
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return String.format("%s(userId=%d, name=%s, birthDay=%s, settings=%s)", className, userId, name, birthDay.toString(), settings);
	}
}
