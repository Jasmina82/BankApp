package bank.DAO;

import java.util.List;

import bank.DTO.Account;

public interface AdminDAO {

	public List<Account> getAllAccounts();
	public List<Account> getLoggedUsers();
	public boolean editBalance(Account account);
	public boolean deleteUser(String accountNumber);
}
