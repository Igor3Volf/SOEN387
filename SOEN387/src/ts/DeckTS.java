package ts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import technical_service.CardRDG;
import technical_service.DeckRDG;
import technical_service.UserRDG;
import model.Card;

public class DeckTS {

	private int deckId;
	private List<CardTS> cards;

	public List<CardTS> getCards() {
		return cards;
	}

	public void setCards(List<CardTS> cards) {
		this.cards = cards;
	}

	public int getDeckId() {
		return deckId;
	}

	public DeckTS(List<CardTS> cards) {
		super();
		this.deckId = getUniqueId();
		this.cards = cards;
	}

	public DeckTS() {
		super();
		this.deckId = getUniqueId();
		this.cards = new ArrayList<CardTS>();
	}

	public DeckTS(int foundDeck, List<CardTS> cards2) {
		// TODO Auto-generated constructor stub
		
		this.deckId = foundDeck;
		this.cards = cards2;
	}

	public synchronized int getDeckSize() {
		return cards.size();
	}

	public synchronized static void upload(DeckTS d, int userId) {
		UserTS.assignDeck(userId, d.getDeckId());
		for (CardTS val : d.getCards()) {
			CardTS.upload(val);
		}

	}

	public synchronized static DeckTS proccess(String deck) {
		DeckTS d = new DeckTS();
		int cId = CardTS.getLastCardId();
		String[] strCard = deck.split("\n");
		for (int i = 0; i < strCard.length; i++) {
			cId++;
			String[] cardDesc = strCard[i].split(" ");
			CardTS c = new CardTS(cId, cardDesc[0].charAt(0), cardDesc[1],d.getDeckId());
			d.cards.add(c);
		}
		return d;
	}

	private synchronized int getUniqueId() {
		int uid = DeckRDG.getMaxId() + 1;
		return uid;
	}

	public static DeckTS findDeck(int userId) {
		int foundDeck = UserRDG.find(userId).getCurrentDeck();
		if (foundDeck < 1) {
			return null;
		} else {
			DeckTS d = new DeckTS(foundDeck, DeckRDG.find(foundDeck).getCards());
			return d;

		}
	}

	public static int nextCardInTheDeck(String gId) 
	{		
		DeckRDG d = DeckRDG.find(Integer.parseInt(gId));
		int nextCard = -1;
		for(int i=0; i<d.getCards().size();i++)
		{
			
			if(d.getCards().get(i).getHandId()==0)
			{
				System.out.println("NEXT CARD IS "+d.getCards().get(i).getHandId());
				return d.getCards().get(i).getCardId();
			}
			
		}
		return nextCard;
	}
}
