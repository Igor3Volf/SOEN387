package technical_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ts.CardTS;
import ts.GameTS;
import Utility.DatabaseConn;

public class GameRDG {

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
		this.gameId = gameId;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	public GameRDG(int gameId, int player1, int player2) {
		super();
		this.gameId = gameId;
		this.player1 = player1;
		this.player2 = player2;
	}
	public GameRDG(int player1, int player2) {
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
	public GameRDG(int gameId, int player1, int player2, int player1Hand,
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
	public GameRDG(){
		this.gameId = 0;
		this.player1 = 0;
		this.player2 = 0;
		this.player1Hand = 0;
		this.player2Hand = 0;
		this.player1Bench = 0;
		this.player2Bench = 0;
	}	
	public GameRDG(int gId) {
		this.gameId = gId;
	}
	private int gameId;	
	private int player1;
	private int player2;
	private int player1Hand;
	private int player2Hand;
	private int player1Bench;
	private int player2Bench;
	
	public synchronized static int getMaxId() {
		int result = 0;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "SELECT MAX(gameId) as 'max' FROM Games;";
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
	
	public static List<GameRDG> findAll() {
		Connection conn = (new DatabaseConn()).dbConn.get();
		List<GameRDG> l = new ArrayList<GameRDG>();

		String allUsersSQL = "SELECT gameId, player1, player2, player1Hand, player2Hand, player1Bench, player2Bench FROM Games";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(allUsersSQL);
			ResultSet rs = prepStat.executeQuery();

			while (rs.next()) {
				GameRDG grdg= new GameRDG();
				
				grdg.setGameId(Integer.parseInt(rs.getString("gameId")));
				grdg.setPlayer1(Integer.parseInt(rs.getString("player1")));
				grdg.setPlayer2(Integer.parseInt(rs.getString("player2")));
				grdg.setPlayer1Hand(Integer.parseInt(rs.getString("player1Hand")));
				grdg.setPlayer2Hand(Integer.parseInt(rs.getString("player2Hand")));
				grdg.setPlayer1Bench(Integer.parseInt(rs.getString("player1Bench")));
				grdg.setPlayer2Bench(Integer.parseInt(rs.getString("player2Bench")));				
				l.add(grdg);
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
	public synchronized static GameRDG find(int gId) {

		Connection conn = (new DatabaseConn()).dbConn.get();
		GameRDG grdg = null;
		String scriptSQL = "SELECT gameId, player1, player2, player1Hand, player2Hand, player1Bench, player2Bench FROM Games WHERE gameId = ?";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(scriptSQL);
			prepStat.setInt(1, gId);
			ResultSet rs = prepStat.executeQuery();
			if (rs.next()) 
			{
				grdg= new GameRDG(gId);
				grdg.setGameId(Integer.parseInt(rs.getString("gameId")));
				grdg.setPlayer1(Integer.parseInt(rs.getString("player1")));
				grdg.setPlayer2(Integer.parseInt(rs.getString("player2")));
				grdg.setPlayer1Hand(Integer.parseInt(rs.getString("player1Hand")));
				grdg.setPlayer2Hand(Integer.parseInt(rs.getString("player2Hand")));
				grdg.setPlayer1Bench(Integer.parseInt(rs.getString("player1Bench")));
				grdg.setPlayer2Bench(Integer.parseInt(rs.getString("player2Bench")));	
				
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
		return grdg;
	}
	public synchronized static int insert(GameTS g) {
		int result = -1;
		Connection conn = (new DatabaseConn()).dbConn.get();
		String sqlStatement = "INSERT INTO Games (gameId, player1, player2, player1Hand, player2Hand, player1Bench, player2Bench) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement prepStat = null;
		try {
			prepStat = conn.prepareStatement(sqlStatement);
			prepStat.setInt(1, g.getGameId());
			prepStat.setInt(2, g.getPlayer1());
			prepStat.setInt(3, g.getPlayer2());
			prepStat.setInt(4, g.getPlayer1Hand());
			prepStat.setInt(5, g.getPlayer2Hand());
			prepStat.setInt(6, g.getPlayer1Bench());
			prepStat.setInt(7, g.getPlayer2Bench());
			result = prepStat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	

}
