package bank.main;

import bank.BO.AdminBO;
import bank.BO.LoginHandler;
import bank.DAO.AdminDAO;
import bank.DAO.AdminDAOImplementation;

public class AdminMenu {

	LoginHandler log = new LoginHandler();
	static AdminDAO adminDao = new AdminDAOImplementation();
	static AdminBO adminBo = new AdminBO();

	
	// prints menu
	public static void getAdminMenu() {

		int adminOption = Inputs
				.getUserIntegerInput("1.Logged users\n2.Delete account\n3.Edit user's balance\n4.Edit user's profile"
						+ "\n5.Create new user" + "\n6.Change admin" + "\n7.Log out");

		handleUserOption(adminOption);

	}

	// handles admin's option
	public static void handleUserOption(int adminOption) {

		switch (adminOption) {

		case 1:
			adminBo.loggedUsers();
			break;
		case 2:
			adminBo.deleteAccount();
			break;
		case 3:
			adminBo.editBalance();
			break;
		case 4:
			adminBo.editProfile();break;
		case 5:
			adminBo.createNewUser();break;
		case 6:adminBo.changeAdmin();break;
		case 7:
			System.out.println("Logging out......\n");
			App.startApp();
			break;
		default:
			System.out.println("Wrong option!");

		getAdminMenu();

		}

	}
	

}
