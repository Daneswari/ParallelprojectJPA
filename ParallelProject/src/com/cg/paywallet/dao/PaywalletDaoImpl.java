package com.cg.paywallet.dao;

import java.util.HashMap;

import com.cg.paywallet.beans.Account;
import com.cg.paywallet.db.PaywalletDb;
import com.cg.paywallet.exception.PaywalletException;

public class PaywalletDaoImpl implements IPaywalletDao {
	private static HashMap<String,Account> paywalletMap=PaywalletDb.getPaywallet();

	@Override
	public String createAccount(Account acc) throws PaywalletException {
		// TODO Auto-generated method stub
		paywalletMap.put(acc.getMobileNo(),acc);
		return acc.getMobileNo();
	}

	@Override
	public double showBalance(String mobileNo) throws PaywalletException {
		// TODO Auto-generated method stub
		Account acc=paywalletMap.get(mobileNo);
		if(acc==null){
			throw new PaywalletException("The mobile number does not exist");
		}
		return acc.getBalance();
	}

	@Override
	public Account deposit(String mobileNo) throws PaywalletException {
		// TODO Auto-generated method stub
		Account acc=paywalletMap.get(mobileNo);
		if(acc==null){
			throw new PaywalletException("The mobile number does not exist");
		}
		return acc;
	}

	@Override
	public Account withdraw(String mobileNo) throws PaywalletException {
		// TODO Auto-generated method stub
		Account acc=paywalletMap.get(mobileNo);
		if(acc==null){
			throw new PaywalletException("The mobile number does not exist");
		}
		return acc;
	}

	@Override
	public Account printTransactionDetails(String mobileNo)
		 throws PaywalletException {
		// TODO Auto-generated method stub
		Account acc=paywalletMap.get(mobileNo);
		if(acc==null){
			throw new PaywalletException("The mobile number does not exist");
		}
		
		return acc;
	}

}
