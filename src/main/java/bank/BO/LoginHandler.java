package bank.BO;

import java.util.Map;
import bank.main.AdminMenu;
import bank.main.Inputs;
import bank.main.UserMenu;

public class LoginHandler {
	
	static LoginHandler log=new LoginHandler();
	InformationHandler infos = new InformationHandler();
	
	//set values for admin
	static final String ADMINS_USERNAME="admin";
	static final int ADMINS_PIN=1000;

	public static void login() {

		String accountNumber = Inputs.getUserInput("Enter account number: ");
		int pinCode = Inputs.getUserIntegerInput("Enter pin: ");

		log.validateAdmin(accountNumber, pinCode);
	}

	// checks if user is admin
	public void validateAdmin(String accountNumber, int pinCode) {

		if (validateAdminsAccount(accountNumber) && validateAdminsPin(pinCode)) {
			AdminMenu.getAdminMenu();
		} else {
			validateAccount(accountNumber, pinCode);
		}
	}

	// method cheks if @param is admin's account
	public  boolean validateAdminsAccount(String accountNumber) {
		return accountNumber.equalsIgnoreCase(ADMINS_USERNAME);
	}

	// method checks if @param is admin's pin
	public  boolean validateAdminsPin(int pin) {
		return pin == ADMINS_PIN;
	}

	// method checks if account and pin are valid
	public void validateAccount(String accountNumber, int pinCode) {

		if (isAccountInList(accountNumber))
			validatePin(accountNumber, pinCode);
		else
			System.out.println("Wrong account number!Try again!");
	}

	// method cheks if account exists in db
	public boolean isAccountInList(String accountNumber) {
		
		Map<String, Integer> accountsWithPins = infos.getAccountNumbersWithPins();
		
		return accountsWithPins.containsKey(accountNumber);
	}

	// method checks if pin is valid for specific account
	public  void validatePin(String accountNumber, int pinCode) {
		UserMenu userMenu = new UserMenu(accountNumber);

		if (isPinValid(accountNumber, pinCode))
			userMenu.getUserMenu();
		else
			System.out.println("Wrong pin!");
	}

	// method checks if pin is valid
	public boolean isPinValid(String accountNumber, int pinCode) {
		
		Map<String, Integer> accountsWithPins = infos.getAccountNumbersWithPins();
		return accountsWithPins.get(accountNumber) == (pinCode);
	}
}
