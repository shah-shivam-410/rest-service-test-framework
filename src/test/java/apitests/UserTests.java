package apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import annoatations.Authors;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import listeneres.IAnnotationTransformerImpl;
import listeneres.ITestListenerImpl;
import listeneres.ExtentLoggingFilter;
import reporting.ExtentLogger;
import utils.RandomStringGenerator;

@Listeners({ ITestListenerImpl.class, IAnnotationTransformerImpl.class })
public class UserTests extends BaseTest {

	public static ExtentLogger logger = new ExtentLogger();

	
	@Test(groups = {"Regression"}, priority = 1)
	@Authors(authors = "Shivam")
	public void createUserTest() {
		Map<String, String> requestBody = Map.of(
					"name", RandomStringGenerator.generateAlpha(8),
					"email", RandomStringGenerator.generateAlphaNumeric(7) + "@" + RandomStringGenerator.generateAlpha(6) + ".com",
					"password", RandomStringGenerator.generateAlphaNumericSpecialChars(8),
					"about", RandomStringGenerator.generateAlpha(20)
				);
		Response response = given()
		.filter(ALLURE_LOGGING_FILTER)
		.auth().oauth2((String) getProps("token"))
		.contentType(ContentType.JSON)
		.body(requestBody)
		.post("/users/")
		.then().statusCode(201)
		.extract().response();
		
		Map<Object, Object> responseBody = response.jsonPath().getMap("$");
		assertEquals(responseBody.get("name"), requestBody.get("name"));
		assertEquals(responseBody.get("email"), requestBody.get("email"));
		assertEquals(responseBody.get("about"), requestBody.get("about"));
		
		setProps("createdUserId", String.valueOf(response.jsonPath().getInt("id")));
	}
	
	@Test(groups = {"Sanity"}, priority = 2)
	@Authors(authors = "Sid")
	public void getCreatedUserTest() {
		Response response = given()
		.filter(ALLURE_LOGGING_FILTER)
		.pathParam("id", getProps("createdUserId"))
		.get("/users/{id}")
		.then().statusCode(200)
		.extract().response();
		
		assertEquals(String.valueOf(response.jsonPath().getInt("id")), getProps("createdUserId"));
	}
	
	@Test(groups = {"Sanity", "Regression"}, priority = 3)
	@Authors(authors = "Shivam")
	public void getAllUserTest() {
		Response response = given()
		.filter(ALLURE_LOGGING_FILTER)
		.get("/users/")
		.then().statusCode(200)
		.body("[0].name", equalTo("Shivam"))
		.extract().response();
		
		List<Map> list = response.jsonPath().getList("$", Map.class);
		assertTrue(list.stream().anyMatch(e -> String.valueOf(e.get("id")).equals(getProps("createdUserId"))), "Created user doesn't exist");
	}
	
	@Test(groups = {"Sanity", "Regression"}, priority = 3)
	@Authors(authors = "Shivam")
	public void updateUserTest() {
		Response response = given()
		.filter(ALLURE_LOGGING_FILTER)
		.get("/users/")
		.then().statusCode(200)
		.body("[0].name", equalTo("Shivam"))
		.extract().response();
		
		List<Map> list = response.jsonPath().getList("$", Map.class);
		assertTrue(list.stream().anyMatch(e -> String.valueOf(e.get("id")).equals(getProps("createdUserId"))), "Created user doesn't exist");
		
	}
	
	
	
}