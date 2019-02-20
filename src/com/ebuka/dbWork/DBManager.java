package com.ebuka.dbWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager implements IDBManager {
	static Connection conn = null;
	
	public static Connection getDBConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.cj.jdbc.Driver
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/accountsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			conn = DriverManager.getConnection(connUrl, dbUser,dbPassw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}	
	
}
