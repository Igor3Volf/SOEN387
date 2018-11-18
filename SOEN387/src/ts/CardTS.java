package ts;

import java.util.ArrayList;
import java.util.List;

import technical_service.CardRDG;
import model.Card;

public class CardTS {
	/* 0 - cardId
	 * 1 - cardType
	 * 2 - cardName
	 * 3 - deckId
	 * 4 - handId
	 * 5 - benchId
	 * */
	private final static String[]FIELD_NAME={"cardId","cardType","cardName","deckId","handId","benchId"};

	private int cardId;
	private char cardType;
	private String cardName;
	private int deckId;
	private int handId;
	private int benchId;
	
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getHandId() {
		return handId;
	}

	public int getBenchId() {
		return benchId;
	}

	public void setHandId(int handId) {
		this.handId = handId;
	}

	public void setBenchId(int benchId) {
		this.benchId = benchId;
	}
	
	public int getDeckId() {
		return deckId;
	}
	public int getCardId() {
		return cardId;
	}

	public char getCardType() {
		return cardType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardType(char cardType) {
		this.cardType = cardType;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	
	}
	
	public CardTS(int c, char ct, String cn) {
		this.cardId = c;
		this.cardType =ct;
		this.cardName = cn;
		this.deckId = 0;
		this.handId =0;
		this.benchId = 0;
	}
	public CardTS(int c, char ct, String cn,int dId) {
		this.cardId = c;
		this.cardType =ct;
		this.cardName = cn;
		this.deckId = dId;
		this.handId =0;
		this.benchId = 0;
	}
	public CardTS(int id, char ct, String name,int dId,int hId,int bId) {
		this.cardId = id;
		this.cardType = ct;
		this.cardName = name;
		this.deckId = dId;
		this.handId =hId;
		this.benchId = bId;
	}	
	public CardTS() {
		this.cardId = 0;
		this.cardType ='\0';
		this.cardName = "";
		this.deckId = 0;
		this.handId = 0;
		this.benchId = 0;
	}
	
	


	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	public static void upload(CardTS c) {

		CardRDG.insert(c);

	}	
	public static int getLastCardId() {
		
		return CardRDG.getMaxId();
	}
	public static void putInHand(int cId, int uId) {
		CardRDG.update(cId,FIELD_NAME[4], uId);
	}
}
