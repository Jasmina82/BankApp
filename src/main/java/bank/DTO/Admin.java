package bank.DTO;

public class Admin extends Account{
	

	
	public Admin(String accountNumber,int pin) {
		this.accountNumber=accountNumber;
		this.pin=pin;
	}

	
	@Override
	public String toString() {
		return "Username " + accountNumber + "; pin: " + pin;
	}
}
