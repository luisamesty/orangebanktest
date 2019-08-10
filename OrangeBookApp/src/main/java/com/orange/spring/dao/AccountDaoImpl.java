package com.orange.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orange.spring.model.Account;


@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;
	// OJO
	@PersistenceContext 
	EntityManager em;
	// OJO
	
	@Override
	public void addAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		boolean isError = false;
	    String errorMessage="";
        try {
    		session.save(account);
        	isError = false;
        } catch (Exception e) {
            errorMessage=e.getLocalizedMessage(); //e.getMessage();
            isError = true;
        } finally {
      	  	if(isError)
      	  		System.out.println("** Account INSERT ERROR ** "+errorMessage+"Account ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
        }
		sessionFactory.getCurrentSession().save(account);
	}
	
	@Override
	public void updateAccount(int id, Account account) {
		Session session = sessionFactory.getCurrentSession();
		Account account2 = session.byId(Account.class).load(id);
  		account2.setAccount_iban(account.getAccount_iban());
  		account2.setName(account.getName());
  		account2.setInitbalance(account.getInitbalance());
  		account2.setBalance(account.getBalance());
	    session.flush();
	}
	
	@Override
	public List<Account> listAccount() {
		 Session session = sessionFactory.getCurrentSession();
		 
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      CriteriaQuery<Account> query = builder.createQuery(Account.class);
	      Root<Account> root = query.from(Account.class);
	      query.select(root);
	      query.orderBy(builder.asc(root.get("id")));
	      Query<Account> queryacct = session.createQuery(query);
	      if (queryacct == null) {
	    		System.out.println("** Account LIST ERROR ** ");
	    		return null;
	      }
	      //
	      return queryacct.getResultList();

	}
	
	@Override
	public Account getAccountById(int id) {
		Account retAccount = null;
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      CriteriaQuery<Account> query = builder.createQuery(Account.class);
	      Root<Account> root = query.from(Account.class);
	      query.select(root).where(builder.equal(root.get("id"), id));;
	      Query<Account> queryacct = session.createQuery(query);
	      //Account retAccount = queryacct.getSingleResult();
		    try {
		    	List<Account> results = queryacct.getResultList();
		        if (results.isEmpty()) 
		        	retAccount = null;
		        else if (results.size() >= 1) 
		        	retAccount = results.get(0);
		    } catch (NoResultException e ) {
		    	retAccount = null;
		    } catch (NonUniqueResultException e2 ) {
		    	retAccount = null;	
		    } 

	      if (retAccount == null )
	    		  return null;
	      else 
	    	  return retAccount;
	}
	
	@Override
	public void deleteAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
	      Account account = session.byId(Account.class).load(id);
	      session.delete(account);
	}
	
	@Override
	public Account getByIBAN(String account_iban) {
		Account retAccount = null;
		if ( account_iban !=null && !account_iban.isEmpty() ) {
			Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Account> query = builder.createQuery(Account.class);
		    Root<Account> root = query.from(Account.class);
		    query.select(root).where(builder.equal(root.get("account_iban"), account_iban.trim()));;
		    Query<Account> queryacct = session.createQuery(query);
		    try {
		    	List<Account> results = queryacct.getResultList();
		        if (results.isEmpty()) 
		        	retAccount = null;
		        else if (results.size() >= 1) 
		        	retAccount = results.get(0);
		    } catch (NoResultException e ) {
		    	retAccount = null;
		    } catch (NonUniqueResultException e2 ) {
		    	retAccount = null;	
		    } 
		} 
		if (retAccount== null ) {
			System.out.println(account_iban+" AccountDaoImpl.getByIBAN *** NOT FOUND ***  IBAN = "+account_iban);
		} else {
			System.out.println(account_iban+" AccountDaoImpl.getByIBAN *** FOUNDED ***  IBAN = " +account_iban);
		}
	    return retAccount;
	}
}
