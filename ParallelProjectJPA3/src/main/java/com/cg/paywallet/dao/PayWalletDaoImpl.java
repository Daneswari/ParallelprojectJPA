package com.cg.paywallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.paywallet.bean.Account;
import com.cg.paywallet.exception.PaywalletException;

public class PayWalletDaoImpl implements IPaywalletDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hii");
	EntityManager em = emf.createEntityManager();

	public String createAccount(Account acc) throws PaywalletException {
		em.getTransaction().begin();
		em.persist(acc);
		em.getTransaction().commit();
		return acc.getMobileNo();
	}

	public double showBalance(String mobileNo) throws PaywalletException {
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(mobileNo.equals(ac.getMobileNo())) {
		return ac.getBalance();
		}else {
		throw new PaywalletException("number doesnot exists");
		}
	
	}

	public Account deposit(String mobileNo, double depAmount) throws PaywalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		
		if(ac==null) {
		throw new PaywalletException("does not exists");
		}
		double d=ac.getBalance()+depAmount;
		ac.setBalance(d);
		em.merge(ac);
		em.getTransaction().commit();
		return ac;

	}

	public Account withdraw(String mobileNo, double withdraw) throws PaywalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		throw new PaywalletException("does not exists");
		}
		double d=ac.getBalance()-withdraw;
		ac.setBalance(d);
		em.merge(ac);
		em.getTransaction().commit();
		return ac;

	}

	public Account printTransactionDetails(String mobileNo) throws PaywalletException {
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac!=null) {
		return ac;
		}else {
		throw new PaywalletException("number doesnot exists");
		}
		}

	}


