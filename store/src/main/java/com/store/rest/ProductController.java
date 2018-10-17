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
@Path("/products")
public class ProductController extends HttpServlet  {


	//@Autowired
	private ProductService prouctService = new ProductService();

	 public void init(ServletConfig config) {
		 try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		  }catch(ServletException e){
		  }
	 }

	@GET
	@Path("/items/")
	public String getAllProducts() {
		return ProductService.getAllProducts();

	}

	@GET
	@Path("/items/{id}")
	public String getProduct(@PathParam("id") int itemId ) {
		return ProductService.getProduct(itemId);

	}
	@GET
	@Path("/items/{keyword}")
	public String getProductByKeyword(@PathParam("keyword")String keyword) {
		return ProductService.getProductByKeyword(keyword);

	}
}
