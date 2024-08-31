package listeneres;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import annoatations.RetryFailedCount;

public class IRetryAnalyzerImpl implements IRetryAnalyzer {

	int counter = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		RetryFailedCount annotation = result
				.getMethod()
				.getConstructorOrMethod()
				.getMethod()
				.getAnnotation(RetryFailedCount.class);
		if(annotation != null && (counter < annotation.value())) {
			counter++;
			return true;
		}
		return false;
	}

}
