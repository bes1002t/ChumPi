package com.raritan.chumpi.backend.perihperal_devices;

import java.util.Date;

import com.raritan.chumpi.backend.data.CoffeeRecipe;
import com.raritan.chumpi.backend.data.CoffeeRecipe.CoffeeStrength;
import com.raritan.chumpi.backend.data.provider.OrderRepository;

public class CoffeeWatcher implements ButtonHandler.Listener {

	private final ButtonHandler panel = new ButtonHandler();

	private boolean extraWhitener;
	private boolean extraSugar;
	private boolean large;
	private CoffeeStrength strength;

	// timeout for selected modifiers (large, extra sugar/whitener, strength)
	private static final int MODIFIER_TIMEOUT = 30; // seconds;
	private Date lastEvent = null;

	// ignore new button presses while brewing
	private static final int BREW_DURATION = 30; // seconds;
	private Date lastOrder = null;

	private static CoffeeWatcher instance;

	public static CoffeeWatcher getInstance() {
		if (instance == null) instance = new CoffeeWatcher();
		return instance;
	}

	private CoffeeWatcher() {
		this.panel.setListener(this);
		setDefaults();
	}

	private void setDefaults() {
		extraWhitener = false;
		extraSugar = false;
		large = false;
		strength = CoffeeStrength.MEDIUM;
	}

	@Override
	public void onButtonEvent(int button, boolean pressed) {
		Date now = new Date();
		if (lastOrder != null) {
			long timeSinceLastOrder = (now.getTime() - lastOrder.getTime()) / 1000;
			if (timeSinceLastOrder >= 0 && timeSinceLastOrder < BREW_DURATION) {
				return; // busy
			}
		}
		if (lastEvent != null) {
			long timeSinceLastEvent = (now.getTime() - lastEvent.getTime()) / 1000;
			if (timeSinceLastEvent < 0 || timeSinceLastEvent > MODIFIER_TIMEOUT) {
				setDefaults();
			}
		}
		lastEvent = now;

		switch (button) {
			case 0:
				extraWhitener = !extraWhitener;
				break;
			case 1:
				extraSugar = !extraSugar;
				break;
			case 2:
				large = !large;
				break;
			case 3:
				strength = strength == CoffeeStrength.MEDIUM ? CoffeeStrength.STRONG
				         : strength == CoffeeStrength.STRONG ? CoffeeStrength.WEAK
				         : CoffeeStrength.MEDIUM;
				break;
			default:
				addOrder(button);
		}
	}

	private void addOrder(int index) {
		if (index < 4 || index > 13) return;
		CoffeeRecipe recipe = new CoffeeRecipe(extraWhitener, extraSugar, large, strength, index);
		OrderRepository.INSTANCE.createOrder(null, recipe);
		lastOrder = new Date();
	}

}
