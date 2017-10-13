package bank.DAO;

import java.util.List;

import bank.DTO.Account;
import bank.DTO.Transaction;

public interface UserDAO {
	
	public boolean withdrawOrDeposit(Account account);	
	public boolean editProfile(Account account);
	public boolean setStatus(String status,String accountNumber);
	public  boolean Transact(Account originAccount,Account targetAccount);
	public List<Transaction> getTransactions(Account account);
}
