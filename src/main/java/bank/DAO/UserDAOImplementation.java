package bank.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import bank.DTO.Transaction;
import bank.DTO.Account;

public class UserDAOImplementation implements UserDAO {
	
	Connection conn = ConnectionManager.getInstance().getConnection();
	
	// records balance after withdraw or deposit
	@Override
	public boolean withdrawOrDeposit(Account account) {
		
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("UPDATE client_infos SET balance=" + account.getBalance() + " WHERE accountNumber= '"
					+ account.getAccountNumber() + "';");
			stmt.executeUpdate(
					"INSERT INTO transactions (amount,balance,type_transaction,date_transaction,accountNumber) VALUES("
							+ account.getTransaction().getAmmount() + "," + account.getTransaction().getBalance() + ",'"
							+ account.getTransaction().getType() + "','" + account.getTransaction().getDate() + "','"
							+ account.getAccountNumber() + "');");
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	// method edits user's profile informations
	public boolean editProfile(Account account) {
		
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("UPDATE client_infos SET email='" + account.getUser().getEmail() + "',phoneNumber='"
					+ account.getUser().getPhoneNumber() + "' WHERE accountNumber= '" + account.getAccountNumber()
					+ "';");
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	// records transaction from one account to another
	@Override
	public boolean Transact(Account originAccount, Account targetAccount) {
		boolean done = false;
		Connection conn = ConnectionManager.getInstance().getConnection();
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			stmt.executeUpdate("UPDATE client_infos SET balance=" + originAccount.getBalance()
					+ " WHERE accountNumber= '" + originAccount.getAccountNumber() + "';");
			stmt.executeUpdate("UPDATE client_infos SET balance=" + targetAccount.getBalance()
					+ " WHERE accountNumber= '" + targetAccount.getAccountNumber() + "';");

			stmt.executeUpdate(
					" INSERT INTO transactions (amount,balance,type_transaction,date_transaction,accountNumber) VALUES("
							+ originAccount.getTransaction().getAmmount() + ","
							+ originAccount.getTransaction().getBalance() + ",'"
							+ originAccount.getTransaction().getType() + "','"
							+ originAccount.getTransaction().getDate() + "','" + originAccount.getAccountNumber()
							+ "');");
			stmt.executeUpdate(
					" INSERT INTO transactions (amount,balance,type_transaction,date_transaction,accountNumber) VALUES("
							+ targetAccount.getTransaction().getAmmount() + ","
							+ targetAccount.getTransaction().getBalance() + ",'"
							+ targetAccount.getTransaction().getType() + "','"
							+ targetAccount.getTransaction().getDate() + "','" + targetAccount.getAccountNumber()
							+ "');");

			conn.commit();
			done = true;
		} catch (SQLException e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				done = false;
			}
			done= false;
		}
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			done = false;
		}
		 return done;
	}

	// returns list with all account's transactions
	@Override
	public List<Transaction> getTransactions(Account account) {
	
		List<Transaction> transactions = new LinkedList<>();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT amount,balance,type_transaction,date_transaction FROM transactions WHERE accountNumber='"
								+ account.getAccountNumber() + "';");) {
			while (rs.next()) {
				transactions.add(new Transaction(rs.getString("type_transaction"), rs.getDouble("amount"),
						rs.getDouble("balance")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	// sets status of user on if online or off if he's logged out
	public boolean setStatus(String status, String accountNumber) {
	
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(
					"UPDATE client_infos SET status='" + status + "' WHERE accountNumber= '" + accountNumber + "';");
		return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

}
