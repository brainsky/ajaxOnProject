package com.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.bean.Customer;
import com.dao.impl.CustomerMapper;

public class CustomerMapperTest {
	
	private CustomerMapper mapper;
	@Before
	public void setUp() throws Exception {
		ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
		mapper = (CustomerMapper) context.getBean("CustomerMapper");
	}

	@Test
	public void testGetByID() throws Exception {
		Customer customer = mapper.getByID(3);
		assertNotNull(customer);
		assertEquals("lkd", customer.getName());
	}

	@Test
	public void testGetAll() throws Exception {
		List<Customer> list = mapper.getAll();
		assertEquals(1, list.size());
	}

	@Test
	public void testInsert() throws Exception {
		Customer customer = new Customer();
		customer.setName("lkd");
		customer.setLevel(3);
		mapper.insert(customer);
	}

	@Test
	public void testUpdate() throws Exception {
		Customer customer1 = mapper.getByID(2);
		System.out.println(customer1.getName());
		customer1.setName("kkd");
		customer1.setLevel(1);
		mapper.update(customer1);
	}

	@Test
	public void testDeleteByID() throws Exception {
		mapper.deleteByID(7);
	}

}
