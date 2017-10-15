package bank.DTO;

import java.util.Date;

public class Transaction {

	private Date date;
	private String type;
	private double ammount;
	private double balance;
	
	public Transaction() {
		
	}

	public Transaction(String type, double ammount, double balance) {
		this.type = type;
		this.ammount = ammount;
		this.balance = balance;
		date = new Date();
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return "Date: " + date + " Type: " + type + " Amount: " + ammount;
	}

}
