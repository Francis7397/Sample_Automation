package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;



import utilities.LambdaTestBaseTest;

public class FirstScenario extends LambdaTestBaseTest {
//	Create a variable for a string value
	private static final String LambdaTest = "Welcome to LambdaTest";

	//private WebDriver driver;

	public FirstScenario(WebDriver driver) {
		this.driver = driver;

	}
	
	@FindBy(xpath = "//a[contains(text(),'Simple Form Demo')]")
	public WebElement simpleFormDemo;
	
	@FindBy(xpath = "//input[contains(@id,'user-message')]")
	public WebElement pleaseEnterYourMessage;
	
	
	
	@FindBy(id = "showInput")
	public WebElement getCheckedValue;
	
	@FindBy(xpath = "//p[@id='message']")
	public WebElement rightSideTextMessage;
	
	
	
	public void scenarioOne() {
		
		
//		Click “Simple Form Demo” under Input Forms.
		waitUntilElementClickable(simpleFormDemo);
		simpleFormDemo.click();
		
		
		
//		Validate that the URL contains “simple-form-demo”.
		
		String actualURL = driver.getCurrentUrl();
		System.out.println(actualURL);
		String ExpectedURL = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
		Assert.assertEquals(actualURL, ExpectedURL);
		
//		Use this variable to enter values in the “Enter Message” text box.
		waitUntilElementClickable(pleaseEnterYourMessage);
		pleaseEnterYourMessage.sendKeys(LambdaTest);
		
//		Click “Get Checked Value”.
		waitUntilElementClickable(getCheckedValue);
		getCheckedValue.click();
		
		
//		Validate whether the same text message is displayed
		waitUntilElementClickable(rightSideTextMessage);
		String actualText = rightSideTextMessage.getText();
		System.out.println(actualText);
		String expectedText = "Welcome to LambdaTest";
		
		Assert.assertEquals(actualText, expectedText);
		
		
		
	
		
		
		
		
	}

}
