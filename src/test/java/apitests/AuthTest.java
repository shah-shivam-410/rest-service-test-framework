package apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import annoatations.Authors;
import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthTest extends BaseTest {
	

	@Test(groups = {"Sanity"}, priority = 0)
	@Authors(authors = "Shivam")
	public void tokenGenerationTest() {
		Map<String, String> map = Map.of(
			"email", "aaa@bbb.ccc",
			"password", "a1b2c3d4"
		);
		Response response = postRequest(false, "/auth/login", map);
		setProps("token", response.jsonPath().getString("token"));
	}

}
