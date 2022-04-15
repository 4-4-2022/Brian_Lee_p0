package com.brian1.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.Brian.model.Customer;

@TestInstance(Lifecycle.PER_CLASS)
public class CustomerTest {
	
	private Customer customer;
	
	/*
	 * Basic set up before running any tests. Need to create an instance
	 */
	
	@BeforeAll
	public void setup() {
		this.customer = new Customer();
	}
//	@Test
//	public void testFindAllCustomers() {
//		Set<Customer> customers = this.
//	}
}
