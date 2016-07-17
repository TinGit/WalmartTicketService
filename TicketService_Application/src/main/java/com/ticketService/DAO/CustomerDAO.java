package com.ticketService.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.domain.Customer;

@Repository
@Transactional
public class CustomerDAO implements ICustomerDAO{

	@PersistenceContext
	EntityManager em;
	
	/**
	 * will query through and returns a customer with that email
	 * @param email -- email of the customer who will be searched
	 * @return -- a Customer object
	 */
	 
	
	public Customer findByEmail(String email) {
		Query q =em.createQuery("select c from Customer c where c.email =:email");
		q.setParameter("email", email);
		
		Customer customer=null;
		List<Customer> custList = q.getResultList();
		if(!custList.isEmpty())
			customer= custList.get(0);
		
		return customer;
	}

	public void createCustomer(Customer cust){
		em.persist(cust);
	}
}
