package com.customer.microservices.dao;

import java.util.List;

import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

public interface CustomerDaoIF {
	
	public List<Customer> getCustomers() throws CustomerException;
	public Customer getCustomer(Long customerId) throws CustomerException;
	public Customer getCustomerByEmail(String emailId) throws CustomerException;
	public Customer getCustomerByName(String name) throws CustomerException;
	public Customer saveCustomer(Customer customer) throws CustomerException;
	public Customer updateCustomer(Customer customer) throws CustomerException;
	public Customer deleteCustomer(Long customerId) throws CustomerException;
	
}
