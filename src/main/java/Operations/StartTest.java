package Operations;

import DriverFactory.Browsers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.java.Before;


public class StartTest {

    private static Browsers browser;
    private static String env;
    private static String yamlFile;
    private static ExtentReports extentReports;

    public static Operation getOperation() {
        return operation;
    }

    private static Operation operation;
public StartTest(){}
    public StartTest(Browsers browser, String env, String yamlFile) throws Exception {
        setBrowser(browser);
        setEnv(env);
        setYamlFile(yamlFile);
        operation = new Operation();

    }

    public static Browsers getBrowser() {
        return browser;
    }

    public static void setBrowser(Browsers browser) throws Exception {
        if ((System.getProperty("browser") == null))
            StartTest.browser = browser;
        else {
            try {
                StartTest.browser = Browsers.valueOf(System.getProperty("browser").toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new Exception("wrong.browser.exception: Please provide correct browser value from global variable");
            }
        }
        if (browser == null) {
            throw new Exception("wrong.browser.exception: Please provide browser value");
        }
        if (browser.equals(Browsers.CHROME)) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").concat("\\Drivers\\chromedriver.exe"));
        } else if (browser.equals(Browsers.IE)) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir").concat("\\Drivers\\IEDriverServer.exe"));
        } else if (browser.equals(Browsers.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir").concat("\\Drivers\\geckodriver.exe"));
        }
    }

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        StartTest.env = env;
    }

    public static String getYamlFile() {
        return yamlFile;
    }

    public static void setYamlFile(String yamlFile) {
        StartTest.yamlFile = yamlFile;
    }


    @Before
    public void onStart() {
        ExtentReports extentReports = new ExtentReports();
        StartTest.extentReports = extentReports;
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports");
        extentReports.attachReporter(extentHtmlReporter);
        System.out.println("------Test Started-----------");
    }

}





