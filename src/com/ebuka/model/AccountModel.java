package com.ebuka.model;

import java.io.Serializable;

import com.ebuka.utils.GenericHelpers;

public class AccountModel implements Serializable {

	private static final long serialVersionUID = 258170952308470152L;
	
	private int id;
	private String accountType;
	private String accountNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		//System.out.println("HASHED account number::" + GenericHelpers.getHashString(accountNumber));
		//this.accountNumber = accountNumber;
		this.accountNumber = GenericHelpers.getHashString(accountNumber);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
