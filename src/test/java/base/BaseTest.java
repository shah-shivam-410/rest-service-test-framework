package base;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseTest {
	
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8081/api/v1";
	}

}
