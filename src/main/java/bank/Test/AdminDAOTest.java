package bank.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bank.DAO.AdminDAO;
import bank.DAO.AdminDAOImplementation;
import bank.DTO.Account;
import bank.DTO.User;

public class AdminDAOTest {

	AdminDAO mockAdminDao;
	Account account;
	List<Account> allAccounts = new ArrayList<>();
	List<Account> allLoggedUsers = new ArrayList<>();

	@Before
	public void setUp() {
		mockAdminDao = Mockito.mock(AdminDAOImplementation.class);

		List<Account> allAccounts = new ArrayList<>();
		List<Account> allLoggedUsers = new ArrayList<>();

		account = new Account("1111 2222 3333 4444", 3271, 1111,
				new User("Jasmina", "Kurtovic", "12DFR123", "1234567890123", "Jasmina@home.org", "061 111 111"), "on");

		allAccounts.add(account);
		allLoggedUsers.add(account);

		Mockito.when(mockAdminDao.getAllAccounts()).thenReturn(allAccounts);
		Mockito.when(mockAdminDao.getLoggedUsers()).thenReturn(allLoggedUsers);
		Mockito.when(mockAdminDao.deleteUser("1111 2222 3333 4444")).thenReturn(true);
		Mockito.when(mockAdminDao.editBalance(account)).thenReturn(true);

	}

	@Test
	public void whenGetAllAccountsCalledShouldReturnListWithAllAccounts() {

		allAccounts = mockAdminDao.getAllAccounts();
		assertEquals(1, allAccounts.size());

	}

	@Test
	public void whenGetLoggedUsersCalledShouldReturnListWithAllUsersLoggedOn() {

		allLoggedUsers = mockAdminDao.getLoggedUsers();

		assertEquals("on", allLoggedUsers.get(0).getStatus());

	}

	@Test
	public void whenGetLoggedUsersCalledShouldReturnEmptyListIfAllUsersLoggedOff() {

		allLoggedUsers = mockAdminDao.getLoggedUsers();
		allLoggedUsers.clear();
		assertTrue(allLoggedUsers.isEmpty());

	}

	@Test
	public void whenDeleteUserCalledShouldReturnTrueIfUserIsDeleted() {

		boolean deleted = mockAdminDao.deleteUser("1111 2222 3333 4444");

		assertTrue(deleted);

	}

	@Test
	public void whenDeleteUserCalledShouldReturnFalseIfSomethingIsWrong() {

		boolean deleted = mockAdminDao.deleteUser("1111 2222 3333 5555");

		assertFalse(deleted);
	}

	@Test
	public void whenEditBalanceCalledShouldReturnTrueIfBalanceIsEdited() {

		account.setBalance(1111);

		boolean balanceEdited = mockAdminDao.editBalance(account);

		assertTrue(balanceEdited);
	}

}
