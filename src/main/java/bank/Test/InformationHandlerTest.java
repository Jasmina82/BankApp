package bank.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import bank.BO.InformationHandler;
import bank.DTO.Account;
import bank.DTO.User;

public class InformationHandlerTest {

	InformationHandler mockLog;
	InformationHandler info=new InformationHandler();
	List<Account> accounts = new ArrayList<>();
	Map<String,Integer> accountsWithPins= new HashMap<>();
	Account account;
	Account accountTwo;

	@Before
	public void setUp() throws Exception {
		mockLog = Mockito.mock(InformationHandler.class);

			    account=new Account("1111 2222 3333 4444", 3271, 1111,
				new User("Jasmina", "Kurtovic", "12DFR123", "1234567890123", "Jasmina@home.org", "061 111 111"),"off");
			    accountTwo=new Account("1212 1212 1212 1212", 10000, 3333,
						new User("Dino", "Kurtovic", "23DFR123", "2134567890123", "dino@home.org", "061 111 000"),"off");
				accounts.add(account);
				accounts.add(accountTwo);
				accountsWithPins.put("1111 2222 3333 4444", 1111);
				accountsWithPins.put("1212 1212 1212 1212", 3333);
				
				when(mockLog.getAccount("1111 2222 3333 4444")).thenReturn(account);
				when(mockLog.getAccountNumbersWithPins()).thenReturn(accountsWithPins);
				
	}

	@Test
	public void whenGetAccountCalledShouldReturnAccountBasedOnAccountNumber() {
		
		String accountNumber="1111 2222 3333 4444";		
		Account accountLog=info.getAccount(accountNumber);
		assertEquals("1111 2222 3333 4444",accountLog.getAccountNumber());
	  
	}
	@Test
	public void whenGetAccountCalledShouldReturnNullIfAccountNumberDoesntExist() {
		
		String accountNumber="5555 5555 5555 5555";		
		Account accountLog=info.getAccount(accountNumber);
		assertEquals(null,accountLog.getAccountNumber());
	  
	}
		
	@Test
	public void whenGetAccountNumbersWithPinsCalledShouldReturnListWithAccountNumbersAndPins() {
		Map<String,Integer> list=info.getAccountNumbersWithPins();
		int pin=list.get("1111 2222 3333 4444");
		assertEquals(1111,pin);
	}

	
	
	

	

}
