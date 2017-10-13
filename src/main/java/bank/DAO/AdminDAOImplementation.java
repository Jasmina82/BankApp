package bank.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bank.DTO.Account;
import bank.DTO.User;

public class AdminDAOImplementation implements AdminDAO {

	Connection conn = ConnectionManager.getInstance().getConnection();

	// method returns list with all users currently online
	@Override
	public List<Account> getLoggedUsers() {
		
		List<Account> accounts = new LinkedList<>();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT accountNumber,balance,pinCode,firstName,lastName,idNumber,jmbg,email,phoneNumber,status FROM client_infos WHERE status='on'");) {
			while (rs.next()) {
				User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("idNumber"),
						rs.getString("jmbg"), rs.getString("email"), rs.getString("phoneNumber"));

				accounts.add(new Account(rs.getString("accountNumber"), rs.getDouble("balance"), rs.getInt("pinCode"),
						user,rs.getString("status")));
			}
		} catch (SQLException e) {

			System.err.println(e);
		}
		return accounts;
	}

	//returns all accounts
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new LinkedList<>();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT accountNumber,balance,pinCode,firstName,lastName,idNumber,jmbg,email,phoneNumber,status FROM client_infos");) {
			while (rs.next()) {
				User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("idNumber"),
						rs.getString("jmbg"), rs.getString("email"), rs.getString("phoneNumber"));

				accounts.add(new Account(rs.getString("accountNumber"), rs.getDouble("balance"), rs.getInt("pinCode"),
						user,rs.getString("status")));
			}
		} catch (SQLException e) {

			System.err.println(e);
		}
		return accounts;
	}
	
	@Override
	public boolean deleteUser(String accountNumber) {

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("DELETE  transactions FROM transactions WHERE accountNumber = '" + accountNumber + "';");
			stmt.executeUpdate("DELETE  client_infos FROM client_infos WHERE accountNumber = '" + accountNumber + "';");
			return true;

		} catch (SQLException e) {

			System.err.println(e);
			return false;
		}
	}
	
	// method edits account,only balance is allowed to edit
		@Override
		public boolean editBalance(Account account) {
		
			try (Statement stmt = conn.createStatement()) {

				stmt.executeUpdate("UPDATE client_infos SET balance=" + account.getBalance() + " WHERE accountNumber= '"
						+ account.getAccountNumber() + "';");
				return true;

			} catch (SQLException e) {

				System.err.println(e);
				return false;
			}
		}


}
