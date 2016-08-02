package com.customer.microservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@NamedQueries({
		@NamedQuery(name = "Customer.findAllCustomers", query = "SELECT * FROM customers"),
		@NamedQuery(name = "Customer.findCustomerById", query = "SELECT * FROM customers C WHERE C.id = :id"),
		@NamedQuery(name = "Customer.findCustomerByEmail", query = "FROM customers C WHERE C.email = :email"),
		@NamedQuery(name = "Customer.findCustomerByName", query = "FROM customers C WHERE C.name = :name"),
		@NamedQuery(name = "Customer.insertCustomer", query = "INSERT INTO customers(name,email) VALUES(:name,:email)"),
		@NamedQuery(name = "Customer.updateCustomer", query = "UPDATE customers SET name=:name, email=:email WHERE id=:id"),
		@NamedQuery(name = "Customer.deleteCustomer", query = "DELETE FROM customers WHERE id=:id") 
		})
@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ID : " + id + " \tName : " + name + " \tEmail :" + email;
	}

}
