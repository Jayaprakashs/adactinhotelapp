package utilis;


import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class CustomListener extends TestListenerAdapter {
    static Logger log = Logger.getLogger(CustomListener.class);

    @Override
    public void onTestFailure(ITestResult Result) {
        log.error("The name of the testcase failed is :" + Result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult Result) {
        log.error("The name of the testcase failed is :" + Result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        log.info("The name of the testcase failed is :" + Result.getName());
    }

}
