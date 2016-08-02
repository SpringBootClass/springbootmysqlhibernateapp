package com.customer.microservices.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.microservices.config.DatabaseConfig;
import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDaoIF,Serializable {

	@Autowired
	private DatabaseConfig databaseConfig;

	@Override
	public List<Customer> getCustomers() throws CustomerException {
		Session session = null;
		List<Customer> customerList = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.findAllCustomers");
			customerList = query.list();
			session.flush();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customerList;
	}

	@Override
	public Customer getCustomer(Long customerId) throws CustomerException {
		Session session = null;
		Customer customer = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.findCustomerById").setLong("id", customerId);
			customer = (Customer) query.list().get(0);
			session.flush();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customer;
	}

	@Override
	public Customer saveCustomer(Customer customer) throws CustomerException {
		Transaction transaction = null;
		Session session = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.insertCustomer").setString("name", customer.getName())
					.setString("email", customer.getEmail());
			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();
			session.flush();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return getCustomer(customer.getId());
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		if (getCustomer(customer.getId()) == null)
			return null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.updateCustomer").setString("name", customer.getName())
					.setString("email", customer.getEmail()).setLong("id", customer.getId());
			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();
			session.flush();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return getCustomer(customer.getId());
	}

	@Override
	public Customer deleteCustomer(Long customerId) throws CustomerException {
		Customer customer = null;
		if (getCustomer(customerId) == null)
			return null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			customer = getCustomer(customerId);
			Query query = session.getNamedQuery("Customer.deleteCustomer").
					setLong("id", customer.getId());
			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();
			session.flush();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String emailId) throws CustomerException {

		Session session = null;
		Customer customer = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.findCustomerByEmail").setString("email", emailId);
			customer = (Customer) query.list().get(0);
			session.flush();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customer;
	}

	@Override
	public Customer getCustomerByName(String name) throws CustomerException {
		Session session = null;
		Customer customer = null;
		try {
			session = databaseConfig.sessionFactory().getObject().openSession();
			Query query = session.getNamedQuery("Customer.findCustomerByName").setString("name", name);
			customer = (Customer) query.list().get(0);
			session.flush();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customer;
	}

}
