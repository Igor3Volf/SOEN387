package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ts.CardTS;
import ts.UserTS;
import Utility.DatabaseConn;

public class CardRDG {

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

	public void setDeckId(int deckId) {
		this.deckId = deckId;
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
	public CardRDG(int id, char type, String name,int dId,int hId,int bId) {
		this.cardId = id;
		this.cardType = type;
		this.cardName = name;
		this.deckId = dId;
		this.handId =hId;
		this.benchId = bId;
	}
	public CardRDG(int id, char type, String name) {
		this.cardId = id;
		this.cardType = type;
		this.cardName = name;
	}
	public CardRDG() {
		this.cardId = 0;
		this.cardType ='\0';
		this.cardName = "";
		this.deckId = 0;
		this.handId =0;
		this.benchId = 0;
	}
	public static void truncate(Connection conn){
		String trunSQL = "TRUNCATE Cards;";
		try {
			PreparedStatement prepStat = conn.prepareStatement(trunSQL);
			prepStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<CardRDG> findAll() {
		Connection conn = (new DatabaseConn()).dbConn.get();
		List<CardRDG> l = new ArrayList<CardRDG>();

		String allUsersSQL = "SELECT cardId, cardType, cardName, deckId, handId, benchId FROM Cards";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(allUsersSQL);
			ResultSet rs = prepStat.executeQuery();

			while (rs.next()) {
				CardRDG crdg=new CardRDG();
				
				crdg.setCardId(Integer.parseInt(rs.getString("cardId")));
				crdg.setCardType(rs.getString("cardType").charAt(0));
				crdg.setCardName(rs.getString("cardName"));
				if(!(rs.getString("deckId")==null))
					crdg.setDeckId(Integer.parseInt(rs.getString("deckId")));
				if(!(rs.getString("handId")==null))
					crdg.setHandId(Integer.parseInt(rs.getString("handId")));
				if(!(rs.getString("benchId")==null))
					crdg.setBenchId(Integer.parseInt(rs.getString("benchId")));		
				
				l.add(crdg);
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
		return l;
	}

	public synchronized static int insert(CardTS c) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "INSERT INTO Cards (cardId,cardType,cardName, deckId) VALUES(?,?,?,?)";
		PreparedStatement prepStat = null;
		try {
			System.out.println("TYPE "+Character.toString(c.getCardType()));
			
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, c.getCardId());
			prepStat.setString(2, Character.toString(c.getCardType()));
			prepStat.setString(3, c.getCardName());
			prepStat.setInt(4, c.getDeckId());
			result = prepStat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public synchronized static int update(int uid, String fieldName, int newValue) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "UPDATE Cards SET "+fieldName+"=? WHERE cardId=?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, newValue);
			prepStat.setInt(2, uid);
			result = prepStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public synchronized static int getMaxId() {
		int result = 0;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "SELECT MAX(cardId) as 'max' FROM Cards;";
		PreparedStatement prepStat = null;

		ResultSet rs;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			rs = prepStat.executeQuery();
			while (rs.next()) {
				result=rs.getInt("max");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
