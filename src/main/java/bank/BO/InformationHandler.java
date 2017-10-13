package bank.BO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.DAO.AdminDAO;
import bank.DAO.AdminDAOImplementation;
import bank.DTO.Account;
import bank.DTO.User;
import bank.main.Inputs;

public class InformationHandler {

	AdminDAO adminDao = new AdminDAOImplementation();
	List<Account> accounts = new ArrayList<>();

	//method returns list of accounts
	public Map<String, Integer> getAccountNumbersWithPins() {

		accounts = adminDao.getAllAccounts();

		Map<String, Integer> accountsWithPins = new HashMap<>();

		if (!accounts.isEmpty())
			for (Account e : accounts) {
				accountsWithPins.put(e.getAccountNumber(), e.getPin());
			}
		return accountsWithPins;
	}
	// method returns user's account object
	public Account getAccount(String accountNumber) {
	
		accounts = adminDao.getAllAccounts();
		Account account = new Account();

		for (Account e : accounts) {
			if (accountNumber.equals(e.getAccountNumber())) {
				account = e;
			}
		}
		return account;
	}
	
	//getting information about new user	
		public Account getNewUserInfos() {
			
			String firstName = Inputs.getUserInput("Enter first name:");
			String lastName = Inputs.getUserInput("Enter last name:");
			String idNumber = InputsValidator.idNumber("Enter id number:");
			String jmbg = InputsValidator.jmbg("Enter JMBG:");
			String email = InputsValidator.getEmail("Enter email:");
			String phoneNumber = InputsValidator.phone("Enter phone number:");

			User newUser = new User(firstName, lastName, idNumber, jmbg, email, phoneNumber);

			double balance = Inputs.getUserDoubleInput("Enter amount:");

			String accountNumber = generateAccountNumber();
			int pin = generatePin();

			Account newAccount = new Account(accountNumber, balance, pin, newUser);
			
			
			return newAccount;
		}

		// method randomly generates account number
		public String generateAccountNumber() {
			Map<String, Integer> accountNumbers = getAccountNumbersWithPins();
			String accountNum = " ";
			while (accountNumbers.containsKey(accountNum) || accountNum.equals(" ")) {
				accountNum = ((int) (1000 + (Math.random() * 9000))) + " " + ((int) (1000 + (Math.random() * 9000))) + " "
						+ ((int) (1000 + (Math.random() * 9000))) + " " + ((int) (1000 + (Math.random() * 9000)));
			}
			return accountNum;

		}

		// method generates pin randomly
		public int generatePin() {
			int pin = (int) (1000 + (Math.random() * 9000));
			return pin;
		}


}