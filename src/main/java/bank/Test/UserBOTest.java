package bank.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bank.BO.UserBO;
import bank.DTO.Account;
import bank.DTO.User;
import static org.mockito.Mockito.when;

public class UserBOTest {

	UserBO mockUserBO;
	Account originAccount;
	Account targetAccount;
	Account emptyAccount = new Account();

	@Before
	public void setUp() {

		mockUserBO = Mockito.mock(UserBO.class);
		originAccount = new Account("1111 2222 3333 4444", 2000, 1111,
				new User("Jasmina", "Kurtovic", "12DFR123", "1234567890123", "Jasmina@home.org", "061 111 111"));

		targetAccount = new Account("1212 1212 1212 1212", 10000, 3333,
				new User("Dino", "Kurtovic", "23DFR123", "2134567890123", "dino@home.org", "061 111 000"));

		when(mockUserBO.completeTransaction(originAccount, targetAccount, 100)).thenReturn(true);
		when(mockUserBO.deposit(originAccount)).thenReturn(true);
		when(mockUserBO.withdraw(originAccount)).thenReturn(true);
		when(mockUserBO.editProfile(originAccount)).thenReturn(true);

	}

	@Test
	public void whenCompleteTransactionCalledShouldReturnTrueIfTransactionIsDone() {

		boolean done = mockUserBO.completeTransaction(originAccount, targetAccount, 100);

		assertTrue(done);
	}

	@Test
	public void whenCompleteTransactionCalledShouldReturnFalseIfTransactionIsRolledBack() {

		boolean done = mockUserBO.completeTransaction(originAccount, emptyAccount, 100);

		assertFalse(done);
	}

	@Test
	public void whenDepositCalledShouldReturnTrueIfDepositIsDone() {

		boolean done = mockUserBO.deposit(originAccount);

		assertTrue(done);
	}

	@Test
	public void whenDepositCalledShouldReturnFalseIfDepositIsRolledBack() {

		boolean done = mockUserBO.deposit(emptyAccount);

		assertFalse(done);
	}

	@Test
	public void whenWithdrawCalledShouldReturnTrueIfDepositIsDone() {

		boolean done = mockUserBO.withdraw(originAccount);

		assertTrue(done);
	}

	@Test
	public void whenWithdrawCalledShouldReturnFalseIfDepositIsRolledBack() {

		boolean done = mockUserBO.withdraw(emptyAccount);

		assertFalse(done);
	}

}
