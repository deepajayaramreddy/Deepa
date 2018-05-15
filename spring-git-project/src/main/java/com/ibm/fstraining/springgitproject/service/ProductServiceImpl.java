package com.ibm.fstraining.springgitproject.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.fstraining.springgitproject.exception.ProductException;
import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.service.rs.RestTemplateServiceImpl;
import com.ibm.fstraining.springgitproject.service.rs.SKUServiceImplementation;
import com.ibm.fstraining.springgitproject.validation.ProductValidation;
import com.ibm.fstraining.springgitproject.repo.ProductRepositoryImpl;
import com.ibm.fstraining.springgitproject.repo.ProductWareHouseRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductRepositoryImpl productRepository;
	private ProductWareHouseRepository productWareHouseRepository;
	private SKUServiceImplementation skuServiceImpl;

	private RestTemplateServiceImpl restTemplateService;

	public void setProductWareHouseRepository(ProductWareHouseRepository productWareHouseRepository) {
		this.productWareHouseRepository = productWareHouseRepository;
	}

	public void setProductRepository(ProductRepositoryImpl productRepository) {
		this.productRepository = productRepository;
	}

	public void setRestTemplateService(SKUServiceImplementation skuServiceImpl) {
		this.skuServiceImpl = skuServiceImpl;
	}

	@Override
	public List<Product> getAll() {
		return productRepository.getAllProducts();
	}

	@Override
	public boolean create(Product product) throws Exception {

		boolean status = false;

		System.out.println("Product is " + product);

		boolean validProduct = new ProductValidation().validateProduct(product);
		if (validProduct) {
			// Invoke external web service to check barCode
			String barCode = skuServiceImpl.getSku(product.getId());
			System.out.println("barcode received==="+barCode);

			if (barCode != null) {
				// check product exist in the external ProductWareHouse database
				boolean isExistingInWareHouse = productWareHouseRepository.isProductAvailable(product);
				if (isExistingInWareHouse) {
					// add the product to product table - DB
					productRepository.createProduct(product);
				} else {
					throw new ProductException("Missing product in the warehouse");
				}
			} else {
				throw new ProductException("Barcode not avilable for product");
			}
			status = true;
		}
		return status;
	}

	@Override
	public Product getProduct(int id, int customerType) {

		Product product = productRepository.getProduct(id, customerType);

		if (product != null) {
			if (customerType == 1) {
				product.setMfgdate("2006/10/10");

			} else {
				product.setMfgdate("10/10/06");
				//product.setMfgdate("10/10/2006");
			}
		}

		return product;
	}

	public String getProductCategory(int productId) {
		if (productId == 1) {
			return "Medium";
		} else {
			return "Small";
		}
	}

	public int getProductAmount(int productId) {
		if (productId == 1) {
			return 1000;
		} else {
			return 2000;
		}
	}

	public String getProductSku(int productId) {
		// need to invoke external application to get the value
		return "1000unit";
	}
}