package ts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import technical_service.DeckRDG;
import technical_service.GameRDG;
import technical_service.HandRDG;
import technical_service.UserRDG;
import Utility.DatabaseConn;

public class HandTS {
	/* 0 - handId
	 * 1 - handSize
	 * 2 - deckSize	 
	 * */
	private final static String[]FIELD_NAME={"handId","handSize","deckSize"};
	private int handId;	
	private int handSize;
	private int deckSize;
	
	public int getHandId() {
		return handId;
	}
	public int getHandSize() {
		return handSize;
	}
	public int getDeckSize() {
		return deckSize;
	}
	public void setHandId(int handId) {
		this.handId = handId;
	}
	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public HandTS(int handId, int handSize, int deckSize) {
		super();
		this.handId = handId;
		this.handSize = handSize;
		this.deckSize = deckSize;
	}
	public HandTS() 
	{
		this.handId = getUniqueId();
		this.handSize = 0;
		this.deckSize = 40;
	}
	public HandTS(int p1HandId) {
		this.handId = p1HandId;
		this.handSize = 0;
		this.deckSize = 40;
	}
	public static int newHand() 
	{
		HandTS h = new HandTS();
		HandRDG.insert(h);
		return h.getHandId();
	}
	
	private synchronized static int getUniqueId(){
		int uid = HandRDG.getMaxId()+1;
		System.out.println("HAND UNIQURE ID "+ uid);

		return uid ;
	}
	public static void addCard(int hId) {
		HandRDG hrdg = HandRDG.find(hId);
		int newHandSize = hrdg.getHandSize() +1;
		int newDeckSize = hrdg.getDeckSize() -1;
		
		HandRDG.update(hId, FIELD_NAME[1], newHandSize );
		HandRDG.update(hId, FIELD_NAME[2],  newDeckSize);
	}
	public static Map<Integer,Integer> listHand(int userId, String gameId) {
		UserRDG u =UserRDG.find(userId);
		int dId = u.getCurrentDeck();
		int hId= u.getHandId();
		DeckRDG d = DeckRDG.find(dId);
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int count=0;
		for(CardTS c: d.getCards())
		{
			count++;
			if(c.getHandId()==hId){
				map.put(count,(c.getCardId()));
			}
		}	
	
		return map;
	}
	
	
}
