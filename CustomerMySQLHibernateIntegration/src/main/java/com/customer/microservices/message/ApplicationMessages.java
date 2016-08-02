package com.customer.microservices.message;

public interface ApplicationMessages {
	
	
	public static final String CUSTOMERS_LIST_NOT_EMPTY = "Customers list is not empty";
	public static final String CUSTOMERS_LIST_EMPTY = "Customers list is empty";
	public static final String CUSTOMER_EXIST = "Customer does exist in Database";
	public static final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist in Database";
	public static final String CUSTOMER_STATE_SAVED = "Customer Details saved to DB";
	public static final String CUSTOMER_STATE_UPDATED = "Customer Details updated to DB";
	public static final String CUSTOMER_STATE_DELETED = "Customer delete from DB";
	public static final String NO_CUSTOMER_DATA = "Customer data is sent from Client";
	
	
	public static final String EMPLOYEES_LIST_NOT_EMPTY = "Employees list is not empty";
	public static final String EMPLOYEES_LIST_EMPTY = "Employees list is empty";
	public static final String EMPLOYEE_EXIST = "Employee does exist in Database";
	public static final String EMPLOYEE_DOES_NOT_EXIST = "Employee does not exist in Database";
	public static final String EMPLOYEE_STATE_SAVED = "Employee Details saved to DB";
	public static final String EMPLOYEE_STATE_UPDATED = "Employee Details updated to DB";
	public static final String EMPLOYEE_STATE_DELETED = "Employee delete from DB";
	public static final String NO_EMPLOYEE_DATA = "Employee data is sent from Client";
	
	
	public static final String EXCEPTION_OCCURRED = "Please contact to Admin";
	
    public static final String CUSTOMER_METHED_FIND_ALL = "Exception in getCustomers"; 
    public static final String CUSTOMER_METHED_FIND_ONE = "Exception in getCustomer"; 
    public static final String CUSTOMER_METHED_STORED = "Exception in saveCustomer"; 
    public static final String CUSTOMER_METHED_UPDATED = "Exception in updateCustomer"; 
    public static final String CUSTOMER_METHED_DELETED = "Exception in deleteCustomer"; 
    
    public static final String EMPLOYEE_METHED_FIND_ALL = "Exception in getEmployees"; 
    public static final String EMPLOYEE_METHED_FIND_ONE = "Exception in getEmployee"; 
    public static final String EMPLOYEE_METHED_STORED = "Exception in saveEmployee"; 
    public static final String EMPLOYEE_METHED_UPDATED = "Exception in updateEmployee"; 
    public static final String EMPLOYEE_METHED_DELETED = "Exception in deleteEmployee"; 
    
    
}
