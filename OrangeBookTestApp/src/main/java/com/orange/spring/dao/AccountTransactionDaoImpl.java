package com.orange.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.orange.spring.model.AccountTransaction;


@Repository
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	@Override
	public int addTransaction(AccountTransaction accounttransaction) {
		sessionFactory.getCurrentSession().save(accounttransaction);
	      return accounttransaction.getId();
	}

	@Override
	public AccountTransaction getTransactionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		AccountTransaction accountTR = (AccountTransaction) session.load(AccountTransaction.class, new Integer(id));
		logger.info("Account Transaction loaded successfully, Account Details="+accountTR.toString());
		return accountTR;
	}

	@Override
	public List<AccountTransaction> listTransaction() {
		Session session = this.sessionFactory.getCurrentSession();
		List<AccountTransaction> accountsTRList = session.createQuery("from AccountTransaction").list();
		for(AccountTransaction p : accountsTRList){
			logger.info("AccountTransaction List::"+p);
		}
		return accountsTRList;
	}

	@Override
	public void updateTransaction( AccountTransaction accounttransaction) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(accounttransaction);
		logger.info("Account Transaction updated successfully, Account Details="+accounttransaction.toString());
	}

	@Override
	public void deleteTransaction(int id) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction = (AccountTransaction) session.load(AccountTransaction.class, new Integer(id));
		if(null != accounttransaction){
			session.delete(accounttransaction);
		}
		logger.info("Account Transaction deleted successfully, Account details="+accounttransaction);
	}
		
}
