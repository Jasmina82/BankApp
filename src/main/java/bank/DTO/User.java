package bank.DTO;

public class User {

	// data fields
	String firstName;
	String lastName;
	String email;
	String idNumber;
	String phoneNumber;
	String jmbg;

	// no-arg constructor
	public User() {

	}

	public User(String firstName, String lastName, String idNumber, String jmbg, String email, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	@Override
	public String toString() {
		return "Client: " + firstName + " " + lastName + "; id number:" + idNumber + "; JMBG:" + jmbg + "; email:"
				+ email + "; phone number:" + phoneNumber;
	}

}
