package com.la.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LambdaTest1 {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "francisbellajay05" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "V8grmvXYqz1beIVmeu5pQphkSIr0Jem8DAgf74XUJeMgceIJm1"
				: System.getenv("LT_ACCESS_KEY");
		String hub = "@hub.lambdatest.com/wd/hub";

		DesiredCapabilities caps = new DesiredCapabilities();
		// Configure your capabilities here
		caps.setCapability("platform", "MacOS Ventura");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("version", "107.0");
		caps.setCapability("resolution", "1024x768");
		caps.setCapability("build", "TestNG With Java");
		caps.setCapability("name", m.getName() + this.getClass().getName());
		caps.setCapability("plugin", "git-testng");

		String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
		caps.setCapability("tags", Tags);

		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
	}

	@Test
	public void basicTest() throws InterruptedException {
		String spanText;
		System.out.println("Loading Url");

		driver.get("https://lambdatest.github.io/sample-todo-app/");

		System.out.println("Checking Box");
		driver.findElement(By.name("li1")).click();

		System.out.println("Checking Another Box");
		driver.findElement(By.name("li2")).click();

		System.out.println("Checking Box");
		driver.findElement(By.name("li3")).click();

		System.out.println("Checking Another Box");
		driver.findElement(By.name("li4")).click();

		System.out.println("Checking Another Box");
		driver.findElement(By.name("li5")).click();

		// Let's also assert that the todo we added is present in the list.

		spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[5]/span")).getText();
		Assert.assertEquals("Fifth Item", spanText);
		Status = "passed";
		Thread.sleep(150);

		System.out.println("TestFinished");

	}

	@Test
	public void testUntitledTestCase() throws Exception {
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.linkText("Simple Form Demo")).click();
		String actualURL = driver.getCurrentUrl();
		System.out.println(actualURL);
		String ExpectedURL = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
		Assert.assertEquals(actualURL, ExpectedURL);
		WebElement rightSideTextMessage = driver.findElement(By.id("user-message"));
		
		rightSideTextMessage.sendKeys("Welcome");
		driver.findElement(By.id("showInput")).click();
		String actualText = driver.findElement(By.id("message")).getText();
		System.out.println(actualText);
		String expectedText = "Welcome";

		Assert.assertEquals(actualText, expectedText);
	}

	@Test
	public void testUntitledTestCase1() throws Exception {
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		Actions action = new Actions(driver);
		action.dragAndDropBy(driver.findElement(By.xpath("//input[@value='15']")), 103, 0).perform();
		String actualValue = driver.findElement(By.id("rangeSuccess")).getText();
		System.out.println(actualValue);
		String expectedValue = "95";
		Assert.assertEquals(actualValue, expectedValue);
	}

	@Test
	public void testUntitledTestCase2() throws Exception {
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.linkText("Input Form Submit")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		WebElement nameTextField = driver.findElement(By.id("name"));
		String actualErrorMessage = nameTextField.getAttribute("validationMessage");
		System.out.println(actualErrorMessage);
		String expectedErrorMessage = "Please fill in this field.";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		driver.findElement(By.id("name")).sendKeys("test");

		driver.findElement(By.id("inputEmail4")).sendKeys("test@yopmail.com");

		driver.findElement(By.id("inputPassword4")).sendKeys("test@12345");

		driver.findElement(By.id("company")).sendKeys("test");

		driver.findElement(By.id("websitename")).sendKeys("test");
		driver.findElement(By.name("country")).click();
		new Select(driver.findElement(By.name("country"))).selectByVisibleText("United States");

		driver.findElement(By.id("inputCity")).sendKeys("test");

		driver.findElement(By.id("inputAddress1")).sendKeys("test");

		driver.findElement(By.id("inputAddress2")).sendKeys("test");

		driver.findElement(By.id("inputState")).sendKeys("test");

		driver.findElement(By.id("inputZip")).sendKeys("test");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		WebElement successMessage = driver
				.findElement(By.xpath("//div[@id='__next']/div/section[3]/div/div/div[2]/div/p"));
		String actualTextMessage = successMessage.getText();
		System.out.println(actualTextMessage);
		String expectedTextMessage = "Thanks for contacting us, we will get back to you shortly.";
		Assert.assertEquals(actualTextMessage, expectedTextMessage);
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}