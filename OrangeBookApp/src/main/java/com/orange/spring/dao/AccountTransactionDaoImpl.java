package com.orange.spring.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orange.spring.model.Account;
import com.orange.spring.model.AccountTransaction;

@Repository
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addTransaction(AccountTransaction accounttransaction) {

		// Variables
		BigDecimal newBalance = BigDecimal.ZERO;
		String treference = "";
		String errorMessage="";
		String trstatus = "";
		Account account = null;
		String account_iban =accounttransaction.getAccount_iban();
		// Get Account From IBAN Transaction
		if ( account_iban !=null && !account_iban.isEmpty() ) {
			Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Account> query = builder.createQuery(Account.class);
		    Root<Account> root = query.from(Account.class);
		    query.select(root).where(builder.equal(root.get("account_iban"), account_iban));;
		    Query<Account> queryacct = session.createQuery(query);
		    account = queryacct.getSingleResult();
		} else {
			account=null;
		}
		// Verify Account BY IBAN
		if (account != null) {
            // Account VALID and Balance OK
			if (account.getBalance().compareTo(accounttransaction.getTramount() ) >= 0) {
				// Update Balance
				newBalance = account.getBalance().subtract(accounttransaction.getTramount());
				// 
				trstatus ="SETTLED";
			} else {
	            // Account VALID and Balance NOT VALID
				trstatus ="PENDING";
				newBalance = account.getBalance().subtract(accounttransaction.getTramount());
				newBalance = newBalance.subtract(accounttransaction.getTrfee());
			}
			// Final Account Updates
			account.setBalance(newBalance);
			//
		} else {
			// ACCOUNT INVALID
			trstatus ="INVALID";
    		System.out.println("** TRANSACTION ERROR ACOUNT INVALID** "+errorMessage+" ID:"+accounttransaction.getId()+" REF:"+accounttransaction.getTreference()+"  IBAN:"+accounttransaction.getAccount_iban());
		}
		// Verify reference if not  put TR ID
		treference = accounttransaction.getTreference();
		if (treference == null || treference.isEmpty() )
			treference = String.format ("%10s", accounttransaction.getId());
		// Final Transaction Updates
		accounttransaction.setTrstatus(trstatus);
		accounttransaction.setTreference(treference);
	    // Writes Transaction
		sessionFactory.getCurrentSession().save(accounttransaction);
	    return accounttransaction.getId();
	}

	@Override
	public AccountTransaction getTransactionById(int id) {
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

	// Get records by IBAN 
	@SuppressWarnings("null")
	@Override
	public List<AccountTransaction> listTransactionsByIBAN(String account_iban, String ASC_DESC) {
		Session session = this.sessionFactory.getCurrentSession();
		List<AccountTransaction> accountsTRList = null;
		// HQL Build from parameters
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<AccountTransaction> query = builder.createQuery(AccountTransaction.class);
	    Root<AccountTransaction> root = query.from(AccountTransaction.class);
	    query.select(root);
	    // Restriction AND Order By id
	    //query.orderBy(builder.asc(root.get("id")));
	    if (account_iban != null && !account_iban.isEmpty()  ) {
	    	// account_iban
	    	query.where(builder.equal(root.get("account_iban"), account_iban));
			if (ASC_DESC.compareToIgnoreCase("D")== 0 || ASC_DESC.compareToIgnoreCase("DESC")== 0 ) {
				query.orderBy(builder.desc(root.get("tramount"))); 
			} else { 
				query.orderBy(builder.asc(root.get("tramount"))); 
			}
	    } else {
	    	query.orderBy(builder.asc(root.get("tramount"))); 
	    }
	    //
	    Query<AccountTransaction> querytr = session.createQuery(query);
	    accountsTRList = querytr.getResultList();
		// LOG
		System.out.println("listTransactionsByIBAN: account_iban="+account_iban+"  "+ASC_DESC);
		for(AccountTransaction p : accountsTRList){
			System.out.println("AccountTransaction List::"+p);
		}
		return accountsTRList;

	}

	// Get Records by REFERENCE
	@SuppressWarnings("null")
	@Override
	public List<AccountTransaction> listTransactionsByREF(String treference) {
		Session session = this.sessionFactory.getCurrentSession();
		List<AccountTransaction> accountsTRList = null;
		// HQL Build from parameters
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<AccountTransaction> query = builder.createQuery(AccountTransaction.class);
	    Root<AccountTransaction> root = query.from(AccountTransaction.class);
	    query.select(root);
	    // Restriction AND Order By id
	    //query.orderBy(builder.asc(root.get("id")));
		if (treference != null && !treference.isEmpty()  ) {
	    	query.where(builder.equal(root.get("treference"), treference));
		}
		query.orderBy(builder.asc(root.get("tramount"))); 
	    Query<AccountTransaction> querytr = session.createQuery(query);
	    accountsTRList = querytr.getResultList();
		// LOG
		System.out.println("listTransactionsByREF: treference="+treference+  " (ASC)");
		for(AccountTransaction p : accountsTRList){
			System.out.println("AccountTransaction List::"+p);
		}
		return accountsTRList;
		
//		// HQL Build from parameters DEPRECATED
//		Query query = null;
//		if (treference != null && !treference.isEmpty()  ) {
//			query = session.createQuery("from AccountTransaction where treference = :trref  order by tramount ASC ");
//			query.setParameter("trref", treference);
//		} else {
//			query = session.createQuery("from AccountTransaction order by tramount ASC ");
//			treference ="";
//		}
//		// Get Transactions as QUERY
//		List<AccountTransaction> accountsTRList = query.list();
//		// LOG
//		System.out.println("listTransactionsByREF: treference="+treference+"  "+query.getQueryString());
//		for(AccountTransaction p : accountsTRList){
//			System.out.println("AccountTransaction List::"+p);
//		}

	}

	@Override
	public void updateTransaction(AccountTransaction accounttransaction) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction2 = session.byId(AccountTransaction.class).load(accounttransaction.getId());
		accounttransaction2.setAccount_iban(accounttransaction.getAccount_iban());
		accounttransaction2.setTreference(accounttransaction.getTreference());
		accounttransaction2.setTrfecha(accounttransaction.getTrfecha());
		accounttransaction2.setTramount(accounttransaction.getTramount());
		accounttransaction2.setTrfee(accounttransaction.getTrfee());
		accounttransaction2.setTrdescription(accounttransaction.getTrdescription());
		accounttransaction2.setTrstatus(accounttransaction.getTrstatus());
	      session.flush();
	}

	@Override
	public void deleteTransaction(int id) {
		Session session = sessionFactory.getCurrentSession();
		AccountTransaction accounttransaction = session.byId(AccountTransaction.class).load(id);
	      session.delete(accounttransaction);
	}
	

}
