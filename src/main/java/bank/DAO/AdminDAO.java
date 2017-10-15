package bank.DAO;

import java.util.List;

import bank.DTO.Account;
import bank.DTO.Admin;

public interface AdminDAO {

	public List<Account> getAllAccounts();
	public List<Account> getLoggedUsers();
	public boolean editBalance(Account account);
	public boolean deleteUser(String accountNumber);
	public boolean changeAdmin(String admin,int pin);
	public Admin getAdmin();
}
