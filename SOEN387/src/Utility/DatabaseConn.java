package Utility;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import technical_service.CardRDG;
import technical_service.UserRDG;

public class DatabaseConn {
	public static ThreadLocal<Connection> dbConn;
	
	
	
	 public void init() {
		System.out.println("Database initiated");

		dbConn= new ThreadLocal<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3308/igor3volf?"
					+"user=igor3volf&password=fendight&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true");
			dbConn.set(conn);
			//truncateAllTables();
			System.out.println("Database is connected");
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};	
	};	

	public void tearDown() {
		System.out.println("Database shutdownl");
		try {
			dbConn.get().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbConn.remove();
	}
	private void truncateAllTables(){
		UserRDG.truncate(dbConn.get());
		CardRDG.truncate(dbConn.get());

	}
}
