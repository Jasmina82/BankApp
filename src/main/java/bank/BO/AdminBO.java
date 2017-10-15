package bank.BO;

import java.util.ArrayList;
import java.util.List;

import bank.DAO.AdminDAO;
import bank.DAO.AdminDAOImplementation;
import bank.DAO.GuestDAO;
import bank.DAO.GuestDAOImplementation;
import bank.DAO.UserDAO;
import bank.DAO.UserDAOImplementation;
import bank.DTO.Account;
import bank.main.AdminMenu;
import bank.main.Inputs;

public class AdminBO {
	AdminDAO adminDao = new AdminDAOImplementation();
	UserDAO userDAO = new UserDAOImplementation();
	UserBO userBo = new UserBO();
	GuestDAO guestDao = new GuestDAOImplementation();
	GuestBO guestBo = new GuestBO();
	InformationHandler infos = new InformationHandler();
	LoginHandler log = new LoginHandler();

	// prints logged users
	public void loggedUsers() {
		List<Account> loggedUsers = new ArrayList<>();
		loggedUsers = getListOfLoggedUsers();

		System.out.println("Currently online users: " + loggedUsers.size());

		for (Account e : loggedUsers)
			System.out.println("Client: " + e.getUser().getFirstName() + " " + e.getUser().getLastName()
					+ " - Account: " + e.getAccountNumber());

		System.out.println();
		AdminMenu.getAdminMenu();
	}

	// deletes user
	public void deleteAccount() {

		String accountNumber = InputsValidator.getAccountFromPerson("Enter account for delete:");

		if (!log.isAccountInList(accountNumber)) {
			System.out.println("Account " + accountNumber + " doesn't exist!\n");
			AdminMenu.getAdminMenu();
		}
		if (adminDao.deleteUser(accountNumber))
			System.out.println("User with account " + accountNumber + " is deleted!\n");
		else
			System.out.println("Something went wrong!User is not deleted!\n");

		AdminMenu.getAdminMenu();
	}

	// sets new balance for chosen account
	public void editBalance() {
		Account account = getAccountForEditBalance();

		if (adminDao.editBalance(account))
			System.out.println("Balance edited!");
		else
			System.out.println("Something went wrong!");

		AdminMenu.getAdminMenu();
	}

	// edits user's email or phone number
	public void editProfile() {

		Account account = getInputOfUsersAccount();
		System.out.println(account.getUser().toString());

		account = userBo.handleProfileOption(account);

		if (userDAO.editProfile(account))
			System.out.println("User edited!");

		else
			System.out.println("Something went wrong!");

		AdminMenu.getAdminMenu();
	}

	// creates new user
	public void createNewUser() {

		Account account = infos.getNewUserInfos();
		if (guestDao.registerNewUser(account))
			System.out.println("New user created!" + account.toString() + "\n" + account.getUser().toString() + "\n");
		else
			System.out.println("Something went wrong!");

		AdminMenu.getAdminMenu();
	}

	// returns a list of logged users from db
	public List<Account> getListOfLoggedUsers() {
		List<Account> loggedUsers = new ArrayList<>();
		loggedUsers = adminDao.getLoggedUsers();

		if (loggedUsers == null) {
			System.out.println("There is no logged users currently!\n");
			AdminMenu.getAdminMenu();
		}
		return loggedUsers;
	}

	// returns account based on user's input of account number
	public Account getInputOfUsersAccount() {

		String accountNumber = InputsValidator.getAccountFromPerson("Enter account:");

		if (!log.isAccountInList(accountNumber)) {
			System.out.println("Account " + accountNumber + " dosn't exist!");
			AdminMenu.getAdminMenu();
		}
		Account accountForEditOrDelete = infos.getAccount(accountNumber);
		return accountForEditOrDelete;
	}

	// method provides options for editing account
	public Account getAccountForEditBalance() {
		Account account = getInputOfUsersAccount();
		System.out.println("Account: " + account.getAccountNumber() + " Balance: " + account.getBalance());

		double balance = Inputs.getUserDoubleInput("Enter new balance:");
		account.setBalance(balance);
		return account;
	}

	// change admin's username and pin
	public boolean changeAdmin() {
		String username = Inputs.getUserInput("Enter username:");
		int pin = InputsValidator.getPinInput("Enter pin ( XXXX ) :");

		if (adminDao.changeAdmin(username, pin)) {
			System.out.println("Admin changed! Username: " + username + "; pin: " + pin);
			AdminMenu.getAdminMenu();
			return true;
		}

		else {
			System.out.println("Something went wrong!");
			AdminMenu.getAdminMenu();
			return false;
		}
	}

}
