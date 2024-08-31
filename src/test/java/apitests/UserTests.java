package apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import annoatations.Authors;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import listeneres.IAnnotationTransformerImpl;
import listeneres.ITestListenerImpl;
import listeneres.RestAssuredFilterImpl;
import reporting.ExtentLogger;

@Listeners({ ITestListenerImpl.class, IAnnotationTransformerImpl.class })
public class UserTests extends BaseTest {

	public static ExtentLogger logger = new ExtentLogger();

	@Test(groups = { "Sanity", "Regression" })
	//@RetryFailedCount(value = 2)
	@Authors(authors = "Shivam")
	public void getAllUserTest() {
		given()
		.filter(new RestAssuredFilterImpl())
		.header("abc", "pqr")
		.cookie("Cooki1").cookie("Cooki2", "Value2")
		.get("/users/")
		.then().statusCode(200).body("[0].name", equalTo("Shivam"));
		
	}
	
	@Test(groups = { "Regression" })
	@Authors(authors = "Shivam")
	public void createUserTest() {
		Map<String, String> map = Map.of(
					"name", "WeweTdw",
					"email", "eew@bbb.erwec",
					"password", "eqgr3223#@",
					"about", "Workplace Expert"
				);
		given()
		.filter(new RestAssuredFilterImpl())
		.auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFAYmJiLmNjYyIsImlhdCI6MTcyNTEyNjkzMiwiZXhwIjoxNzI1MTMwNTMyfQ.e4LXQy8wgZS6rdNR1ix2ABpL5Ou8AhX4pHH_ga0Lkxo")
		.contentType(ContentType.JSON)
		.body(map)
		.post("/users/")
		.then().statusCode(201).body("name", equalTo("WeweTdw"));
		
	}

}