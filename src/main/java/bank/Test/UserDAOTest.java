package bank.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bank.DAO.UserDAO;
import bank.DAO.UserDAOImplementation;
import bank.DTO.Account;
import bank.DTO.Transaction;
import bank.DTO.User;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest {

	UserDAO mockUserDao;
	Account account;
	Account empty = new Account();
	Account targetAccount;
	List<Transaction> transactions = new ArrayList<>();

	@Before
	public void setUp() {
		mockUserDao = Mockito.mock(UserDAOImplementation.class);
		account = new Account("1111 2222 3333 4444", 2000, 1111,
				new User("Jasmina", "Kurtovic", "12DFR123", "1234567890123", "Jasmina@home.org", "061 111 111"));

		targetAccount = new Account("1212 1212 1212 1212", 10000, 3333,
				new User("Dino", "Kurtovic", "23DFR123", "2134567890123", "dino@home.org", "061 111 000"));

		account.deposit(1000);
		transactions.add(account.getTransaction());

		when(mockUserDao.withdrawOrDeposit(account)).thenReturn(true);
		when(mockUserDao.editProfile(account)).thenReturn(true);
		when(mockUserDao.getTransactions(account)).thenReturn(transactions);
		when(mockUserDao.Transact(account, targetAccount)).thenReturn(true);

	}

	@Test
	public void whenDepositCalledShouldReturnTrueIfDepositIsDone() {

		account.deposit(1000);
		boolean depositDone = mockUserDao.withdrawOrDeposit(account);

		assertTrue(depositDone);
	}

	@Test
	public void whenDepositCalledShouldReturnFalseIfDepositAmountIsLargerThanAllowed() {

		boolean done = account.deposit(1000000500);
		assertFalse(done);

	}

	@Test
	public void whenEditProfileCalledShouldReturnTrueIfAccountEdited() {

		account.getUser().setEmail("jaca@yahoo.com");
		boolean editedProfile = mockUserDao.editProfile(account);

		assertTrue(editedProfile);

	}

	@Test

	public void whenGetTransactionCalledShouldReturnListaWithAllTransactionsOfThatAccount() {
		List<Transaction> transanctionsTwo = mockUserDao.getTransactions(account);

		assertEquals(1, transanctionsTwo.size());
	}

	@Test
	public void whenTransactCalledShouldReturnTrueIfTransactionIsDone() {

		account.withdraw(200);
		targetAccount.deposit(200);

		boolean transactionDone = mockUserDao.Transact(account, targetAccount);
		assertTrue(transactionDone);

	}

	@Test
	public void whenTransactCalledShouldReturnFalseIfTransactionIsRolledBack() {

		boolean transactionDone = mockUserDao.Transact(account, empty);
		assertFalse(transactionDone);

	}

	@Test
	public void whenWithdrawCalledShouldReturnTrueIIsDone() {

		boolean done = account.withdraw(200);
		assertTrue(done);
	}

	@Test
	public void whenWithdrawCalledShouldReturnFalseIfAmountIsLargerThanBalance() {

		boolean done = account.withdraw(2000000);
		assertFalse(done);
	}

	@Test
	public void whenWithdrawOrDepositCalledShouldReturnTrueIIsDone() {

		account.withdraw(200);
		boolean done = mockUserDao.withdrawOrDeposit(account);
		assertTrue(done);
	}

	@Test
	public void whenWithdrawCalledShouldReturnFalseIfWithdrawRolledBack() {

		boolean done = mockUserDao.withdrawOrDeposit(empty);
		assertFalse(done);
	}

}
