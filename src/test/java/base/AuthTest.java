package base;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import annoatations.Authors;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import listeneres.RestAssuredFilterImpl;

public class AuthTest {
	
	@Test(groups = { "Sanity" })
	@Authors(authors = "Shivam")
	public void tokenGenerationTest() {
		Map<String, String> map = Map.of(
					"email", "eew@bbb.erwec",
					"password", "eqgr3223#@"
				);
		given()
		.filter(new RestAssuredFilterImpl())
		.contentType(ContentType.JSON)
		.body(map)
		.post("/auth/login")
		.then().statusCode(200).body("token", notNullValue());
		
	}

}
