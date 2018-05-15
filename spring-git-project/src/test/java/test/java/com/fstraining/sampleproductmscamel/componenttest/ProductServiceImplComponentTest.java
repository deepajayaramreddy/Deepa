package test.java.com.fstraining.sampleproductmscamel.componenttest;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ibm.fstraining.springgitproject.exception.ProductException;
import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.repo.ProductRepositoryImpl;
import com.ibm.fstraining.springgitproject.repo.ProductWareHouseRepository;
import com.ibm.fstraining.springgitproject.service.ProductServiceImpl;
import com.ibm.fstraining.springgitproject.service.rs.SKUServiceImplementation;


public class ProductServiceImplComponentTest {

	private ProductServiceImpl productServiceImpl;
	private ProductRepositoryImpl productRepository;
	
	
	// External data ware house dependency mocking object
	@Mock
	private ProductWareHouseRepository productWareHouseRepository;
	
	// External webservice endpoint dependency mocking object
	@Mock
	private SKUServiceImplementation restTemplateServiceImpl;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		productRepository =  new ProductRepositoryImpl();
		productServiceImpl = new ProductServiceImpl();
		
		productServiceImpl.setProductWareHouseRepository(productWareHouseRepository);
		productServiceImpl.setRestTemplateService(restTemplateServiceImpl);
		productServiceImpl.setProductRepository(productRepository);
	}

	@Test
	public void createProductSuceess() throws Exception {
		Product product = new Product(1, "Product1", "Product1Desc", "10/11/2017");
		when(restTemplateServiceImpl.getSku(1))
				.thenReturn("barcode123");

		when(productWareHouseRepository.isProductAvailable(product)).thenReturn(true);

		try {
			productServiceImpl.create(product);
		} catch (ProductException e) {
			throw e;
		}
	}

	@Test(expected = ProductException.class)
	public void createProductFailed_with_Barcode_not_avilable_for_product() throws Exception {
		Product product = new Product(10, "Product1", "Product1Desc", "10/11/2017");
		when(restTemplateServiceImpl.getSku(10))
				.thenReturn(null);

		try {
			productServiceImpl.create(product);
		} catch (ProductException e) {
			throw e;
		}
	}

	
	@Test(expected = ProductException.class)
	public void createProductFailed_Missing_product_in_the_warehouse() throws Exception {
		Product product = new Product(1, "Product1", "Product1Desc", "10/11/2017");
		when(restTemplateServiceImpl.getSku(1))
				.thenReturn("barcode123");
		when(productWareHouseRepository.isProductAvailable(product)).thenReturn(false);
		try {
			productServiceImpl.create(product);
		} catch (ProductException e) {
			throw e;
		}
	}
}
