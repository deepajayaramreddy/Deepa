package com.ibm.fstraining.springgitproject.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ibm.fstraining.springgitproject.model.Product;

public class ProductRepositoryImpl {

	private static final AtomicInteger counter = new AtomicInteger();
	private static List<Product> products = new ArrayList<Product>(
			Arrays.asList(new Product(counter.incrementAndGet(), "Product1",
					"product1", "10/11/2017"),
					new Product(counter.incrementAndGet(), "Product2",
							"product2", "10/12/2017"),
					new Product(counter.incrementAndGet(), "Product3",
							"product3", "11/02/2017"),
					new Product(counter.incrementAndGet(), "Product4",
							"product4", "3/09/2017")));

	public Product getAvailableProduct(Product product) {
		for (Product prod : products) {
			if (prod.getProductName().equals(product.getProductName())) {
				return prod;
			}
		}
		return null;
	}

	public boolean createProduct(Product product) {
		product.setId(counter.incrementAndGet());
		products.add(product);
		return true;
	}
	
	public  List<Product> getAllProducts(){
		return products;
	}
	
	public Product getProduct(int id, int customerType){
		for (Product p : products) {

			if (p.getId() == id) {
				return p;
			}		
		}	
		return null;
	}

}
