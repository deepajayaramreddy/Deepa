package com.ibm.fstraining.springgitproject.service;

import java.util.List;

import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.service.rs.SKUServiceImplementation;
import com.ibm.fstraining.springgitproject.repo.ProductRepositoryImpl;
import com.ibm.fstraining.springgitproject.repo.ProductWareHouseRepository;

public interface ProductService {

	List<Product> getAll();

	boolean create(Product Product) throws Exception;

	Product getProduct(int id, int customerType);

	public void setProductRepository(ProductRepositoryImpl productRepository);
	
	public void setRestTemplateService(SKUServiceImplementation skuServiceImpl);
	
	public void setProductWareHouseRepository(ProductWareHouseRepository productWareHouseRepository);
	
}
