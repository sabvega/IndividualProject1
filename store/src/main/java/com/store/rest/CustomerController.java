package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.ArrayList;

import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet  {


	//@Autowired
	private CustomerService customerService = new CustomerService();

	 public void init(ServletConfig config) {
		 try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		  }catch(ServletException e){
		  }
	 }

	@GET
	@Path("/customers/{username}")
	public String getCustomerUsername(@PathParam("username") String username) {
	return customerService.getCustomerUsername(username);

	}

	@GET
	@Path("/customers/")
	public Collection<Customers> getAllCustomers() {
	return customerService.getAllCustomers();

	}


	@PUT
	@Path("/customers/")
	public Collection<Customers> updateCustomer() {
	return customerService.updateCustomer();
}

	@DELETE
	@Path("/customers/{username}")
	public boolean deleteCustomer(@PathParam("username") String username) {
	return customerService.deleteCustomer(username);
	}
}
