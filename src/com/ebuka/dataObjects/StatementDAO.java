package com.ebuka.dataObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ebuka.dbWork.DBManager;
import com.ebuka.model.StatementModel;
import com.ebuka.utils.GenericHelpers;
import com.google.gson.Gson;

public class StatementDAO implements IStatementDAO {
	static Connection conn = null;
	static PreparedStatement prepareSql = null;

	@Override
	public int insertStatement(StatementModel statement) {
		int status = 0;
		try {
			String sql = "insert into statement values(?,?,?)";
			conn = DBManager.getDBConnection();
			
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setInt(1, statement.getAccountId());
			prepareSql.setString(2, statement.getDateField());
			prepareSql.setDouble(3, statement.getAmount());
						
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
	public int updateStatement(StatementModel statement, int id) {
		int status = 0;
		try {
			String sql = "update statement SET account_id = ?, datefield = ?, amount = ? WHERE id = ?";
					
			conn = DBManager.getDBConnection();
			
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setInt(1, statement.getAccountId());
			prepareSql.setDate(2, GenericHelpers.getDateFromString(statement.getDateField()));
			prepareSql.setDouble(3, statement.getAmount());
			prepareSql.setInt(4, id);
						
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
	public int deleteStatementById(int id) {
		int status = 0;
		try
	    {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("DELETE FROM statement WHERE id=?");
			prepareSql.setInt(1, id);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			status = prepareSql.executeUpdate();
	    }
	    catch (SQLException ex)
	    {
	      System.out.println(ex.getMessage());
	    }
		return status;
	}

	@Override
	public int deleteStatementByAccountId(int accountId) {
		int status = 0;
		try
	    {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("DELETE FROM statement WHERE account_id=?");
			prepareSql.setInt(1, accountId);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			status = prepareSql.executeUpdate();
	    }
	    catch (SQLException ex)
	    {
	      System.out.println(ex.getMessage());
	    }
		return status;
	}

	@Override
	public StatementModel getStatementById(int id) {
		StatementModel statement = new StatementModel();
		IAccountDAO acDAO = new AccountDAO();
		Gson gson = new Gson();
        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from statement where id=? limit 1");
			prepareSql.setInt(1, id);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(GenericHelpers.getStringFromDate(rs.getDate(3)));
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
				
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		String jsonData = gson.toJson(statement);
		System.out.println("Account Statement::" + jsonData);
		return statement;
	}

	@Override
	public List<StatementModel> getStatementByAccountId(int accountId) {
	
		List<StatementModel> accountStmtList = new ArrayList<>();
		IAccountDAO acDAO = new AccountDAO();
		Gson gson = new Gson();
        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from statement where account_id=?;");
			prepareSql.setInt(1, accountId);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				StatementModel statement = new StatementModel();
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(rs.getDate(3).toString()); //GenericHelpers.getStringFromDate(rs.getDate(3))
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
					
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
				
				accountStmtList.add(statement);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		String jsonData = gson.toJson(accountStmtList);
		System.out.println("Account Statement::" + jsonData);
		
		return accountStmtList;
	}

	@Override
	public List<StatementModel> getAllStatementsByDate(String fromDate, String toDate) {
		List<StatementModel> accountStmtList = new ArrayList<>();
		IAccountDAO acDAO = new AccountDAO();
		        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("SELECT * FROM statement WHERE datefield >=? AND datefield <=?;"); //eg: 2019-01-14
			prepareSql.setString(1, fromDate);
			prepareSql.setString(2, toDate);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				StatementModel statement = new StatementModel();
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(rs.getDate(3).toString()); //GenericHelpers.getStringFromDate(rs.getDate(3))
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
					
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
				
				accountStmtList.add(statement);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
		return accountStmtList;
	}

	@Override
	public List<StatementModel> getAllStatementsByAmount(Double fromAmount, Double toAmount) {
		List<StatementModel> accountStmtList = new ArrayList<>();
		IAccountDAO acDAO = new AccountDAO();
        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("SELECT * FROM statement WHERE amount >=? AND amount <=?;");
			prepareSql.setDouble(1, fromAmount);
			prepareSql.setDouble(2, toAmount);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				StatementModel statement = new StatementModel();
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(rs.getDate(3).toString());
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
					
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
				
				accountStmtList.add(statement);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
		return accountStmtList;
	}

	@Override
	public List<StatementModel> getAllStatements() {
		List<StatementModel> accountStmtList = new ArrayList<>();
		IAccountDAO acDAO = new AccountDAO();
		        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from statement;");
						
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				StatementModel statement = new StatementModel();
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(rs.getDate(3).toString()); //GenericHelpers.getStringFromDate(rs.getDate(3))
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
				
				accountStmtList.add(statement);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Account Statement::" + accountStmtList);
		
		return accountStmtList;
	}
	
	@Override
	public List<StatementModel> getAllStatementsByQuery(String query)
	{
		List<StatementModel> accountStmtList = new ArrayList<>();
		IAccountDAO acDAO = new AccountDAO();
		        
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement(query);
						
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				StatementModel statement = new StatementModel();
				statement.setId(rs.getInt(1));
				statement.setAccountId(rs.getInt(2));
				statement.setDateField(rs.getDate(3).toString()); //GenericHelpers.getStringFromDate(rs.getDate(3))
				statement.setAmount(rs.getDouble(4));
				
				//get account detail for this statement
				if(rs.getInt(2) != 0)
				{
					statement.setAccount(acDAO.getAccountById(statement.getAccountId()));
				}
				
				accountStmtList.add(statement);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Account Statement::" + accountStmtList);
		
		return accountStmtList;
	}

}
