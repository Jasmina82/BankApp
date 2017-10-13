package bank.BO;

import bank.DAO.GuestDAO;
import bank.DAO.GuestDAOImplementation;
import bank.DTO.Account;
import bank.main.App;
import bank.main.Inputs;
import bank.main.UserMenu;

public class GuestBO {

	InformationHandler infos = new InformationHandler();
	GuestDAO guestDao = new GuestDAOImplementation();
	LoginHandler log = new LoginHandler();

	// handles register request
	public void registerNewUser() {

		Account newAccount = infos.getNewUserInfos();

		// record new user in db BankJK
		if (guestDao.registerNewUser(newAccount)) {
			System.out.println(newAccount.toString() + "\n" + newAccount.getUser().toString() + "\n");
			UserMenu userMenu = new UserMenu(newAccount.getAccountNumber());
			userMenu.getUserMenu();
		} else if (!guestDao.registerNewUser(newAccount))
			System.out.println("Something went wrong!");

	}

	// method deposits amount to specific account from guest
	public void deposit() {

		String accountForDeposit = InputsValidator
				.getAccountFromPerson("Enter account (example: XXXX XXXX XXXX XXXX) ");

		if (log.isAccountInList(accountForDeposit)) {
			Account account = infos.getAccount(accountForDeposit);
			double amountForDeposit = Inputs.getUserDoubleInput("Enter amount:");
			finishDeposit(account, amountForDeposit);
		} else
			System.out.println("Account doesn't exist!");
	}

	public void finishDeposit(Account account, double amountForDeposit) {

		if (account.deposit(amountForDeposit))
			guestDao.deposit(account);

		App.startApp();
	}

}
