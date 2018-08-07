package com.cg.paywallet.beans;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Account {
	@Id
    @GeneratedValue
	private int id;
	private String mobileNo;
	private String name;
	private String email;
	private double balance;
	Date date;
    public Account() {
			super();		
		}
	public Account(String name, String mobileNo, String email, double balance,
				Date date) {
			super();
			this.name = name;
			this.mobileNo = mobileNo;
			this.email = email;
			this.balance = balance;
			this.date = date;
		}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
