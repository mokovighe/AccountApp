package com.ebuka.dataObjects;

import java.util.List;

import com.ebuka.model.AccountModel;

public interface IAccountDAO {
	public int insertAccount(AccountModel account);
	public int updateAccount(AccountModel account, int id);
	public int deleteAccountById(int id);
	public AccountModel getAccountById(int id);
	public AccountModel getAccountByAccountNumber(String accountNumber);
	public List<AccountModel> getAllAccountsByAccountType(String accountType);
	public List<AccountModel> getAllAccounts();
}
