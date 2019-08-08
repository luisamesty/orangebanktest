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
		// HQL All Transactions
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

	// Get records by IBAN 
	@SuppressWarnings("null")
	@Override
	public List<AccountTransaction> listTransactionsByIBAN(String account_iban, String ASC_DESC) {
		Session session = this.sessionFactory.getCurrentSession();
		// <List<AccountTransaction> accountsTRList = session.createQuery("from AccountTransaction").list();
		// HQL Build from parameters
		Query query = null;
		if (account_iban != null && !account_iban.isEmpty()  ) {
			query = session.createQuery("from AccountTransaction where account_iban = :codeiban  order by tramount :ascdes ");
			query.setParameter("codeiban", account_iban);
			if (ASC_DESC.compareToIgnoreCase("D")== 0 || ASC_DESC.compareToIgnoreCase("DESC")== 0 ) {
				query.setParameter("ascdes", "DESC");
			} else { 
				query.setParameter("ascdes", "ASC");
			}
		} else {
			query = session.createQuery("from AccountTransaction "); //order by tramount :ascdes ");
		}
		// Get Transactions as QUERY
		List<AccountTransaction> accountsTRList = query.list();
		// LOG
		logger.info(query.getQueryString());
		for(AccountTransaction p : accountsTRList){
			logger.info("AccountTransaction List::"+p);
		}
		return accountsTRList;
	}

	// Get Records by REFERENCE
	@SuppressWarnings("null")
	@Override
	public List<AccountTransaction> listTransactionsByREF(String treference) {
		Session session = this.sessionFactory.getCurrentSession();
		//List<AccountTransaction> accountsTRList = session.createQuery("from AccountTransaction").list();
		// HQL Build from parameters
		Query query = null;
		if (treference != null && !treference.isEmpty()  ) {
			query = session.createQuery("from AccountTransaction where treference = :trref  order by tramount ASC ");
			query.setParameter("trref", treference);
		} else {
			query = session.createQuery("from AccountTransaction order by tramount ASC ");
		}
		// Get Transactions as QUERY
		List<AccountTransaction> accountsTRList = query.list();
		// LOG
		logger.info(query.getQueryString());
		for(AccountTransaction p : accountsTRList){
			logger.info("AccountTransaction List::"+p);
		}
		return accountsTRList;
	}
		
}
