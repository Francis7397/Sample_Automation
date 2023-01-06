package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.Activity;
import pageobjects.ScreenShot;

public class BaseTest {

	public Activity first;
	public ScreenShot test;

	protected WebDriver driver;

	@BeforeMethod
	public void baseTest() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();

		driver.get("http://zero.webappsecurity.com/");
//		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		first = PageFactory.initElements(driver, Activity.class);
		test = PageFactory.initElements(driver, ScreenShot.class);

	}

	@AfterMethod
	public void close() {

		driver.close();
		driver.quit();

	}

	public void takeScreenShot(WebElement element) throws IOException {

		File f = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f, new File("/Users/francisbell.ajaycdw.com/Documents/screenshots.png"));
	}
	public void waitUntilElementClickable(WebElement element) {
		{
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.elementToBeClickable(element));
		}
	}

	public void waitForPageLoad() {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
	
	public void extentReport() {
		
		
		ExtentReports extent = new ExtentReports();
		
		
		
		File file = new File(System.getProperty("user.dir")+"/ExtentReports/reports.html");
		String userDir = System.getProperty("user.dir");
		System.out.println(userDir);
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		
		extent.attachReporter(sparkReporter);
		ExtentTest test1 = extent.createTest("Test Name", "Test Description");
		test.log(Status.INFO, "Test Step 1"); 
		try { // code for test step
			test.log(Status.PASS, "Test Step Passed"); } 
		catch (AssertionError e) { test.log(Status.FAIL, "Test Step Failed"); } extent.flush();
		}
		
		
	}


