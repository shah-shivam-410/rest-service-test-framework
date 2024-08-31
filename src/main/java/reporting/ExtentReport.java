package reporting;

import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	private static final String REPORT_PATH = ".//extent-reports//";

	ExtentReports extent = null;
	ExtentManager extentManager = null;

	public ExtentReport() {
		extentManager = new ExtentManager();
	}

	public void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH + "TestAutomationReport.html");
			try {
				spark.loadXMLConfig(".//src//main//resources//extent-spark-config.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			extent.attachReporter(spark);
			extent.setSystemInfo("Device", "Lenovo S340");
			extent.setSystemInfo("OS", "Windows 10");
			extent.setSystemInfo("Organization", "Personal");
		}
	}

	public void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
	}

	public void createTest(String testCaseName) {
		extentManager.setExtentTest(extent.createTest(testCaseName));
	}

	public void completeTest() {
		extentManager.unload();
	}

	public void assignTestAuthor(String[] authors) {
		extentManager.getExtentTest().assignAuthor(authors);
	}

	public void assignTestCategory(String[] category) {
		extentManager.getExtentTest().assignCategory(category);
	}

//    synchronized public void addAuthors(AuthorType[] authors) {
//        for (AuthorType author : authors) {
//            extentManager.getExtentTest().assignAuthor(author.toString());
//        }
//    }

//    synchronized public void addCategories(CategoryType[] categories) {
//        // for (String category : categories) {
//        for (CategoryType category : categories) {
//            extentManager.getExtentTest().assignCategory(category.toString());
//        }
//    }

//    synchronized public void addDevices() {
//        extentManager.getExtentTest().assignDevice(BrowserInfoUtils.getBrowserInfo());
////		extentManager.getExtentTest()
////				.assignDevice(BrowserIconUtils.getBrowserIcon() + " : " + BrowserInfoUtils.getBrowserInfo());
//    }

}
