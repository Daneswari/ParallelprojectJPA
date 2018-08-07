package com.cg.paywallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.cg.paywallet.beans.Account;
import com.cg.paywallet.exception.PaywalletException;

public class PaywalletDaoImpl implements IPaywalletDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hi");	 
	EntityManager em = emf.createEntityManager();

	@Override
	public String createAccount(Account acc) throws PaywalletException {
		em.getTransaction().begin();
		em.persist(acc);
		em.getTransaction().commit();
		return acc.getMobileNo();
	}

	@Override
	public double showBalance(String mobileNo) throws PaywalletException {
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(mobileNo.equals(ac.getMobileNo())) {
		return ac.getBalance();
		}else {
		throw new PaywalletException("number doesnot exists");
		}
	}

	@Override
	public Account deposit(String mobileNo, double depositAmt)
			throws PaywalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		throw new PaywalletException("does not exists");
		}
		double d=ac.getBalance()+depositAmt;
		ac.setBalance(d);
		em.merge(ac);
		em.getTransaction().commit();
		return ac;
	}

	@Override
	public Account withdraw(String mobileNo, double withdrawAmt)
			throws PaywalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		throw new PaywalletException("does not exists");
		}
		double d=ac.getBalance()-withdrawAmt;
		ac.setBalance(d);
		em.merge(ac);
		em.getTransaction().commit();
		return ac;
	}

	@Override
	public Account printTransactionDetails(String mobileNo)
			throws PaywalletException {
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		return ac;
		}else {
		throw new PaywalletException("number doesnot exists");
		}
		}
	
}
