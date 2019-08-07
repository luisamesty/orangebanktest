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
	public int save(Account account) {
		sessionFactory.getCurrentSession().save(account);
	      return account.getId();
	}

	@Override
	public Account get(int id) {
		return sessionFactory.getCurrentSession().get(Account.class, id);
	}

	@Override
	public Account getByIBAN(String account_iban) {
		List<Account> accts = null;
		Account retAccount = null;
		Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Account> cq = cb.createQuery(Account.class);
	      Root<Account> root = cq.from(Account.class);
	      cq.select(root);
	      //  OJO WHERE Clause
	      cq.where(em.getCriteriaBuilder().equal(root.get("account_iban"), account_iban ));
	      //  OJO WHERE Clause
	      Query<Account> query = session.createQuery(cq);
	      accts = query.getResultList();
	      if (accts.size() > 0)
	    	  retAccount = accts.get(0) ;
	      return retAccount;
	}
	
	@Override
	public List<Account> list() {
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Account> cq = cb.createQuery(Account.class);
	      Root<Account> root = cq.from(Account.class);
	      cq.select(root);
	      //  OJO Order By id
	      cq.orderBy(cb.asc(root.get("id")));
	      //  OJO Order By id
	      Query<Account> query = session.createQuery(cq);
	      return query.getResultList();
	}

	@Override
	public void update(int id, Account account) {
		Session session = sessionFactory.getCurrentSession();
		Account account2 = session.byId(Account.class).load(id);
		account2.setAccount_iban(account.getAccount_iban());
		account2.setBalance(account.getBalance());
		account2.setName(account.getName());
	      session.flush();
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
	      Account account = session.byId(Account.class).load(id);
	      session.delete(account);
	}


}
