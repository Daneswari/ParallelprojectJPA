package com.cg.paywallet.service;

import com.cg.paywallet.bean.Account;
import com.cg.paywallet.exception.PaywalletException;

public interface IPaywalletService {
	String createAccount(Account acc) throws PaywalletException;
	double showBalance(String mobileNo) 
			throws PaywalletException;
	Account deposit(String mobileNo,double depositAmount) throws PaywalletException;
	Account withdraw(String mobileNo,double withdrawAmount) throws PaywalletException;
	boolean fundTransfer(String sourceMobileNo,String destinationMobileNo,double transferAmount)
			throws PaywalletException;
	Account printTransactionDetails(String mobileNo) throws PaywalletException;

}
