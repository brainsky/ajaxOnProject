package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bean.Customer;

@Repository("CustomerMapper")
public interface CustomerMapper {
	
	Customer getByID(int id) throws Exception;
	
	List<Customer> getAll() throws Exception;
	
	void insert(Customer customer) throws Exception;
	
	void update(Customer customer) throws Exception;
	
	void deleteByID(int id) throws Exception;
}
