package ts;

import model.Play;
import technical_service.GameRDG;
import technical_service.HandRDG;
import technical_service.UserRDG;


public class BoardTS {
	public int getGameid() {
		return gameid;
	}

	public int[] getDecks() {
		return decks;
	}

	public int[] getPlayers() {
		return players;
	}

	public Play[] getPlays() {
		return plays;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public void setDecks(int[] decks) {
		this.decks = decks;
	}

	public void setPlayers(int[] players) {
		this.players = players;
	}

	public void setPlays(Play[] plays) {
		this.plays = plays;
	}
	
	public BoardTS(int gameid, int[] decks, int[] players, Play[] plays) {
		super();
		this.gameid = gameid;
		this.decks = decks;
		this.players = players;
		this.plays = plays;
	}
	
	public BoardTS() {
		this.gameid = 0;
		this.decks = new int[2];
		this.players = new int[2];
		this.plays = new Play[2];
	}

	private int gameid;
	private int[] decks;
	private int[] players;
	private Play[] plays;

	public synchronized static BoardTS listBoard(String gId) {
		int intGid= Integer.parseInt(gId); 
		BoardTS b = new BoardTS();
		b.setGameid(intGid);
		
		GameRDG grdg = GameRDG.find(intGid);
		int[] plrs = {grdg.getPlayer1(),grdg.getPlayer2()};
		
		UserRDG p1 =UserRDG.find(grdg.getPlayer1());
		UserRDG p2 =UserRDG.find(grdg.getPlayer2());
		
		int[] dks = {p1.getCurrentDeck(),p2.getCurrentDeck()};
		HandRDG h1 = HandRDG.find(p1.getHandId());
		HandRDG h2 = HandRDG.find(p2.getHandId());
		
		Play play1= new Play(h1.getHandSize(),h1.getDeckSize(),p1.getGameStatus());
		Play play2= new Play(h2.getHandSize(),h2.getDeckSize(),p2.getGameStatus());
		Play[] playL= {play1, play2};
		b.setDecks(dks);
		
		b.setPlayers(plrs);
		b.setPlays(playL);
		
		System.out.println("Game Id "+ b.getGameid());
		System.out.println("Player Id "+ b.getPlayers()[0]);
		System.out.println("Player Id "+ b.getPlayers()[1]);
		System.out.println("Deck Id "+ b.getDecks()[0]);
		System.out.println("Deck Id "+ b.getDecks()[1]);		
		System.out.println("Status P1 "+ b.getPlays()[0].getStatus());
		System.out.println("Status P1 "+ b.getPlays()[0].getHandsize());
		System.out.println("Status P1 "+ b.getPlays()[0].getDecksize());

		System.out.println("Status P2 "+ b.getPlays()[1].getStatus());
		System.out.println("Status P2 "+ b.getPlays()[1].getHandsize());
		System.out.println("Status P2 "+ b.getPlays()[1].getDecksize());

	

		return b;

	}
}
