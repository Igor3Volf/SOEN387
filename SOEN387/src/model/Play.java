package model;


public class Play {
	public String getStatus() {
		return status;
	}

	public int getHandsize() {
		return handsize;
	}

	public int getDecksize() {
		return decksize;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setHandsize(int handsize) {
		this.handsize = handsize;
	}

	public void setDecksize(int decksize) {
		this.decksize = decksize;
	}

	private int handsize;
	private int decksize;
	private String status;

	public Play(int handsize, int decksize, String status) {
		super();
		this.handsize = handsize;
		this.decksize = decksize;
		this.status = status;
	}

}