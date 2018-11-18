package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ts.CardTS;
import ts.DeckTS;
import model.Card;
import Utility.DatabaseConn;

public class DeckRDG {

	private int deckId;

	public List<CardTS> getCards() {
		return cards;
	}

	public void setCards(List<CardTS> cards) {
		this.cards = cards;
	}

	private List<CardTS> cards;

	public int getDeckId() {
		return deckId;
	}

	public DeckRDG() {
		cards = new ArrayList<CardTS>();
	}

	public DeckRDG(int id) {
		this.deckId = id;
		cards = new ArrayList<CardTS>();
	}

	public synchronized static int getMaxId() {
		int result = 0;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "SELECT MAX(deckId) as 'max' FROM Cards;";
		PreparedStatement prepStat = null;

		ResultSet rs;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			rs = prepStat.executeQuery();
			while (rs.next()) {
				result = rs.getInt("max");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public synchronized static DeckRDG find(int dId) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		DeckRDG dr = null;
		String scriptSQL = "SELECT cardId, cardType, cardName, deckId, handId, benchId FROM Cards WHERE deckId = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(scriptSQL);
			prepStat.setInt(1, dId);
			ResultSet rs = prepStat.executeQuery();
			dr = new DeckRDG(dId);			
			while (rs.next()) 
			{	
				CardTS card = new CardTS();
				card.setCardId(Integer.parseInt(rs.getString("cardId")));
				card.setCardType(rs.getString("cardType").charAt(0));
				card.setCardName(rs.getString("cardName"));
				card.setDeckId(Integer.parseInt(rs.getString("deckId")));
				
				if(!(rs.getString("handId")==null))					
					card.setHandId(Integer.parseInt(rs.getString("handId")));
				if(!(rs.getString("benchId")==null))					
					card.setBenchId(Integer.parseInt(rs.getString("gameId")));
				
				dr.getCards().add(card);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (prepStat != null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dr;
	}
}
