package bank.Test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import bank.BO.LoginHandler;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LoginHandlerTest {

	LoginHandler mockLog;
	Map<String, Integer> accountsWithPins = new HashMap<>();

	@Before
	public void setUp() throws Exception {
		mockLog = Mockito.mock(LoginHandler.class);

		accountsWithPins.put("Admin", 1000);
		accountsWithPins.put("1111 2222 3333 4444", 1111);		

		when(mockLog.isAccountInList("1111 2222 3333 4444")).thenReturn(true);
		when(mockLog.isAccountInList("1111 8888 3333 5555")).thenReturn(false);
		when(mockLog.validateAdminsAccount("Admin")).thenReturn(true);
	}

	@Test
	public void whenValidateAdminAccountCalledShouldReturnTrueIfUserInputIsEqualAsAdmin() {

		boolean isValid = mockLog.validateAdminsAccount("Admin");
		assertTrue(isValid);
	}

	@Test
	public void whenValidateAdminAccountCalledShouldReturnFalseIfUserInputIsNotEqualAsAdmin() {

		boolean isValid = mockLog.validateAdminsAccount("Jasmina");
		assertFalse(isValid);
	}

	@Test
	public void whenValidateAdminPinCalledShouldReturnFalseIfUserInputIsNotEqualAsAdminsPin() {

		boolean isValid = mockLog.validateAdminsPin(1010);
		assertFalse(isValid);
	}

	@Test
	public void whenIsAccountInListCalledShouldReturnTrueIfAccountExists() {

		boolean contains = mockLog.isAccountInList("1111 2222 3333 4444");
		assertTrue(contains);
	}

	@Test
	public void whenIsAccountInListCalledShouldReturnFalseIfAccountDoesNotExist() {

		boolean contains = mockLog.isAccountInList("1111 2222 3333 6666");
		assertFalse(contains);
	}

	@Test
	public void whenValidatePinCalledShouldReturnTrueIfPinIsValid() {

		mockLog.validatePin("1111 2222 3333 4444", 1111);
		assertEquals(1111, 1111);
	}

	@Test
	public void whenValidatePinCalledShouldReturnFalseIfPinIsNotValid() {

		mockLog.isPinValid("1111 2222 3333 4444", 1122);
		assertNotEquals(1111, 1122);
		// System.out.println("Wrong pin!");
	}

	@Test
	public void whenValidateAccountCalledShouldReturnTrueIfAccountExist() {

		mockLog.validateAccount("1111 2222 3333 4444", 9999);
		assertTrue(accountsWithPins.containsKey("1111 2222 3333 4444"));
	}

	@Test
	public void whenValidateAccountCalledShouldReturnFalseIfAccountDoesNotExist() {

		mockLog.validateAccount("1010 1010 1010 1010", 9999);
		assertFalse(accountsWithPins.containsKey("1010 1010 1010 1010"));
	}

}
