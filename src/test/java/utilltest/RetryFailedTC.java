package utilltest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTC implements IRetryAnalyzer{

	int count=0;
	int maxTry=1;
	@Override
	public boolean retry(ITestResult result) {
		// Retry execution for failed test cases
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
