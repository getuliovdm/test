package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	
	public List<Customer> getCustomers() {
		// get the curent Hibernate Sesson
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query .. sort by last name
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		// return results
		
		return customers;
	}
	@Override
	public void saveCustomer(Customer theCustomer) {
	
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		// save the customer... finally LoL
		
		
	}
	@Override
	public Customer getCustomer(int theCustomerId) {
		// get the Current Hibernate SEssion
		Session currentSession = sessionFactory.getCurrentSession();
		// now retrieve/ read from database using the primary key
		
		return currentSession.get(Customer.class, theCustomerId);
	}
	@Override
	public void delete(int theCustomerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theDeleteQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		theDeleteQuery.setParameter("customerId", theCustomerId);
	
		theDeleteQuery.executeUpdate();
	}

}
