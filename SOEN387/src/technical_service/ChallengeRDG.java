package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utility.DatabaseConn;
import technical_service.CardRDG;
import ts.CardTS;
import ts.ChallengeTS;


public class ChallengeRDG {

	private int chalId;	
	private int challenger;
	private int challengee;
	private int status;
	public ChallengeRDG(int chalId, int challenger, int challengee, int status) {
		super();
		this.chalId = chalId;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	public int getChalId() {
		return chalId;
	}
	public int getChallenger() {
		return challenger;
	}
	public int getChallengee() {
		return challengee;
	}
	public int getStatus() {
		return status;
	}
	public void setChallenger(int challenger) {
		this.challenger = challenger;
	}
	public void setChallengee(int challengee) {
		this.challengee = challengee;
	}
	public void setStatus(int status) {
		this.status = status;
	} 
	
	public synchronized static int getMaxId() {
		int result = 0;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "SELECT MAX(chalId) as 'max' FROM Challenges;";
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

	public synchronized static List<ChallengeRDG> findAll() {

		Connection conn = (new DatabaseConn()).dbConn.get();		
		List<ChallengeRDG>list=new ArrayList<ChallengeRDG>();
		
		ChallengeRDG ur = null;
		String scriptSQL = "SELECT chalId, challenger, challengee, statusId FROM Challenges ";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(scriptSQL);
			ResultSet rs = prepStat.executeQuery();
			while (rs.next()) {
				int chalId = Integer.parseInt(rs.getString("chalId"));
				int challenger = Integer.parseInt(rs.getString("challenger"));
				int challengee = Integer.parseInt(rs.getString("challengee"));
				int statusId = Integer.parseInt(rs.getString("statusId"));			
				
				System.out.println("chalId" + chalId);
				System.out.println("challenger : " + challenger);
				System.out.println("challengee : " + challengee);
				System.out.println("statusId : " + statusId);
				ur = new ChallengeRDG(chalId, challenger, challengee, statusId);
				list.add(ur);
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
		return list;
	}
	
	public synchronized static ChallengeRDG find(int cId) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		ChallengeRDG ur = null;
		String scriptSQL = "SELECT chalId, challenger, challengee, statusId FROM Challenges WHERE chalId = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(scriptSQL);
			prepStat.setInt(1, cId);
			ResultSet rs = prepStat.executeQuery();
			while (rs.next()) {
				int chalId = Integer.parseInt(rs.getString("chalId"));
				int challenger = Integer.parseInt(rs.getString("challenger"));
				int challengee = Integer.parseInt(rs.getString("challengee"));
				int statusId = Integer.parseInt(rs.getString("statusId"));

				System.out.println("Find chalId" + chalId);
				System.out.println("Find challenger : " + challenger);
				System.out.println("Find challengee : " + challengee);
				System.out.println("Find statusId : " + statusId);
				ur = new ChallengeRDG(chalId, challenger, challengee, statusId);
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
		return ur;
	}
	
	public synchronized static int insert(ChallengeTS c) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "INSERT INTO Challenges (chalId,challenger,challengee, statusId) VALUES(?,?,?,?)";
		PreparedStatement prepStat = null;
		try {
			
			System.out.println("Insert chalId" + c.getChalId());
			System.out.println("Insert challenger : " + c.getChallenger());
			System.out.println("Insert challengee : " + c.getChallengee());
			System.out.println("Insert statusId : " + c.getStatus());
			
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, c.getChalId());
			prepStat.setInt(2, c.getChallenger());
			prepStat.setInt(3, c.getChallengee());
			prepStat.setString(4, String.valueOf(c.getStatus()));
			result = prepStat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static int update(String cId, int status) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "UPDATE Challenges SET statusId=? WHERE chalId=?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setString(1, String.valueOf(status));
			prepStat.setInt(2, Integer.parseInt(cId));
			result = prepStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}
	
}
