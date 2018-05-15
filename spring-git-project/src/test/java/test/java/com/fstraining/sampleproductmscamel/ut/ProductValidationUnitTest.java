package test.java.com.fstraining.sampleproductmscamel.ut;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.fstraining.springgitproject.model.Product;
import com.ibm.fstraining.springgitproject.validation.ProductValidation;


public class ProductValidationUnitTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			Product product = new Product();
			String mfgdate = new String("date1");
			String sku = new String("sku1");
			product.setId(100);
			product.setMfgdate(mfgdate);
			product.setProductName("Product1");
			ProductValidation validation = new ProductValidation();
			assertTrue("TestPass", validation.validateProduct(product));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test1() {
		Product product = new Product();
		String mfgdate = null;
		String sku = null;
		product.setId(-100);
		product.setMfgdate(mfgdate);
		ProductValidation validation = new ProductValidation();
		assertFalse("TestPass", validation.validateProduct(product));
	}

}
