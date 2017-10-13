package bank.main;

import bank.BO.GuestBO;
import bank.BO.LoginHandler;

public class GuestMenu {

	GuestBO guest = new GuestBO();

	public void guestMenu() {
		int option = Inputs.getUserIntegerInput("1.Register\t2.Login\t\t3.Deposit");
		handleOption(option);
	}

	public void handleOption(int option) {

		switch (option) {

		case 1:
			guest.registerNewUser();
			break;
		case 2:
			LoginHandler.login();
			break;
		case 3:
			guest.deposit();
			break;
		default:
			System.out.println("Wrong option!");
		}
	}
}
