package bank.DAO;

import bank.DTO.Account;

public interface GuestDAO {

	public boolean registerNewUser(Account account);
	public void deposit(Account account);
}
