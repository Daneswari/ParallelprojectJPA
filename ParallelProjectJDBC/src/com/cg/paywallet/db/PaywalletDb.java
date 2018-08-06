package com.cg.paywallet.db;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.cg.paywallet.beans.Account;

public class PaywalletDb {
	private static HashMap<String,Account> paywalletDb=new HashMap<String,Account>();
	public static HashMap<String,Account>getPaywallet(){
		return paywalletDb;
	}
	Account obj=new Account();
	static{
		paywalletDb.put("9988776655", new Account("9988776655","Teja","teja@gmail.com",2000.0,LocalDateTime.now()));
		paywalletDb.put("9988776644", new Account("9988776644","Hema","hema@gmail.com",1000.0,LocalDateTime.now()));
		paywalletDb.put("9988776633", new Account("9988776633","Satya","satya@gmail.com",1500.0,LocalDateTime.now()));
		paywalletDb.put("9988776622", new Account("9988776622","Shiva","shiva@gmail.com",2000.0,LocalDateTime.now()));
	}

}
