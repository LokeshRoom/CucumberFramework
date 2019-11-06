package Operations;


import ObjectUtils.ObjectReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Operation extends ObjectReader {

    private WebDriver driver;
    private JSONObject yamlJsonObject;
    private ExtentReports extentReports;

    private ExtentTest extentTest;
    private ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\Reports.html");

    public Operation() throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentTest = extentReports.createTest("My Test Case Name");
        this.setYamlJsonObject(StartTest.getYamlFile(), System.getProperty("user.dir") + "\\src\\test\\ObjectRepository\\");

    }


    public JSONObject getYamlJsonObject() {
        return yamlJsonObject;
    }

    public void setYamlJsonObject(String fileName, String path) throws Exception {
        this.yamlJsonObject = ObjectReader.readYaml(fileName, path);
    }

    private WebElement findElement(String element, String page) {
        return driver.findElement(ObjectReader.getElement(yamlJsonObject, element, page));
    }

    public void goToUrl(String urlObject) {
        String url = ObjectReader.getUrl(yamlJsonObject, urlObject);
        driver.get(url);
        extentTest.log(Status.PASS, "Launched Url: " + url);

    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
        extentTest.log(Status.PASS, "Navigate to URL: " + url);
    }

    public void enterText(String element, String page, CharSequence text) throws Exception {
        WebElement webElement = this.findElement(element, page);
        webElement.sendKeys(text);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String scrst = screenshot.getScreenshotAs(OutputType.BASE64);
        extentTest.log(Status.PASS, "Enterted Text: " + text);
        extentTest.addScreenCaptureFromBase64String(scrst);

    }

    public void click(String element, String page) {
        WebElement webElement = this.findElement(element, page);
        webElement.click();
        extentTest.log(Status.PASS, "Clicked on element: " + element);
        extentReports.flush();
    }

    public void doubleClick(String element, String page) {
        try {
            WebElement webElement = this.findElement(element, page);
            Actions actions = new Actions(driver);
            actions.doubleClick(webElement).perform();
            extentTest.log(Status.PASS, "Double clicked on Element" + element);
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Double click failed on Element:" + e);
        }
    }

    public void rightClick(String element, String page) {
        try {
            WebElement webElement = this.findElement(element, page);
            Actions actions = new Actions(driver);
            actions.contextClick(webElement).build().perform();
            extentTest.log(Status.PASS, "Right click on Element" + element);
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Right click failed on Element:" + e);
            throw e;
        }
    }

    public void assertTextExists(String text) throws Exception {
        try {
            WebElement webElement = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
            if (webElement == null)
                throw new Exception();
            extentTest.log(Status.PASS, "Text exists in page");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Right click failed on Element:" + e);
            throw e;
        }
    }

    public void javaScriptExecutorClick(String element, String page) {
        WebElement webElement = this.findElement(element, page);
        JavascriptExecutor jexecutor = (JavascriptExecutor) driver;
        jexecutor.executeScript("arguments[].click()", webElement);
    }

    public void acceptAlert(String acceptOrReject) throws Exception {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptOrReject.equalsIgnoreCase("accept")) {
                alert.accept();
                extentTest.log(Status.PASS, "Alert Accepted");
            } else if (acceptOrReject.equalsIgnoreCase("reject")) {
                alert.dismiss();
                extentTest.log(Status.PASS, "Alert Dismissed");
            } else throw new Exception("Enter Accept or Reject");

        } catch (NoAlertPresentException e) {
            extentTest.log(Status.FAIL, "No Alerts are present on page:" + e);
            throw e;
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Not able to " + acceptOrReject + ":" + e);
            throw e;
        }
    }

    @After
    public void exit() {
        driver.quit();
        extentReports.flush();

    }

}
