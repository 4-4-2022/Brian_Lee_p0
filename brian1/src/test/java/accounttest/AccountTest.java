package accounttest;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Brian.model.Account;
;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AccountTest {
	private Account account;
	private Account account2;
	private Account account3;
	private Account account4;
	private Account account5;
	
	@BeforeAll
	public void setup() {
		account = new Account(1, 750f, 1);
		account2 = new Account(2, 550.56f, 1);
		account3 = new Account(3, 666f, 2);
		account4 = new Account(4, 100f, 3);
		account5 = new Account(5, 100f, 3);
	}
	
	@Test
	public void TestAddFundsInst() {
		account2.addFundsInst(0);
		Assertions.assertEquals(550.56f, account2.getFunds());
	}
	@Test
	public void TestAddFundsInst2() {
		account.addFundsInst(-1000);
		Assertions.assertEquals(750f, account.getFunds());
	}
	@Test
	public void TestAddFundsInst3() {
		account.addFundsInst(-500);
		Assertions.assertEquals(750f, account.getFunds());
	}
	@Test
	public void TestAddFundsInst4() {
		account.addFundsInst(500);
		Assertions.assertEquals(1250f, account.getFunds());
	}
	@Test
	public void TestReduceFundsInst() {
		account3.reduceFundsInst(-500);
		Assertions.assertEquals(666, account3.getFunds());
	}
	@Test
	public void TestReduceFundsInst2() {
		account3.reduceFundsInst(0);
		Assertions.assertEquals(666, account3.getFunds());
	}
	@Test
	public void TestReduceFundsInst3() {
		account5.reduceFundsInst(150);
		Assertions.assertEquals(100, account5.getFunds());
	}
	@Test
	public void TestReduceFundsInst4() {
		account4.reduceFundsInst(100);
		Assertions.assertEquals(0, account4.getFunds());
	}
	@Test
	public void TestBalanceXfer1() {
		Account accountx = new Account(10,100,10);
		Account accounty = new Account(10,100,10);
		Account.transferFundsInst(accountx, accounty, 0);
		Assertions.assertEquals(100, accountx.getFunds());
		Assertions.assertEquals(100, accounty.getFunds());
	}
	
	@Test
	public void TestBalanceXfer2() {
		Account accountm = new Account(10,100,10);
		Account accountn= new Account(10,100,10);
		Account.transferFundsInst(accountm, accountn, 100);
		Assertions.assertEquals(0, accountn.getFunds());
		Assertions.assertEquals(200, accountm.getFunds());
		
	}
	@Test
	public void TestBalanceXfer3() {
		Account accounta = new Account(10,100,10);
		Account accountb= new Account(10,100,10);
		Account.transferFundsInst(accounta, accountb, 1000);
		Assertions.assertEquals(100, accountb.getFunds());
		Assertions.assertEquals(100, accounta.getFunds());
	}
	@Test
	public void TestBalanceXfer4() {
		Account accountc = new Account(10,100,10);
		Account accountd= new Account(10,100,10);
		Account.transferFundsInst(accountc, accountd, -100);
		Assertions.assertEquals(200, accountd.getFunds());
		Assertions.assertEquals(0, accountc.getFunds());
		
	}
	
	
}
