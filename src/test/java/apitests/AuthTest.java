package apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import annoatations.Authors;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeneres.ExtentLoggingFilter;

public class AuthTest extends BaseTest {
	

	@Test(groups = {"Sanity"}, priority = 0)
	@Authors(authors = "Shivam")
	public void tokenGenerationTest() {
		Map<String, String> map = Map.of(
					"email", "aaa@bbb.ccc",
					"password", "a1b2c3d4"
				);
		Response response = given()
		.filter(ALLURE_LOGGING_FILTER)
		.contentType(ContentType.JSON)
		.body(map)
		.post("/auth/login")
		.then().statusCode(200).body("token", notNullValue())
		.extract().response();
		setProps("token", response.jsonPath().getString("token"));
		
		
	}

}
