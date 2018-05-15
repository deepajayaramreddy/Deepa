package test.java.com.fstraining.sampleproductmscamel.restassured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProductServicemSISTTest {

	public static Workbook workbook = null;

	public static RequestSpecBuilder getProductBuilder;
	public static RequestSpecification getProductRequestSpec;

	public static RequestSpecBuilder postProductBuilder;
	public static RequestSpecification postProductRequestSpec;

	public static String hostPort = "http://localhost:8080";

	public static ResponseSpecBuilder builder;
	public static ResponseSpecification responseSpec;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String INPUT_FILE = "./src/test/resources/DATA_REST.xls";
		workbook = WorkbookFactory.create(new File(INPUT_FILE));

		getProductBuilder = new RequestSpecBuilder();
		getProductBuilder.addHeader("Content-Type", "application/json");

		postProductBuilder = new RequestSpecBuilder();
		postProductBuilder.addHeader("Content-Type", "application/json");
		// getProductBuilder.addHeader("Accept", "abcd-123-xyz");
		// getProductBuilder.addParameter("loginID", "joebloggs");
		getProductRequestSpec = getProductBuilder.build();

		builder = new ResponseSpecBuilder();
		builder.expectStatusCode(200);
		responseSpec = builder.build();

	}

	@Test
	public void TC_GET() {

		DataFormatter dataFormatter = new DataFormatter();

		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
			if (sheet.getSheetName().equals("GetProduct")) {
				for (Row row : sheet) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						System.out.print(cellValue + "\t");
						if (cellValue != null) {
							String[] inputs = cellValue.split(",");
							Response res = given().spec(getProductRequestSpec).when()
									.get(hostPort + "/sampleproductmscamel/service/products?id=" + inputs[0]
											+ "&customerType=" + inputs[1]);
							String bodyRes = res.getBody().asString();
							System.out.println("Response===" + bodyRes);
							assertFalse(res.getStatusCode()!=200);
							String mfgdate = res.jsonPath().getString("mfgdate");
							assertFalse(mfgdate == null);
						}
					}
				}

			}
		}

	}

	@Test
	public void TC_POST() {
		DataFormatter dataFormatter = new DataFormatter();
		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
			if (sheet.getSheetName().equals("CreateProduct")) {
				for (Row row : sheet) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						System.out.print(cellValue + "\t");
						if (cellValue != null) {
							getProductBuilder.setBody(cellValue);
							postProductRequestSpec = getProductBuilder.build();
							Response res = given().spec(postProductRequestSpec).when()
									.post(hostPort + "/sampleproductmscamel/service/createProduct");
							String bodyRes = res.getBody().asString();
							System.out.println(res.getStatusCode());
							assertFalse(res.getStatusCode()!=200);
							assertFalse(bodyRes.equals("false"));
						}
					}
				}

			}
		}
	}
	
	/*@Test
	public void TC_POST_COMPLEX() {
		
		DataFormatter dataFormatter = new DataFormatter();
		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
			if (sheet.getSheetName().equals("CreateProduct")) {
				for (Row row : sheet) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						System.out.print(cellValue + "\t");
						if (cellValue != null) {
							getProductBuilder.setBody(cellValue);
							postProductRequestSpec = getProductBuilder.build();
							Response res = given().spec(postProductRequestSpec).when()
									.post(hostPort + "/sampleproductmscamel/service/createProduct");
							String bodyRes = res.getBody().asString();
							System.out.println(res.getStatusCode());
							assertFalse(res.getStatusCode()!=200);
							assertFalse(bodyRes.equals("false"));
							
							JsonPath jsonPath = JsonPath.from(bodyRes);
							List<Object> values = jsonPath.getList("tab.reportBody.values");
							ArrayList<HashMap<String, ArrayList>> valuesList = (ArrayList<HashMap<String, ArrayList>>) values.get(0);
							for (HashMap<String, ArrayList> hm : valuesList) {
								System.out.println(hm);
								ArrayList<HashMap<String, String>> valuesInside = hm.get("rowDetails");
								for (HashMap<String, String> valuesHm : valuesInside) {
								}
							}
							
						}
					}
				}

			}
		}
		
		
	}*/
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
