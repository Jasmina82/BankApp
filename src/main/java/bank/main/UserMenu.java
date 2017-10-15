package bank.main;

import bank.BO.InformationHandler;
import bank.BO.InputsValidator;
import bank.BO.LoginHandler;
import bank.BO.UserBO;
import bank.DAO.UserDAO;
import bank.DAO.UserDAOImplementation;
import bank.DTO.Account;
import bank.DTO.Transaction;

public class UserMenu {

	String accountNumber;
	UserBO userBO = new UserBO();
	UserDAO userDao = new UserDAOImplementation();
	InformationHandler infos = new InformationHandler();
	Transaction transaction = new Transaction();
	LoginHandler log = new LoginHandler();

	Account account;

	// No-arg constructor
	public UserMenu() {

	}

	// Constructor with parametar for accountNumber data field
	public UserMenu(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void getUserMenu() {
		userDao.setStatus("on", accountNumber);
		int userOption = Inputs.getUserIntegerInput(
				"1.Check balance\t  2.Withdraw\t3.Deposit\t4.Transaction\t 5.Edit profile" + "\t 6.Log out");
		handleUserOption(userOption);
	}

	public void handleUserOption(int userOption) {

		account = infos.getAccount(accountNumber);
		double amount = 0;

		switch (userOption) {
		case 1:
			System.out.println("Account: " + accountNumber + " ; Current balance: " + account.getBalance() + ";\n");
			break;
		case 2:
			amount = Inputs.getUserDoubleInput("Enter amount:");
			if (account.withdraw(amount))
				userBO.withdraw(account);
			break;
		case 3:
			amount = Inputs.getUserDoubleInput("Enter amount:");
			if (account.deposit(amount))
				userBO.deposit(account);
			break;
		case 4:
			transactions();
			break;
		case 5:
			userBO.editProfile(account);
			break;
		case 6:
			userDao.setStatus("off", accountNumber);
			System.out.println("Logging out......");
			App.startApp();
			break;
		default:
			System.out.println("Wrong option!");
		}

		getUserMenu();
	}

	// prints transaction options and calls corresponding methods
	public void transactions() {
		int transactionOption = Inputs.getUserIntegerInput("1.Do transaction\n2.See transactions\n3.Exit");

		switch (transactionOption) {

		case 1:
			double amount = Inputs.getUserDoubleInput("Enter amount: ");
			String accountFoRTransaction = InputsValidator
					.getAccountFromPerson("Enter account number for transaction:");
			checkDatasForTransaction(accountFoRTransaction, amount);
			break;
		case 2:
			userBO.getTransactions(account);
			break;
		case 3:
			System.out.println("Returning to main menu......\n");
			break;
		default:
			System.out.println("Wrong option!");

		}
	}

	// checks if user has enough founds to transfer amount and if target account can
	// receive that amount,based on those results,calls method
	public void checkDatasForTransaction(String accountNumberForTransaction, double amount) {

		if (!log.isAccountInList(accountNumberForTransaction)) {
			System.out.println("Account " + accountNumberForTransaction + " doesn't exist!");
			transactions();
		}

		Account targetAccount = infos.getAccount(accountNumberForTransaction);

		if(account.isWithdrawAllowed(amount) && targetAccount.isDepositAllowed(amount))
			     userBO.completeTransaction(account,targetAccount,amount);

	}

}
