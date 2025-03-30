package base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import listeneres.ExtentLoggingFilter;

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

}
