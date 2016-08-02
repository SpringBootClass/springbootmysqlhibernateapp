package com.customer.microservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.microservices.model.Customer;
import com.customer.microservices.result.Result;
import com.customer.microservices.service.CustomerServiceIF;
import com.customer.microservices.constant.ApplicationConstants;
import com.customer.microservices.message.ApplicationMessages;

@RestController
@RequestMapping(path = "/customer/v1")
public class CustomerController {

	

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerServiceIF customerServiceIF;

	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getCustomers() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Customer> customerList = null;
		try {
			customerList = customerServiceIF.getCustomers();
			if (customerList.isEmpty()) {
				result.setMessage(ApplicationMessages.CUSTOMERS_LIST_EMPTY);
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setObject(customerList);
			} else {
				result.setObject(customerList);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.CUSTOMERS_LIST_NOT_EMPTY);
			}

		} catch (Exception e) {
			logger.error(ApplicationMessages.CUSTOMER_METHED_FIND_ALL);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(customerList);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getCustomer(@PathVariable("customerId") String customerId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Customer customer = null;
		try {
			customer = customerServiceIF.getCustomer(Long.parseLong(customerId));
			if (customer == null) {
				result.setObject(customer);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.CUSTOMER_DOES_NOT_EXIST);
			} else {
				result.setObject(customer);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.CUSTOMER_EXIST);
			}

		} catch (Exception e) {
			logger.error(ApplicationMessages.CUSTOMER_METHED_FIND_ONE);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(customer);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveCustomer(@RequestBody Customer customer) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			
			if (customer == null) {
				result.setObject(customer);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.CUSTOMER_DOES_NOT_EXIST);
			} else {
				logger.info("customer.getId() else: "+customer.getId());
				customerServiceIF.saveCustomer(customer);
				result.setObject(customer);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.CUSTOMER_STATE_SAVED);
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.CUSTOMER_METHED_STORED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(customer);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateCustomer(@RequestBody Customer customer) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Customer customer1 = null;
		try {
			if (customer == null) {
				result.setObject(customer);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.NO_CUSTOMER_DATA);
			} else {
				customer1 = customerServiceIF.updateCustomer(customer);
				if(customer1 == null){
					result.setObject(customer1);
					statusCode = HttpStatus.NO_CONTENT;
					result.setStatus(ApplicationConstants.FAILED);
					result.setMessage(ApplicationMessages.CUSTOMER_DOES_NOT_EXIST);
				}
				else{
				result.setObject(customer1);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.CUSTOMER_STATE_UPDATED);
				}
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.CUSTOMER_METHED_UPDATED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(customer);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteCustomer(@PathVariable("customerId") String customerId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Customer customer = null;
		try {
			customer = customerServiceIF.deleteCustomer(Long.parseLong(customerId));
			if (customer == null) {
				result.setObject(customer);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.CUSTOMER_DOES_NOT_EXIST);
			} else {
				result.setObject(customer);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.CUSTOMER_STATE_DELETED);
			}

		} catch (Exception e) {
			logger.error(ApplicationMessages.CUSTOMER_METHED_DELETED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(customer);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}
}
