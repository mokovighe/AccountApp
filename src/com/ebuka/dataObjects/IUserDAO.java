package com.ebuka.dataObjects;

import java.util.List;

import com.ebuka.model.UserModel;

public interface IUserDAO {
	public int insertUser(UserModel user);
	public int updateLoginCount(String username, int countValue);
	public UserModel getUser(String username, String password);
	public UserModel getUserByUsername(String username);
	public List<UserModel> getAllUsers();

}
