package com.ebuka.model;

import java.io.Serializable;

public class StatementModel implements Serializable {

	private static final long serialVersionUID = 1339712003544216247L;
	
	private int id;
	private int accountId;
	private String dateField;
	private Double amount;
	private AccountModel account;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getDateField() {
		return dateField;
	}
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public AccountModel getAccount() {
		return account;
	}
	public void setAccount(AccountModel account) {
		this.account = account;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
