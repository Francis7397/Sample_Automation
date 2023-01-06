package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.FirstScenario;
import pageobjects.SecondScenario;
import pageobjects.ThirdScenario;

public class LambdaTestBaseTest {

	public FirstScenario Demo;
	public SecondScenario Demo1;
	public ThirdScenario Demo2;

	protected WebDriver driver;

	public String browserName;
	 String username = System.getenv("LT_USERNAME") == null ? "francisbellajay05" : System.getenv("LT_USERNAME");
     String authkey = System.getenv("LT_ACCESS_KEY") == null ? "V8grmvXYqz1beIVmeu5pQphkSIr0Jem8DAgf74XUJeMgceIJm1" : System.getenv("LT_ACCESS_KEY");
     String hub = "@hub.lambdatest.com/wd/hub";

	@Parameters("browserName")
	@BeforeMethod
	public void baseTest(String browser) {
		browserName = browser;

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("lambdachrome")) {
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("MacOS Ventura");
			browserOptions.setBrowserVersion("107.0");
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("username", "francisbellajay05");
			ltOptions.put("accessKey", "V8grmvXYqz1beIVmeu5pQphkSIr0Jem8DAgf74XUJeMgceIJm1");
			ltOptions.put("resolution", "2048x1536");
			ltOptions.put("project", "Untitled");
			ltOptions.put("selenium_version", "4.1.2");
			ltOptions.put("w3c", true);
			ltOptions.put("plugin", "java-testNG");
			browserOptions.setCapability("LT:Options", ltOptions);			try {
				driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (browser.equalsIgnoreCase("lambdafirefox")) {
			FirefoxOptions browserOptions = new FirefoxOptions();
			browserOptions.setPlatformName("MacOS Ventura");
			browserOptions.setBrowserVersion("107.0");
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("username", "francisbellajay05");
			ltOptions.put("accessKey", "V8grmvXYqz1beIVmeu5pQphkSIr0Jem8DAgf74XUJeMgceIJm1");
			ltOptions.put("resolution", "2048x1536");
			ltOptions.put("project", "Untitled");
			ltOptions.put("selenium_version", "4.1.2");
			ltOptions.put("w3c", true);
			ltOptions.put("plugin", "java-testNG");
			browserOptions.setCapability("LT:Options", ltOptions);
			try {
				driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		waitForPageLoad();
		Demo = PageFactory.initElements(driver, FirstScenario.class);
		Demo1 = PageFactory.initElements(driver, SecondScenario.class);
		Demo2 = PageFactory.initElements(driver, ThirdScenario.class);
		Demo.setBrowser(this.browserName);
		Demo1.setBrowser(this.browserName);
		Demo2.setBrowser(this.browserName);

//		 WebDriverManager.firefoxdriver().setup();
//	     driver = new FirefoxDriver();
//	 	driver.get("https://www.lambdatest.com/selenium-playground/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		Demo = PageFactory.initElements(driver, FirstScenario.class);
//		Demo1 = PageFactory.initElements(driver, SecondScenario.class);
//		Demo2 = PageFactory.initElements(driver, ThirdScenario.class);

	}

	@AfterMethod(alwaysRun = true)
	public void close() {

		driver.quit();

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

	public void setBrowser(String browser) {
		this.browserName = browser;
	}

}
