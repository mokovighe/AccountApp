package com.ebuka.dataObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

///import org.apache.log4j.Logger;

import com.ebuka.dbWork.DBManager;
import com.ebuka.model.UserModel;

public class UserDAO implements IUserDAO {
	static Connection conn = null;
	static PreparedStatement prepareSql = null;
	
	@Override
	public int insertUser(UserModel user)
	{
		int status = 0;
		try {
			String sql = "insert into user(username,password,surname,othernames, role) values (?,?,?,?)";
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setString(1, user.getUsername());
			prepareSql.setString(2, user.getPassword());
			prepareSql.setString(3, user.getSurname());
			prepareSql.setString(4, user.getOthernames());
			prepareSql.setString(5, user.getRole());
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			status = prepareSql.executeUpdate();
			
			System.out.println("DB Query Done. Please close connection");
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
	
	public int updateLoginCount(String username, int countValue)
	{
		int status = 0;
		try {
			String sql = "update user SET loginCount = ? WHERE username = ?";
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setInt(1, countValue);
			prepareSql.setString(2, username);
						
			System.out.println("DB Query::" + prepareSql.toString());
			
			status = prepareSql.executeUpdate();
			
			System.out.println("DB Query Done. Please close connection");
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
	
	@Override
	public UserModel getUser(String username, String password) {
		UserModel u = new UserModel();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from user where username=? and password=? limit 1");
			prepareSql.setString(1, username);
			prepareSql.setString(2, password);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setSurname(rs.getString(4));
				u.setOthernames(rs.getString(5));
				u.setRole(rs.getString(6));
				u.setLoginCount(rs.getInt(7));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return u;
	}
	
	@Override
	public UserModel getUserByUsername(String username)
	{
		UserModel u = new UserModel();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from user where username=? limit 1");
			prepareSql.setString(1, username);
					
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setSurname(rs.getString(4));
				u.setOthernames(rs.getString(5));
				u.setRole(rs.getString(6));
				u.setLoginCount(rs.getInt(7));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return u;
	}
	
	@Override
	public List<UserModel> getAllUsers()
	{
		List<UserModel> users = new ArrayList<>();
		
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from user");
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				UserModel u = new UserModel();
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setSurname(rs.getString(4));
				u.setOthernames(rs.getString(5));
				u.setRole(rs.getString(6));
				u.setLoginCount(rs.getInt(7));
				
				users.add(u);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return users;
	}

}
