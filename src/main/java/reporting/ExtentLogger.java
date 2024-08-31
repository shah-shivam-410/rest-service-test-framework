package reporting;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ExtentLogger {
	
	ExtentManager extentManager = null;
	
	public ExtentLogger() {
		extentManager = new ExtentManager();
	}

	public void pass(String message) {
        extentManager.getExtentTest().pass(message);
    }

    public void pass(Markup message) {
        extentManager.getExtentTest().pass(message);
    }

    public void fail(String message) {
        extentManager.getExtentTest().fail(message);
    }
    
    public void failBlock(String message) {
    		extentManager.getExtentTest().fail(MarkupHelper.createCodeBlock(message));
    }
    
    public void fail(Throwable t) {
        extentManager.getExtentTest().fail(t);
    }

    public void fail(Markup message) {
        extentManager.getExtentTest().fail(message);
    }

    public void skip(String message) {
        extentManager.getExtentTest().skip(message);
    }

    public void skip(Markup message) {
        extentManager.getExtentTest().skip(message);
    }

    public void info(Markup message) {
        extentManager.getExtentTest().info(message);
    }

    public void info(String message) {
        extentManager.getExtentTest().info(message);
    }
    
    public void infoBlock(String message) {
        extentManager.getExtentTest().info(MarkupHelper.createCodeBlock(message));
    }

//    public void captureScreenshot() {
//        extentManager.getExtentTest().info("Capturing Screenshot",
//                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
//    }


	
}
