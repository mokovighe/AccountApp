package com.ebuka.dataObjects;
import java.util.List;

import com.ebuka.model.StatementModel;

public interface IStatementDAO {
	public int insertStatement(StatementModel statement);
	public int updateStatement(StatementModel statement, int id);
	public int deleteStatementById(int id);
	public int deleteStatementByAccountId(int accountId);
	public StatementModel getStatementById(int id);
	public List<StatementModel> getStatementByAccountId(int accountId);
	public List<StatementModel> getAllStatementsByDate(String fromDate, String toDate);
	public List<StatementModel> getAllStatementsByAmount(Double fromAmount, Double toAmount);
	public List<StatementModel> getAllStatements();
}
