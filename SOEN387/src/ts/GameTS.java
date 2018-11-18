package ts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import technical_service.CardRDG;
import technical_service.ChallengeRDG;
import technical_service.DeckRDG;
import technical_service.GameRDG;
import technical_service.HandRDG;
import technical_service.UserRDG;

public class GameTS {

	
	private synchronized static int getUniqueId(){
		 int uid = GameRDG.getMaxId()+1;
		 return uid ;
	}
	public int getGameId() {
		return gameId;
	}

	public int getPlayer1() {
		return player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setGameId(int gameId) {
		this.gameId = getUniqueId();
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	public GameTS(int gameId, int player1, int player2) {
		super();
		this.gameId = gameId;
		this.player1 = player1;
		this.player2 = player2;
	}
	public GameTS(int player1, int player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
	public int getPlayer1Hand() {
		return player1Hand;
	}
	public int getPlayer2Hand() {
		return player2Hand;
	}
	public int getPlayer1Bench() {
		return player1Bench;
	}
	public int getPlayer2Bench() {
		return player2Bench;
	}
	public void setPlayer1Hand(int player1Hand) {
		this.player1Hand = player1Hand;
	}
	public void setPlayer2Hand(int player2Hand) {
		this.player2Hand = player2Hand;
	}
	public void setPlayer1Bench(int player1Bench) {
		this.player1Bench = player1Bench;
	}
	public void setPlayer2Bench(int player2Bench) {
		this.player2Bench = player2Bench;
	}
	public GameTS(int player1, int player2, int player1Hand,
			int player2Hand) {
		super();
		this.gameId = getUniqueId();
		this.player1 = player1;
		this.player2 = player2;
		this.player1Hand = player1Hand;
		this.player2Hand = player2Hand;
		this.player1Bench = 0;
		this.player2Bench = 0;
	}
	
	public GameTS(int player1, int player2, int player1Hand,
			int player2Hand, int player1Bench, int player2Bench) {
		super();
		this.gameId = getUniqueId();
		this.player1 = player1;
		this.player2 = player2;
		this.player1Hand = player1Hand;
		this.player2Hand = player2Hand;
		this.player1Bench = player1Bench;
		this.player2Bench = player2Bench;
	}
	
	public GameTS(int gameId, int player1, int player2, int player1Hand,
			int player2Hand, int player1Bench, int player2Bench) {
		super();
		this.gameId = gameId;
		this.player1 = player1;
		this.player2 = player2;
		this.player1Hand = player1Hand;
		this.player2Hand = player2Hand;
		this.player1Bench = player1Bench;
		this.player2Bench = player2Bench;
	}
	
	private int gameId;	
	private int player1;
	private int player2;
	private int player1Hand;
	private int player2Hand;
	private int player1Bench;
	private int player2Bench;
	
	public synchronized static void initGame(String challengeId) {
		ChallengeRDG c =ChallengeRDG.find(Integer.parseInt(challengeId));	
		int player1 = c.getChallenger();
		int player2 = c.getChallengee();
		
		int hI1= HandTS.newHand();
		int hI2=HandTS.newHand();
		
		
		
		GameTS g=new GameTS(player1,player2,hI1,hI2);
		GameRDG.insert(g);
		
		

		UserTS.statusPlay(player1);
		UserTS.statusPlay(player2);
		
		UserTS.assignToGame(player1, g.getGameId());
		UserTS.assignToGame(player2, g.getGameId());
		
		UserTS.assignHand(player1,hI1);
		UserTS.assignHand(player2,hI2);


	}
	
	public static Map<Integer,Integer[]> listGames() 
	{
		Map<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
		List<GameRDG> rdgl= GameRDG.findAll();
		
		for(GameRDG val:rdgl)
		{
			Integer[] temp = new Integer[2];
			int key=val.getGameId();
			temp[0]=val.getPlayer1();
			temp[1]=val.getPlayer2();			
			map.put(key,temp);
		}
		return map;

	}
	public static int drawCard(int uId, String gId) {
		int nextCardId = DeckTS.nextCardInTheDeck(gId);
		if(nextCardId>0){
			UserRDG u = UserRDG.find(uId);		
			CardTS.putInHand(nextCardId, u.getHandId());
			HandTS.addCard(u.getHandId());
			return nextCardId;
		}
		else{
			return nextCardId;
		}			
	}
	
	
}
