package com.raritan.chumpi.backend.data;

public class CoffeeRecipe {

	public enum CoffeeStrength {
		WEAK, MEDIUM, STRONG
	}

	public static final CoffeeRecipe DEFAULT = new CoffeeRecipe(false, false, true, CoffeeStrength.MEDIUM, 9);
	/** whitener flag */
	private final boolean whitener;
	/** do you want sugar */
	private final boolean sugar;
	/** it's a mug or it's a cup */
	private final boolean bigCup;
	/** strong coffee goes here */
	private final CoffeeStrength strength;
	/** program selector index. Range is [5, 14] */
	private final int selector;

	public CoffeeRecipe(boolean whitener, boolean sugar, boolean bigCup,
			CoffeeStrength strength, int selector) {
		this.whitener = whitener;
		this.sugar = sugar;
		this.bigCup = bigCup;
		this.strength = strength;
		if (5 <= selector && 14 >= selector)
			this.selector = selector;
		else
			this.selector = 9;
	}

	public int getSelector() {
		return selector;
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return String.format("%s(whitener=%b, sugar=%b, bigCup=%b, strength=%s, selector=%d)", className, whitener, sugar, bigCup, strength, selector);
	}
}
