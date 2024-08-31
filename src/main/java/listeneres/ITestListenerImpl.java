package listeneres;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import annoatations.Authors;
import reporting.ExtentLogger;
import reporting.ExtentReport;

public class ITestListenerImpl implements ITestListener {
	
	private ExtentReport extentReport = null;
	private ExtentLogger extentLogger = null;
	
//	private static final Logger LOGGER = LogManager.getLogger(ITestListenerImpl.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		extentReport.createTest(result.getName());
		Authors annotation = result
				.getMethod()
				.getConstructorOrMethod()
				.getMethod()
				.getAnnotation(Authors.class);
		if(annotation != null) {
			extentReport.assignTestAuthor(annotation.authors());
		}
		extentReport.assignTestCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentLogger.pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//extentLogger.captureScreenshot();
		extentLogger.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentLogger.skip(MarkupHelper.createLabel("Test skipped", ExtentColor.PURPLE));
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport = new ExtentReport();
		extentReport = new ExtentReport();
		extentLogger = new ExtentLogger();
		extentReport.initReports();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flushReports();
	}

	
	
}
