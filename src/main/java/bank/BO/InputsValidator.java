package bank.BO;

import bank.main.Inputs;

public class InputsValidator {

	// validates email input
	public static String getEmail(String message) {

		String email = "";
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		while (!email.matches(pattern))
			email = Inputs.getUserInput(message);

		return email;
	}

	// validates phone number input XXX XXX XXX OR XXX XXX
	public static String phone(String message) {

		String phone = "";

		// 061 111 111; 717 717
		String pattern1 = "(\\d{3})?[\\s-]?\\d{3}[\\s-]?\\d{3}";
		while (!phone.matches(pattern1))
			phone = Inputs.getUserInput(message);

		return phone;
	}

	// validates JMBG ( 13 numbers )
	public static String jmbg(String message) {

		String jmbg = "";
		String pattern1 = "[0-9]{13}";
		while (!jmbg.matches(pattern1))
			jmbg = Inputs.getUserInput(message);

		return jmbg;
	}

	// validates ID number ( 12HJK1234)
	public static String idNumber(String message) {

		String idNumber = "";
		String pattern1 = "[0-9]{0,3}[a-z]{2,5}[0-9]{4,8}";
		while (!idNumber.matches(pattern1))
			idNumber = Inputs.getUserInput(message);

		return idNumber.toUpperCase();
	}

	// validates account input ( XXXX XXXX XXXX XXXX)
	public static String getAccountFromPerson(String message) {

		String account = "";

		String pattern = "\\d{4}[\\s]\\d{4}[\\s]\\d{4}[\\s]\\d{4}";

		while (!account.matches(pattern))
			account = Inputs.getUserInput(message);

		return account;
	}

	// validates pin input,four integer allowed (XXXX)
	public static int getPinInput(String message) {

		int pin = 0;

		while (pin < 1000 || pin > 9999)
			pin = Inputs.getUserIntegerInput(message);

		return pin;
	}

}
