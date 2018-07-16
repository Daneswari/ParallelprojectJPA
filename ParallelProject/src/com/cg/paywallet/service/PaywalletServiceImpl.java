package com.cg.paywallet.service;

import java.time.LocalDateTime;

import com.cg.paywallet.beans.Account;
import com.cg.paywallet.dao.IPaywalletDao;
import com.cg.paywallet.dao.PaywalletDaoImpl;
import com.cg.paywallet.exception.PaywalletException;

public class PaywalletServiceImpl implements IPaywalletService {
	IPaywalletDao dao=new PaywalletDaoImpl();

	@Override
	public String createAccount(Account acc) throws PaywalletException {
		// TODO Auto-generated method stub
		if (!acc.getMobileNo().matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		if (acc.getName().isEmpty() || acc.getName() == null) {
			throw new PaywalletException("Name cannot be empty");
		} else {
			if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {
				throw new PaywalletException("Name should start with capital letter and should contain only alphabets");
			}
		}
		if (acc.getEmail().matches("[a-z0-9]+@[a-z]+\\.com")) {
			throw new PaywalletException("Enter valid emailid");
		}
		if (acc.getBalance() <= 0) {
			throw new PaywalletException("Balance should be greater than zero");
		}
		return dao.createAccount(acc);
	}

	@Override
	public double showBalance(String mobileNo) throws PaywalletException {
		// TODO Auto-generated method stub
		if (!mobileNo.matches("\\d{10}")) {

			throw new PaywalletException("Mobile number should contain 10 digits");

		}
		return dao.showBalance(mobileNo);
	}

	@Override
	public Account deposit(String mobileNo, double depositAmt)
			throws PaywalletException {
		// TODO Auto-generated method stub
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		Account acc = dao.deposit(mobileNo);
		if (depositAmt <= 0) {
			throw new PaywalletException("Deposit amount must be greater than zero");
		}
		acc.setBalance(acc.getBalance() + depositAmt);
		acc.setDate(LocalDateTime.now());
		return acc;
	}

	@Override
	public Account withdraw(String mobileNo, double withdrawAmt)
			throws PaywalletException {
		// TODO Auto-generated method stub
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		Account acc = dao.withdraw(mobileNo);
		if (withdrawAmt > acc.getBalance() || withdrawAmt <= 0) {
			throw new PaywalletException(
					"The amount to be withdrawn should be greater than available balance and greater than zero");
		}
		acc.setBalance(acc.getBalance() - withdrawAmt);
		acc.setDate(LocalDateTime.now());
		return acc;
	}

	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmt) throws PaywalletException {
		// TODO Auto-generated method stub
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		if (!destMobileNo.matches("\\d{10}")) {
			throw new PaywalletException("Mobile number should contain 10 digits");
		}
		IPaywalletService service = new PaywalletServiceImpl();
		Account acc1 = service.withdraw(sourceMobileNo, transferAmt);
		Account acc2 = service.deposit(destMobileNo, transferAmt);
		return true;
	}

	@Override
	public Account printTransactionDetails(String mobileNo)
			throws PaywalletException {
		// TODO Auto-generated method stub
		return dao.printTransactionDetails(mobileNo);
	}

}
