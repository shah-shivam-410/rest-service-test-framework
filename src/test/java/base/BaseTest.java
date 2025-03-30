package base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import listeneres.ExtentLoggingFilter;

import static io.restassured.RestAssured.given;

public class BaseTest {
	
	public static final Filter EXTENT_LOGGING_FILTER = new ExtentLoggingFilter();
	public static final Filter ALLURE_LOGGING_FILTER = new AllureRestAssured();
	
	public static final Properties props = new Properties();
	
	public static synchronized void setProps(String key, String object) {
		props.setProperty(key, String.valueOf(object));
	}
	
	public static synchronized String getProps(String key) {
		return props.getProperty(key);
	}
	
	public static void printProps() {
		System.out.println(props);
	}
	
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8081/api/v1";
	}

	/**
	 * Executes a POST request.
	 * @param useAuth Whether to include authentication headers
	 * @param endpoint API endpoint
	 * @param body Request body
	 * @return Response
	 */
	public Response postRequest(boolean useAuth, String endpoint, Object body) {
		RequestSpecification request = createRequest(useAuth);
		return request.body(body).post(endpoint);
	}

	/**
	 * Executes a GET request.
	 * @param useAuth Whether to include authentication headers
	 * @param endpoint API endpoint
	 * @param pathParams Path parameters
	 * @return Response
	 */
	public Response getRequest(boolean useAuth, String endpoint, Map<String, Object> pathParams) {
		RequestSpecification request = createRequest(useAuth);
		if (pathParams != null) {
			request.pathParams(pathParams);
		}
		return request.get(endpoint);
	}

	/**
	 * Executes a PUT request.
	 * @param useAuth Whether to include authentication headers
	 * @param endpoint API endpoint
	 * @param body Request body
	 * @param pathParams Path parameters
	 * @return Response
	 */
	public Response putRequest(boolean useAuth, String endpoint, Object body, Map<String, Object> pathParams) {
		RequestSpecification request = createRequest(useAuth);
		if (pathParams != null) {
			request.pathParams(pathParams);
		}
		return request.body(body).put(endpoint);
	}

	/**
	 * Executes a DELETE request.
	 * @param useAuth Whether to include authentication headers
	 * @param endpoint API endpoint
	 * @param pathParams Path parameters
	 * @return Response
	 */
	public Response deleteRequest(boolean useAuth, String endpoint, Map<String, Object> pathParams) {
		RequestSpecification request = createRequest(useAuth);
		if (pathParams != null) {
			request.pathParams(pathParams);
		}
		return request.delete(endpoint);
	}

	/**
	 * Creates a base request specification.
	 * @param useAuth Whether to include authentication headers
	 * @return RequestSpecification
	 */
	private RequestSpecification createRequest(boolean useAuth) {
		RequestSpecification request = given()
			.filter(ALLURE_LOGGING_FILTER)
			.contentType(io.restassured.http.ContentType.JSON);
		if (useAuth) {
			request.headers(getAuthHeaders());
		}
		return request;
	}

	/**
	 * Generates headers with authentication token.
	 * @return Map of headers
	 */
	private Map<String, String> getAuthHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + getProps("token"));
		return headers;
	}
}
