package com.ibm.fstraining.springgitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.service.rs.RestServiceImpl;

@RestController
@RequestMapping("/service")
public class ProjectController {
	
	@Autowired 
	RestServiceImpl restServiceImp;
	
	
	
	
	public ProjectController(RestServiceImpl restServiceImp) {
		this.restServiceImp = restServiceImp;
	}




	@GetMapping("/product")
	public Product getPro(@RequestParam("id") int id,@RequestParam("customerType") int customerType)
	{
		
		return restServiceImp.getProduct(id, customerType);
				
	}
	
	@PostMapping("/createProduct")
	public boolean addProduct(@RequestBody Product product)
	{
		return restServiceImp.createProduct(product);
	}

}
