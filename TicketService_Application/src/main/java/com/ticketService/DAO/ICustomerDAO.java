package com.ticketService.DAO;

import com.ticketService.domain.Customer;

public interface ICustomerDAO {
	public Customer findByEmail(String email);
	public void createCustomer(Customer cust);
}
