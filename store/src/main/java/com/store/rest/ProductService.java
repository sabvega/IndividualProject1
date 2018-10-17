package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class ProductService {

	//@Autowired
	private ProductDAO productDAO = new ProductDAO();

	public String getAllProducts(){
		String ans = "";
		Collection<Product> products = productDAO.getAllProducts();
		for(Product product : products) {
			ans += product.toString();
		}

		return ans;
		}

		public Product getProduct(int itemId){
			return productDAO.getProduct(itemId);
		}

		public Product getProductByKeyword(String keyword){
			return productDAO.getProductByKeyword(keyword);
		}

		public boolean deleteProduct(Product product) {
			return productDAO.deleteProduct(product);
		}
	}
