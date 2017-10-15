package bank.DTO;

public class Account {

	String accountNumber;
	double balance;
	int pin;
	User user;
	Transaction transaction;
	String status;

	public Account() {

	}

	public Account(String accountNumber, double balance, int pin, User user) {

		this.accountNumber = accountNumber;
		this.balance = balance;
		this.pin = pin;
		this.user = user;
	}

	public Account(String accountNumber, double balance, int pin, User user, String status) {

		this.accountNumber = accountNumber;
		this.balance = balance;
		this.pin = pin;
		this.user = user;
		this.status = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public User getUser() {
		return user;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean withdraw(double amount) {

		if (isWithdrawAllowed(amount)) {
			balance -= amount;
			transaction = new Transaction("W", amount, balance);
			return true;

		} else {
			System.out.println("Your limit is " + balance + "\n");
			return false;
		}
	}

	public boolean isWithdrawAllowed(double amount) {

		return (amount < balance);

	}

	public boolean deposit(double amount) {
		if (isDepositAllowed(amount)) {
			balance += amount;
			transaction = new Transaction("D", amount, balance);
			return true;
		} else {
			System.out.println("Maximum amount you can deposit is " + (1000000000 - balance) + "\n");
			return false;

		}
	}

	public boolean isDepositAllowed(double amount) {

		return (amount + balance) < 1000000000;

	}

	public String toString() {
		return "Account: " + accountNumber + " ; PIN : " + pin + "\nBalance: " + balance + " ; ";
	}
}
