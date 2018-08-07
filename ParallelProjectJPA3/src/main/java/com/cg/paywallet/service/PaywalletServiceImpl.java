package com.cg.paywallet.service;

import com.cg.paywallet.bean.Account;
import com.cg.paywallet.dao.IPaywalletDao;
import com.cg.paywallet.dao.PayWalletDaoImpl;
import com.cg.paywallet.exception.PaywalletException;

public class PaywalletServiceImpl implements IPaywalletService {
	IPaywalletDao dao = new PayWalletDaoImpl();

	public String createAccount(Account acc) throws PaywalletException {
		if (!acc.getMobileNo().matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
			}
			if (acc.getName().isEmpty() || acc.getName() == null) {
			throw new PaywalletException("Name cannot be empty");
			} else {
			if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {
			throw new PaywalletException(
			"Name should start with capital letter and should contain only alphabets");
			}
			}
			if (acc.getBalance() < 0) {
			throw new PaywalletException("Balance should be greater than zero");
			}
			if (!acc.getEmail().matches("[a-z]+@[a-z]+\\.com")) {
			throw new PaywalletException("Enter valid emailid");
			}
			return dao.createAccount(acc);

	}

	public double showBalance(String mobileNo) throws PaywalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
			}
			return dao.showBalance(mobileNo);
	}

	public Account deposit(String mobileNo, double depositAmount) throws PaywalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
			}
			//Account acc=dao.findOne(mobileNo);
			if (depositAmount <= 0) {
			throw new PaywalletException(
			"Deposit amount must be greater than zero");
			}
			 
			return dao.deposit(mobileNo,depositAmount);
			
	}

	public Account withdraw(String mobileNo, double withdrawAmount) throws PaywalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		if ( withdrawAmount <= 0) {
		throw new PaywalletException(
		"The amount to be withdrawn should be greater than available balance and greater than zero");
		}
	
		Account acc1 = dao.withdraw(mobileNo,withdrawAmount);
		return acc1;

	}

	public boolean fundTransfer(String sourceMobileNo, String destinationMobileNo, double transferAmount)
			throws PaywalletException {
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
			}
			if (!destinationMobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
			}
			IPaywalletService service = new PaywalletServiceImpl();
			Account acc1 = service.withdraw(sourceMobileNo, transferAmount);
			Account d2 = service.deposit(destinationMobileNo, transferAmount);
			return true;
	}

	public Account printTransactionDetails(String mobileNo) throws PaywalletException {
		return dao.printTransactionDetails(mobileNo);
	}

}
