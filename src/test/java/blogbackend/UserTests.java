package blogbackend;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import annoatations.Authors;
import annoatations.RetryFailedCount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import listeneres.IAnnotationTransformerImpl;
import listeneres.ITestListenerImpl;
import listeneres.RestAssuredFilterImpl;
import reporting.ExtentLogger;

@Listeners({ ITestListenerImpl.class, IAnnotationTransformerImpl.class })
public class UserTests {

	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8081/api/v1";
	}
	
	public static ExtentLogger logger = new ExtentLogger();

	@Test(groups = { "Sanity", "Regression" })
	@RetryFailedCount(value = 2)
	@Authors(authors = "Shivam")
	public void getAllUserTest() {
		RequestSpecification reqSpecs = given().filter(new RestAssuredFilterImpl()).header("abc", "pqr")
				.cookie("Cooki1").cookie("Cooki2", "Value2");
		Response resp = reqSpecs.get("/users/");
		resp.then().statusCode(200).body("[0].name", equalTo("Shivam"));
	}

}