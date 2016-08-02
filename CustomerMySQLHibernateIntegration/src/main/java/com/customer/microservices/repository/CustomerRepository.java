package com.customer.microservices.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.microservices.model.Customer;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);
	
	public Customer findByName(String name);
	
}
