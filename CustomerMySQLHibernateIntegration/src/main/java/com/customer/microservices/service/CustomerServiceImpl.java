package com.customer.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.microservices.dao.CustomerDaoIF;
import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerServiceIF {

	@Autowired
	private CustomerDaoIF customerDaoIF;
	
	@Override
	public List<Customer> getCustomers() throws CustomerException {
		List<Customer> customerList = customerDaoIF.getCustomers();
		return customerList;
	}

	@Override
	public Customer getCustomer(Long customerId) throws CustomerException {
		Customer customer = customerDaoIF.getCustomer(customerId);
		return customer;
	}

	@Override
	public Customer saveCustomer(Customer customer) throws CustomerException {
		return customerDaoIF.saveCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		return customerDaoIF.updateCustomer(customer);
	}

	@Override
	public Customer deleteCustomer(Long customerId) throws CustomerException {
		return customerDaoIF.deleteCustomer(customerId);
	}

}
