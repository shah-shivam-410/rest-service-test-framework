package apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import annoatations.Authors;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeneres.IAnnotationTransformerImpl;
import listeneres.ITestListenerImpl;
import reporting.ExtentLogger;
import utils.RandomStringGenerator;

@Listeners({ ITestListenerImpl.class, IAnnotationTransformerImpl.class })
public class UserTests extends BaseTest {

	public static ExtentLogger logger = new ExtentLogger();

	
	@Test(groups = {"Regression"}, priority = 1)
	@Authors(authors = "Shivam")
	public void createUserTest() {
		setProps("createdName", RandomStringGenerator.generateAlpha(8));
		setProps("createdEmail", RandomStringGenerator.generateAlphaNumeric(7) + "@" + RandomStringGenerator.generateAlpha(6) + ".com");
		setProps("createdPassword", RandomStringGenerator.generateAlphaNumericSpecialChars(8));
		setProps("createdAbout", RandomStringGenerator.generateAlpha(20));

		Map<String, String> requestBody = Map.of(
					"name", getProps("createdName"),
					"email", getProps("createdEmail"),
					"password", getProps("createdPassword"),
					"about", getProps("createdAbout")
				);
		Response response = postRequest(true, "/users/", requestBody);
		
		Map<Object, Object> responseBody = response.jsonPath().getMap("$");
		assertEquals(responseBody.get("name"), requestBody.get("name"));
		assertEquals(responseBody.get("email"), requestBody.get("email"));
		assertEquals(responseBody.get("about"), requestBody.get("about"));
		
		setProps("createdUserId", String.valueOf(response.jsonPath().getInt("id")));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/createUserResponseSchema.json"));
	}
	
	@Test(groups = {"Sanity"}, priority = 2)
	@Authors(authors = "Sid")
	public void getCreatedUserTest() {
		Response response = getRequest(false, "/users/{id}", Map.of("id", getProps("createdUserId")));
		
		assertEquals(String.valueOf(response.jsonPath().getInt("id")), getProps("createdUserId"));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/getUserResponseSchema.json"));
	}
	
	@Test(groups = {"Sanity", "Regression"}, priority = 3)
	@Authors(authors = "Shivam")
	public void getAllUserTest() {
		Response response = getRequest(false, "/users/", null)
			.then().statusCode(200)
			.body("[0].name", equalTo("Shivam"))
			.extract().response();
		
		List<Map> list = response.jsonPath().getList("$", Map.class);
		assertTrue(list.stream().anyMatch(e -> String.valueOf(e.get("id")).equals(getProps("createdUserId"))), "Created user doesn't exist");
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/getAllUsersResponseSchema.json"));
	}
	
	@Test(groups = {"Sanity", "Regression"}, priority = 4)
	@Authors(authors = "Shivam")
	public void updateUserTest() {
		setProps("updatedName", RandomStringGenerator.generateAlpha(8));
		Map<String, String> requestBody = Map.of(
				"name", getProps("updatedName"),
				"email", getProps("createdEmail"),
				"password", getProps("createdPassword"),
				"about", getProps("createdAbout"));
		Response response = putRequest(true, "/users/{id}", requestBody, Map.of("id", getProps("createdUserId")));
		
		assertEquals(response.jsonPath().getString("name"), getProps("updatedName"));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/updateUserResponseSchema.json"));
	}
	
	@Test(groups = { "Sanity" }, priority = 5)
	@Authors(authors = "Sid")
	public void deletedUserTest() {
		Response response = deleteRequest(true, "/users/{id}", Map.of("id", getProps("createdUserId")));
		assertEquals(response.jsonPath().getString("message"), "User deleted successfully");
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/deleteUserResponseSchema.json"));
	}
	
}