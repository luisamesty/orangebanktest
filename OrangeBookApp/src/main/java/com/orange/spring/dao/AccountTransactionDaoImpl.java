package com.orange.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orange.spring.model.AccountTransaction;

@Repository
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long save(AccountTransaction accounttransaction) {
		sessionFactory.getCurrentSession().save(accounttransaction);
	      return accounttransaction.getId();
	}

	@Override
	public AccountTransaction get(long id) {
		return sessionFactory.getCurrentSession().get(AccountTransaction.class, id);
	}

	@Override
	public List<AccountTransaction> list() {
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<AccountTransaction> cq = cb.createQuery(AccountTransaction.class);
	      Root<AccountTransaction> root = cq.from(AccountTransaction.class);
	      cq.select(root);
	      Query<AccountTransaction> query = session.createQuery(cq);
	      return query.getResultList();
	}

	@Override
	public void update(long id, AccountTransaction accounttransaction) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction2 = session.byId(AccountTransaction.class).load(id);
		accounttransaction2.setAccount_id(accounttransaction.getAccount_id());
		accounttransaction2.setReference(accounttransaction.getReference());
		accounttransaction2.setAccount_iban(accounttransaction.getAccount_iban());
		accounttransaction2.setDate(accounttransaction.getDate());
		accounttransaction2.setAmount(accounttransaction.getAmount());
		accounttransaction2.setFee(accounttransaction.getFee());
		accounttransaction2.setDescription(accounttransaction.getDescription());
		accounttransaction2.setStatus(accounttransaction.getStatus());
	      session.flush();
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction = session.byId(AccountTransaction.class).load(id);
	      session.delete(accounttransaction);
	}

}
