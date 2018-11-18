package model;

public class Card {
	private char cardType;
	private String cardName;

	public char getCartType() {
		return cardType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCartType(char cartType) {
		this.cardType = cartType;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Card(char t, String n) {
		this.cardType = t;
		this.cardName = n;
	}

}