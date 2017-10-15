package bank.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import bank.DTO.Account;

public class GuestDAOImplementation implements GuestDAO {

	Connection conn = ConnectionManager.getInstance().getConnection();

	public GuestDAOImplementation() {

	}

	// guest deposits amount to specific account,change is recorded in db - balance
	// of specific account increased,transaction recorded for that account
	@Override
	public void deposit(Account account) {

		// increasing balance for @param amount
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			stmt.executeUpdate("UPDATE client_infos SET balance=" + account.getBalance() + " WHERE accountNumber= '"
					+ account.getAccountNumber() + "';");

			stmt.executeUpdate(
					"INSERT INTO transactions (amount,balance,type_transaction,date_transaction,accountNumber) VALUES("
							+ account.getTransaction().getAmmount() + "," + account.getTransaction().getBalance() + ",'"
							+ account.getTransaction().getType() + "','" + account.getTransaction().getDate() + "','"
							+ account.getAccountNumber() + "');");

			System.out.println("\nYour deposit is successfuly finished!\n");
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					System.err.println(e);
				}

			}
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public boolean registerNewUser(Account account) {

		try (Statement stmt = conn.createStatement();) {

			stmt.executeUpdate(
					"INSERT INTO client_infos (accountNumber,balance,pinCode,firstName,lastname,idNumber,jmbg,email,phoneNumber) "
							+ " VALUES('" + account.getAccountNumber() + "'," + account.getBalance() + ","
							+ account.getPin() + ",'" + account.getUser().getFirstName() + "','"
							+ account.getUser().getLastName() + "','" + account.getUser().getIdNumber() + "','"
							+ account.getUser().getJmbg() + "','" + account.getUser().getEmail() + "','"
							+ account.getUser().getPhoneNumber() + "');");

			return true;

		} catch (SQLException e) {

			System.err.println(e);
			return false;
		}

	}

}
