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
import com.orange.spring.model.TransactionId;

@Repository
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long addTransaction(AccountTransaction accounttransaction) {
		sessionFactory.getCurrentSession().save(accounttransaction);
	      return accounttransaction.getId();
	}

	@Override
	public AccountTransaction getTransaction(long id) {
		return sessionFactory.getCurrentSession().get(AccountTransaction.class, id);
	}

	@Override
	public List<AccountTransaction> listTransaction() {
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<AccountTransaction> cq = cb.createQuery(AccountTransaction.class);
	      Root<AccountTransaction> root = cq.from(AccountTransaction.class);
	      cq.select(root);
	      //  OJO Order By id
	      cq.orderBy(cb.asc(root.get("id")));
	      //
	      Query<AccountTransaction> query = session.createQuery(cq);
	      return query.getResultList();
	}

	@Override
	public void updateTransaction(long id, AccountTransaction accounttransaction) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction2 = session.byId(AccountTransaction.class).load(id);
		accounttransaction2.setAccount_iban(accounttransaction.getAccount_iban());
		accounttransaction2.setReference(accounttransaction.getReference());
		accounttransaction2.setDate(accounttransaction.getDate());
		accounttransaction2.setAmount(accounttransaction.getAmount());
		accounttransaction2.setFee(accounttransaction.getFee());
		accounttransaction2.setTrdescription(accounttransaction.getTrdescription());
		accounttransaction2.setTrstatus(accounttransaction.getTrstatus());
	      session.flush();
	}

	@Override
	public void deleteTransaction(long id) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction = session.byId(AccountTransaction.class).load(id);
	      session.delete(accounttransaction);
	}

}
