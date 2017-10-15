package bank.BO;

import java.util.List;

import bank.DAO.AdminDAO;
import bank.DAO.AdminDAOImplementation;
import bank.DAO.UserDAO;
import bank.DAO.UserDAOImplementation;
import bank.DTO.Account;
import bank.DTO.Transaction;
import bank.main.Inputs;
import bank.main.UserMenu;

public class UserBO {

	UserDAO userDao = new UserDAOImplementation();
	AdminDAO adminDao = new AdminDAOImplementation();
	InformationHandler infos = new InformationHandler();
	LoginHandler log = new LoginHandler();

	// returns list of all transactions connetcted to this account
	public void getTransactions(Account account) {

		List<Transaction> transactions = userDao.getTransactions(account);

		System.out.println("Account: " + account.getAccountNumber());
		for (Transaction e : transactions) {
			System.out.println(e.toString());
		}
		System.out.println();
	}

	// transfer amount from originAccount to targetAccount and calls method to
	// record changes in database
	public boolean completeTransaction(Account origin,Account target,double amount) {

		origin.withdraw(amount);
		target.deposit(amount);

		if (userDao.Transact(origin,target)) {
			System.out.println("Transaction finished!");
			return true;
		} else {

			System.err.print("Transaction is being rolled back\n");
			// if transaction is rolled back,return amount to user's account so he can
			// continue without changes in app
			origin.deposit(amount);
			return false;
		}
	}

	public boolean editProfile(Account account) {

		System.out.println(account.getUser().toString());

		account = handleProfileOption(account);

		if (userDao.editProfile(account)) {
			System.out.println("Profile edited!");
			return true;
		}

		else {
			System.out.println("Something went wrong!");
			return false;
		}
	}

	// method handles user's inputs for editing profile
	public Account handleProfileOption(Account account) {
		int option = Inputs.getUserIntegerInput("1.Edit email\n2.Edit phone number\n3.Exit");
		switch (option) {

		case 1:
			String email = InputsValidator.getEmail("Enter email:");
			account.getUser().setEmail(email);
			break;
		case 2:
			String phoneNumber = InputsValidator.phone("Enter phone number:");
			account.getUser().setPhoneNumber(phoneNumber);
			break;
		case 3:
			System.out.println("Returning to main menu....");
			UserMenu user=new UserMenu();
			user.getUserMenu();
			break;
		default:
			System.out.println("Wrong option!");
		}
		return account;
	}

	// calls method to record changes to db
	public boolean withdraw(Account account) {

		if (userDao.withdrawOrDeposit(account)) {
			System.out.println("Withdraw successfully finished!Current balance: " + account.getBalance() + "\n");
			return true;
		} else {
			System.out.println("Withdraw rolled back!Balance is not changed!");
			return false;
		}
	}

	// calls method to record changes to db
	public boolean deposit(Account account) {

		if (userDao.withdrawOrDeposit(account)) {
			System.out.println("Deposit successfully finished!Current balance: " + account.getBalance() + "\n");
			return true;
		} else {
			System.out.println("Deposit rolled back!Balance is not changed!");
			return false;
		}
	}

}
