package com.orange.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	public void updateAccount(Account account) {
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
      	  		System.out.println("** Account UPDATE ERROR ** "+errorMessage+"Account ID:"+account.getId()+"  IBAN:"+account.getAccount_iban());
        }
	}
	
	@Override
	public List<Account> listAccount() {
		 Session session = sessionFactory.getCurrentSession();
		 
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      CriteriaQuery<Account> query = builder.createQuery(Account.class);
	      Root<Account> root = query.from(Account.class);
	      query.select(root);
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
		System.out.println("Traza : "+id);
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      CriteriaQuery<Account> query = builder.createQuery(Account.class);
	      Root<Account> root = query.from(Account.class);
	      query.select(root).where(builder.equal(root.get("id"), id));;
	      Query<Account> queryacct = session.createQuery(query);
	      Account retAccount = queryacct.getSingleResult();
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
		Session session = sessionFactory.getCurrentSession();
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Account> query = builder.createQuery(Account.class);
	    Root<Account> root = query.from(Account.class);
	    query.select(root).where(builder.equal(root.get("account_iban"), account_iban));;
	    Query<Account> queryacct = session.createQuery(query);
	    retAccount = queryacct.getSingleResult();
	    return retAccount;
	}


}
