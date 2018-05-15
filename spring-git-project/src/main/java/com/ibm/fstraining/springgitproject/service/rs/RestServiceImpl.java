package com.ibm.fstraining.springgitproject.service.rs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.repo.ProductRepositoryImpl;
import com.ibm.fstraining.springgitproject.repo.ProductWareHouseRepositoryImpl;
import com.ibm.fstraining.springgitproject.service.ProductService;


//import com.att.ajsc.common.AjscService;



//@AjscService
@Service
public class RestServiceImpl implements RestService {	



	
	@Autowired
	private ProductService productService;

	public RestServiceImpl() {
		// needed for autowiring
	}



	
	
	@Override
	public Product getProduct(int id, int customerType) {
		ProductRepositoryImpl impl = new ProductRepositoryImpl(); 
		productService.setProductRepository(impl);
		return productService.getProduct(id, customerType);
	}

	@Override
	public boolean createProduct(Product product) {
		try {
			
			System.out.println("From Env =========================================");
			System.out.println(System.getProperty("http.proxyHost"));
			System.out.println(System.getProperty("http.proxyPort"));
			System.out.println(System.getProperty("HTTP_PROXY"));
			
			ProductRepositoryImpl impl = new ProductRepositoryImpl(); 
			productService.setProductRepository(impl);
			
			ProductWareHouseRepositoryImpl repository =  new ProductWareHouseRepositoryImpl(); 		
			productService.setProductWareHouseRepository(repository);
			
			SKUServiceImplementation skuImpl = new SKUServiceImplementation();
			productService.setRestTemplateService(skuImpl);
			
			return productService.create(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

	
	
	

}
