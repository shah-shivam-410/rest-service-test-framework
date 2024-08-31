package reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	ExtentTest getExtentTest() {
		return extentTest.get();
	}

	void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}

	void unload() {
		extentTest.remove();
	}

}
