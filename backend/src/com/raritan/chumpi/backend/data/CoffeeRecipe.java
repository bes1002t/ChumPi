package com.raritan.chumpi.backend.data;

public class CoffeeRecipe {
	
	public static final CoffeeRecipe DEFAULT = new CoffeeRecipe(0, 0, true, true, 9);
	/** amount of coffee whitener */
	private final int amountWhite;
	/** amount of sugar */
	private final int amountSugar;
	/** it's a mug or it's a cup */
	private final boolean bigCup;
	/** strong coffee goes here */
	private final boolean strong;
	/** program selector index. Range is [9, 14] */
	private final int selector;
	
	public CoffeeRecipe(int amountWhite, int amountSugar, boolean bigCup,
			boolean strong, int selector) {
		this.amountWhite = amountWhite;
		this.amountSugar = amountSugar;
		this.bigCup = bigCup;
		this.strong = strong;
		if (9 <= selector && 14 >= selector)
			this.selector = selector;
		else
			this.selector = 9;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Whitener: " + amountWhite);
		sb.append("\nSugar: " + amountSugar);
		sb.append("\n" + (bigCup?"Big":"Small"));
		if (strong) sb.append("\nStrong coffee");
		sb.append("\nSelected Button: " + selector);
		return sb.toString();
	}

}
