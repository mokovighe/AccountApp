package com.ebuka.dataObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ebuka.dbWork.DBManager;
import com.ebuka.model.AccountModel;

public class AccountDAO implements IAccountDAO {
	static Connection conn = null;
	static PreparedStatement prepareSql = null;
	
	@Override
	public int insertAccount(AccountModel account) {
		int status = 0;
		try {
			String sql = "insert into account values(?,?)";
			conn = DBManager.getDBConnection();
			
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setString(1, account.getAccountType());
			prepareSql.setString(2, account.getAccountNumber());
						
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
	public int updateAccount(AccountModel account, int id) {
		int status = 0;
		try {
			String sql = "update account SET account_type = ?, account_number = ? WHERE id = ?";
					
			conn = DBManager.getDBConnection();
			
			prepareSql = conn.prepareStatement(sql);
			prepareSql.setString(1, account.getAccountType());
			prepareSql.setString(2, account.getAccountNumber());
			prepareSql.setInt(3, id);
						
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
	public int deleteAccountById(int id) {
		int status = 0;
		try
	    {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("DELETE FROM account WHERE id=?");
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
	public AccountModel getAccountById(int id) {
		AccountModel account = new AccountModel();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from account where id=? limit 1");
			prepareSql.setInt(1, id);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				account.setId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getString(3));
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return account;
	}

	@Override
	public AccountModel getAccountByAccountNumber(String accountNumber) {
		AccountModel account = new AccountModel();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from account where account_number=? limit 1");
			prepareSql.setString(1, accountNumber);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				account.setId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getString(3));
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return account;
	}

	@Override
	public List<AccountModel> getAllAccountsByAccountType(String accountType) {
		List<AccountModel> accountList = new ArrayList<>();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from account where account_type=?");
			prepareSql.setString(1, accountType);
			
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				AccountModel account = new AccountModel();
				account.setId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getString(3));
				
				accountList.add(account);
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return accountList;
	}

	@Override
	public List<AccountModel> getAllAccounts() {
		List<AccountModel> accountList = new ArrayList<>();
		try {
			conn = DBManager.getDBConnection();
			prepareSql = conn.prepareStatement("select * from account");
						
			System.out.println("DB Query::" + prepareSql.toString());
			
			ResultSet rs = prepareSql.executeQuery();
			while(rs.next())
			{
				AccountModel account = new AccountModel();
				account.setId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getString(3));
				
				accountList.add(account);
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return accountList;
	}

}
