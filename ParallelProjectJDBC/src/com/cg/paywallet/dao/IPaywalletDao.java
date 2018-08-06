package com.cg.paywallet.dao;

import com.cg.paywallet.beans.Account;
import com.cg.paywallet.exception.PaywalletException;

public interface IPaywalletDao {
	String createAccount(Account acc) throws PaywalletException;
	double showBalance(String mobileNo) throws PaywalletException;
	Account deposit(String mobileNo, double deposit) throws PaywalletException;
	Account withdraw(String mobileNo, double withdraw) throws PaywalletException;
	Account printTransactionDetails(String mobileNo) throws PaywalletException;
}
