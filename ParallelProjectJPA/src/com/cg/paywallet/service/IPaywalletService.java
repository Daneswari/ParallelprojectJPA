package com.cg.paywallet.service;

import com.cg.paywallet.beans.Account;
import com.cg.paywallet.exception.PaywalletException;

public interface IPaywalletService {
	String createAccount(Account acc) throws PaywalletException;
	double showBalance(String mobileNo) throws PaywalletException;
	Account deposit(String mobileNo,double depositAmt) throws PaywalletException;
	Account withdraw(String mobileNo,double withdrawAmt) throws PaywalletException;
	boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmt) throws PaywalletException;
	Account printTransactionDetails(String mobileNo) throws PaywalletException;

}
