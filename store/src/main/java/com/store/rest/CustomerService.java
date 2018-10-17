package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService {

	//@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();

	public String getCustomerUsername(String username){
		String ans = "";
		Customer customerSelected = customerDAO.getCustomerUsername(username);
		ans = customerSelected.toString();

		return ans;
		}

		public Collection<Customers> getAllCustomers(){
			return customerDAO.getAllCustomers();
		}

		public booelan deleteCustomer(String username){
			return customerDAO.deleteCustomer(username);
		}

		public Customer updateCustomer(Customer customer){
			return customerDAO.updateCustomer(customer);
		}

	public Collection<Customers> getAllCustomers() {
		return customerService.getAllCustomers();
	}


	}
