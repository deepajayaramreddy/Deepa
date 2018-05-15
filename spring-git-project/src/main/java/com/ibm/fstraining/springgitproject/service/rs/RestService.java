package com.ibm.fstraining.springgitproject.service.rs;

import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fstraining.springgitproject.model.Product;

//@Path("/service")
//@Produces({ MediaType.APPLICATION_JSON })

//@RestController
//@RequestMapping("/service")
public interface RestService {


	
//	@GET
//	@Path("/products")
//	@Produces({MediaType.APPLICATION_JSON})
	
	
	//@GetMapping("/products")
	public Product getProduct(
			@QueryParam("id")
			int id,@QueryParam("customerType")
			int customerType);
	
	
//	@POST
//	@Path("/createProduct")
//	@Produces({MediaType.APPLICATION_JSON})
	
	//@PostMapping("/createProduct")
	public boolean createProduct(@RequestBody Product product);
	
	
}