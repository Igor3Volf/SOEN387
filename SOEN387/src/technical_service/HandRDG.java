package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ts.HandTS;
import Utility.DatabaseConn;

public class HandRDG {
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
	public HandRDG(int handId, int handSize, int deckSize) {
		super();
		this.handId = handId;
		this.handSize = handSize;
		this.deckSize = deckSize;
	}
	public HandRDG(int hId)
	{
		this.handId = hId;
	}
	public synchronized static int getMaxId() {
		int result = 0;
		Connection conn = (new DatabaseConn()).dbConn.get();
		CardRDG cl = null;
		String sqlStatement = "SELECT MAX(handId) as 'max' FROM Hands;";
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
	public static int insert(HandTS h) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "INSERT INTO Hands (handId, handSize, deckSize ) VALUES(?,?,?)";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, h.getHandId());
			prepStat.setInt(2, h.getHandSize());
			prepStat.setInt(3, h.getDeckSize());
			
			result = prepStat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	public synchronized static HandRDG find(int hId) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		HandRDG hrdg = null;
		String scriptSQL = "SELECT handId, handSize, deckSize FROM Hands WHERE handId = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(scriptSQL);
			prepStat.setInt(1, hId);
			ResultSet rs = prepStat.executeQuery();
			if (rs.next()) 
			{
				hrdg= new HandRDG(hId);				
				hrdg.setHandSize(Integer.parseInt(rs.getString("handSize")));
				hrdg.setDeckSize(Integer.parseInt(rs.getString("deckSize")));			
				
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
		return hrdg;
	}
	public synchronized static int update(int hid, String fieldName, int newValue) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "UPDATE Hands SET "+fieldName+"=? WHERE handId=?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, newValue);
			prepStat.setInt(2, hid);
			result = prepStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
