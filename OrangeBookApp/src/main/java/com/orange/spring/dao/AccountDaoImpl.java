package com.orange.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.orange.spring.model.Account;



@Repository
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addAccount(Account account) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(account);
		logger.info("Account saved successfully, Account Details="+account.toString());
		
	}

	@Override
	public void updateAccount( Account account) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(account);
		logger.info("Account updated successfully, Account Details="+account.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> listAccount() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Account> accountsList = session.createQuery("from Account").list();
		for(Account p : accountsList){
			logger.info("Account List::"+p);
		}
		return accountsList;
	}

	@Override
	public Account getAccountById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Account account = (Account) session.load(Account.class, new Integer(id));
		logger.info("Account loaded successfully, Account Details="+account.toString());
		return account;
	}

	@Override
	public void deleteAccount(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Account account = (Account) session.load(Account.class, new Integer(id));
		if(null != account){
			session.delete(account);
		}
		logger.info("Account deleted successfully, Account details="+account);
		
	}

	@Override
	public Account getByIBAN(String account_iban) {
		// TODO Auto-generated method stub
		return null;
	}



}
