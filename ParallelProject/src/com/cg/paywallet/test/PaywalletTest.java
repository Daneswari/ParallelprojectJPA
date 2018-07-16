package com.cg.paywallet.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import com.cg.paywallet.beans.Account;
import com.cg.paywallet.exception.PaywalletException;
import com.cg.paywallet.service.IPaywalletService;
import com.cg.paywallet.service.PaywalletServiceImpl;

public class PaywalletTest {
	private IPaywalletService service;
	@Before
	public void init() {
		service = new PaywalletServiceImpl();
	}
	@Test
	public void testCreateAccountForMobile() {
		Account ac = new Account();
		ac.setMobileNo("9876543");
		ac.setName("Durga");
		ac.setEmail("durga@gmail.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (PaywalletException e) {
			Assert.assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForName() {
		Account ac = new Account();
		ac.setMobileNo("9876543210");
		ac.setName("john25");
		ac.setEmail("john@gmail.com");
		ac.setBalance(500.0);
		try {
			service.createAccount(ac);
		} catch (PaywalletException e) {
			Assert.assertEquals("Name should start with capital letter and should contain only alphabets", e.getMessage());
		}
	}
	
	@Test
	public void testCreateAccountForNameIsEmpty() {
		Account ac = new Account();
		ac.setMobileNo("1234567890");
		ac.setName("");
		ac.setEmail("dhanu@gmail.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (PaywalletException e) {
			Assert.assertEquals("Name cannot be empty", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForEmailId() {
		Account ac = new Account();
		ac.setMobileNo("1234567890");
		ac.setName("Krishna");
		ac.setEmail("krishna@25gmail.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (PaywalletException e) {
			Assert.assertEquals("Enter valid emailid", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {
		Account ac = new Account();
		ac.setMobileNo("1234567890");
		ac.setName("Satya");
		ac.setEmail("satya@gmail.com");
		ac.setBalance(200.0);
			try {
				String s=service.createAccount(ac);
				Assert.assertNotNull(s);
			} catch (PaywalletException e) {
				//System.out.println(e.getMessage());
			}
	}
	
	@Test
	public void testShowBalanceForMobileNo() {
		/*Account ac=new Account();
		ac.setMobileNo("95059345");*/
		try {
			service.showBalance("88765");
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testShowBalanceForMobileNoDoesNotExist() {
		/*Account ac=new Account();
		ac.setMobileNo("95059");*/
		try {
			service.showBalance("9988776611");
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The mobile number does not exist",e.getMessage());
		}
	}

	@Test
	public void testShowBalanceForName() {
		Account ac=new Account();
		ac.setMobileNo("9996765654");
		try {
			service.showBalance(ac.getMobileNo());
			assertEquals("Satya", ac.getName());
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The mobile number does not exist",e.getMessage());
		}
	}
	@Test
	public void testDepositForMobileNo() {
		Account ac=new Account();
		ac.setMobileNo("95059345");
		try {
			service.deposit(ac.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testDepositForMobileNo1() {
		Account ac=new Account();
		ac.setMobileNo("95059345");
		try {
			service.deposit(ac.getMobileNo(), -230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testDepositForMobileNoDoesNotExist() {
		Account ac=new Account();
		ac.setMobileNo("9976598765");
		try {
			service.deposit(ac.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The mobile number does not exist",e.getMessage());
		}
	}
	@Test
	public void testDepositForDepositAmt1() {
		Account ac=new Account();
		ac.setMobileNo("9988776655");
		try {
			service.deposit(ac.getMobileNo(), -230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Deposit amount must be greater than zero",e.getMessage());
		}
	}
	@Test
	public void testDeposit() {
		Account ac=new Account();
		ac.setMobileNo("9988776655");
		try {
			Account ac1=service.deposit(ac.getMobileNo(), 230);
			assertNotNull(ac1);
		} catch (PaywalletException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testWithDrawForMobileNo() {
		Account ac=new Account();
		ac.setMobileNo("99882233");
		try {
			service.withdraw(ac.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testWithDrawForMobileNo2() {
		Account ac=new Account();
		ac.setMobileNo("99sfdsgd33");
		try {
			service.withdraw(ac.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testWithdrawForMobileNoDoesNotExist() {
		Account ac=new Account();
		ac.setMobileNo("9876543210");
		try {
			service.withdraw(ac.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The mobile number does not exist",e.getMessage());
		}
	}
	@Test
	public void testWithdrawForAmt() {
		Account ac=new Account();
		ac.setMobileNo("9988776655");
		try {
			service.withdraw(ac.getMobileNo(), -230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	@Test
	public void testFundTransferForMobileNo() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("99887766");
		ac2.setMobileNo("1234");
		try {
			service.fundTransfer(ac.getMobileNo(),ac2.getMobileNo(), 230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testFundTransferForMobileNoDoesNotExist() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("9898766754");
		ac2.setMobileNo("9654786543");
		try {
			service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The mobile number does not exist",e.getMessage());
		}
	}
	@Test
	public void testFundTransferForAmt() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("9988776655");
		ac2.setMobileNo("9988776633");
		try {
			service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  -230);
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	@Test
	public void testFundTransfer() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("9988776655");
		ac2.setMobileNo("9988776633");
		try {
			assertTrue(service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  230));
		} catch (PaywalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testPrinttransactionDetails() {
		Account ac=new Account();
		ac.setMobileNo("9988776633");
		try {
			Account acc=service.printTransactionDetails(ac.getMobileNo());
			assertNotNull(acc);
		} catch (PaywalletException e) {
			System.out.println(e.getMessage());
		}
	}
}