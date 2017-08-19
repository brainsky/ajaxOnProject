package com.service;

import java.util.List;

import com.bean.Customer;

public interface CustomerService {
	
	Customer findCustomerByID(int id) throws Exception;
	
	List findAllCustomer() throws Exception;
	
	void insertCustomer(String name,String level) throws Exception;
	
	void updateCustomer(String id,String name, String level) throws Exception;
	
	void deleteCustomer(int CustomerID) throws Exception;
}
