package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ts.UserTS;
import model.User;
import Utility.DatabaseConn;

public class UserRDG {

	

	private int userId;
	private String userName;
	private String password;
	private int currentDeck;
	private String gameStatus;
	private int gameId;
	private int handId;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGameStatus() {
		return gameStatus;
	}

	public int getGameId() {
		return gameId;
	}

	public int getHandId() {
		return handId;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setHandId(int handId) {
		this.handId = handId;
	}
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public int getCurrentDeck() {
		return currentDeck;
	}

	public void setUserName(String u) {
		this.userName = u;
	}

	public void setCurrentDeck(int d) {
		this.currentDeck = d;
	}
	public UserRDG(int userId, String userName, String password,
			int currentDeck, String gameStatus, int gameId, int handId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.currentDeck = currentDeck;
		this.gameStatus = gameStatus;
		this.gameId = gameId;
		this.handId = handId;
	}
	
	public UserRDG(int id, String n, int d) {
		this.userId = id;
		this.userName = n;
		this.currentDeck = d;
	}

	public UserRDG(int id, String n, String pass) {
		this.userId = id;
		this.userName = n;
		this.password = pass;

	}

	public UserRDG(String n) {
		this.userName = n;
	}
	public UserRDG(){
		this.userId = 0;
		this.userName = "";
		this.password = "";
		this.currentDeck = 0;
		this.gameStatus = "";
		this.gameId = 0;
		this.handId = 0;
	}

	public static void truncate(Connection conn){
		String trunSQL = "TRUNCATE Users;";
		try {
			PreparedStatement prepStat = conn.prepareStatement(trunSQL);
			prepStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static List<UserRDG> findAll() {
		Connection conn = (new DatabaseConn()).dbConn.get();
		List<UserRDG> l = new ArrayList<UserRDG>();

		String allUsersSQL = "SELECT userId, userName, currentDeck, gameStatus, gameId, handId FROM Users";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(allUsersSQL);
			ResultSet rs = prepStat.executeQuery();

			while (rs.next()) {
				UserRDG ur= new UserRDG();				
				ur.setUserId(Integer.parseInt(rs.getString("userId")));
				ur.setUserName(rs.getString("userName"));
				ur.setGameStatus(rs.getString("gameStatus"));
				
				if(!(rs.getString("currentDeck")==null))					
					ur.setCurrentDeck(Integer.parseInt(rs.getString("currentDeck")));
				if(!(rs.getString("gameId")==null))					
					ur.setGameId(Integer.parseInt(rs.getString("gameId")));
				if(!(rs.getString("handId")==null))					
					ur.setHandId(Integer.parseInt(rs.getString("handId")));			

				l.add(ur);
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

	public synchronized static UserRDG find(String username) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		UserRDG ur = new UserRDG();				

		String allUsersSQL = "SELECT userId, userName, currentDeck, gameStatus, gameId, handId FROM Users WHERE userName = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(allUsersSQL);
			prepStat.setString(1, username);
			ResultSet rs = prepStat.executeQuery();

			if(rs.next()) {
				ur.setUserId(Integer.parseInt(rs.getString("userId")));
				ur.setUserName(rs.getString("userName"));
				ur.setGameStatus(rs.getString("gameStatus"));
				
				if(!(rs.getString("currentDeck")==null))					
					ur.setCurrentDeck(Integer.parseInt(rs.getString("currentDeck")));
				if(!(rs.getString("gameId")==null))					
					ur.setGameId(Integer.parseInt(rs.getString("gameId")));
				if(!(rs.getString("handId")==null))					
					ur.setHandId(Integer.parseInt(rs.getString("handId")));			

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

	public synchronized static UserRDG find(int userId) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		UserRDG ur = null;
		String allUsersSQL = "SELECT userId, userName, currentDeck, gameStatus, gameId, handId FROM Users WHERE userId = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(allUsersSQL);
			prepStat.setInt(1, userId);
			ResultSet rs = prepStat.executeQuery();

			if(rs.next()) {
				ur = new UserRDG();
				ur.setUserId(Integer.parseInt(rs.getString("userId")));
				ur.setUserName(rs.getString("userName"));
				ur.setGameStatus(rs.getString("gameStatus"));
				
				if(!(rs.getString("currentDeck")==null))					
					ur.setCurrentDeck(Integer.parseInt(rs.getString("currentDeck")));
				if(!(rs.getString("gameId")==null))					
					ur.setGameId(Integer.parseInt(rs.getString("gameId")));
				if(!(rs.getString("handId")==null))					
					ur.setHandId(Integer.parseInt(rs.getString("handId")));			

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

	public synchronized static UserRDG find(String u, String pass) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		UserRDG ur = null;
		String sqlStatement = "SELECT userId, userName, currentDeck, gameStatus, gameId, handId FROM Users WHERE userName = ? AND password = MD5(?)";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setString(1, u);
			prepStat.setString(2, pass);

			ResultSet rs = prepStat.executeQuery();
			if(rs.next()) {
				ur = new UserRDG();
				ur.setUserId(Integer.parseInt(rs.getString("userId")));
				ur.setUserName(rs.getString("userName"));
				ur.setGameStatus(rs.getString("gameStatus"));
				
				if(!(rs.getString("currentDeck")==null))					
					ur.setCurrentDeck(Integer.parseInt(rs.getString("currentDeck")));
				if(!(rs.getString("gameId")==null))					
					ur.setGameId(Integer.parseInt(rs.getString("gameId")));
				if(!(rs.getString("handId")==null))					
					ur.setHandId(Integer.parseInt(rs.getString("handId")));			

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

	public synchronized static int insert(UserTS u) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "INSERT INTO Users (userId,userName,password) VALUES(?,?,MD5(?))";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, u.getUserId());
			prepStat.setString(2, u.getUserName());
			prepStat.setString(3, u.getPassword());

			result = prepStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public synchronized static int delete(int uid) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "DELETE FROM Users WHERE userId=?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, uid);
			result = prepStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public synchronized static int update(int uid, String fieldName, String newValue) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "UPDATE Users SET "+fieldName+"=? WHERE userId=?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setString(1, newValue);
			prepStat.setInt(2, uid);
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
		String sqlStatement = "UPDATE Users SET "+fieldName+"=? WHERE userId=?";
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
		String sqlStatement = "SELECT MAX(userId) as 'max' FROM Users;";
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
