package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Customer;
import com.dao.impl.CustomerMapper;
import com.service.CustomerService;
import com.web.util.ActionUtil;
@Service("CustomerSevice")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper mapper;
	@Override
	public Customer findCustomerByID(int id) throws Exception {
		Customer customer = mapper.getByID(id);
		return customer;
	}

	@Override
	public List findAllCustomer() throws Exception {
		List<Customer> list = mapper.getAll();
		return list;
	}

	@Override
	public void insertCustomer(String name,String level)  throws Exception{
			Customer customer = new Customer();
			customer.setName(name);
			customer.setLevel(Integer.parseInt(level));
			mapper.insert(customer);
	}

	@Override
	public void updateCustomer(String id, String name, String level) throws Exception {
				int cusId = Integer.parseInt(id);
				int cusLevel = Integer.parseInt(level);
				Customer customer = mapper.getByID(cusId);
				customer.setName(name);
				customer.setLevel(cusLevel);
				mapper.update(customer);
			}

	@Override
	public void deleteCustomer(int CustomerID) throws Exception {
			mapper.deleteByID(CustomerID);
	}

}
