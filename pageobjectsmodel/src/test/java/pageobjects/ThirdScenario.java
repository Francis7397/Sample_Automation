package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;



import utilities.LambdaTestBaseTest;

public class ThirdScenario extends LambdaTestBaseTest {
	
	//private WebDriver driver;

	public ThirdScenario(WebDriver driver) {
		this.driver = driver;

	}
	
	@FindBy(xpath = "//a[contains(text(),'Input Form Submit')]")
	public WebElement inputFormSubmit;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement submitButton;
	
	@FindBy(css = ".text-label")
	public WebElement name;
	
	@FindBy(css = "#name")
	public WebElement nameTextField;
	

	
	@FindBy(id = "inputEmail4")
	public WebElement emailTextField;
	
	@FindBy(id = "inputPassword4")
	public WebElement passwordTextField;
	
	@FindBy(css = "#company")
	public WebElement companyTextField;
	
	@FindBy(id = "websitename")
	public WebElement websiteTextField;
	
	@FindBy(name = "country")
	public WebElement countryDropDown;
	
	@FindBy(id = "inputCity")
	public WebElement cityTextField;
	
	@FindBy(id = "inputAddress1")
	public WebElement addressOneTextField;
	
	@FindBy(id = "inputAddress2")
	public WebElement addressTwoTextField;
	
	@FindBy(css = "#inputState")
	public WebElement stateTextField;
	
	@FindBy(id = "inputZip")
	public WebElement zipCodeTextField;
	
	@FindBy(css = "p.hidden")
	public WebElement successMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Browser Automation')]")
	public WebElement browser;
	
	
	public void ScenarioThree() {
		waitUntilElementClickable(browser);
		inputFormSubmit.click();
		
		
		waitUntilElementClickable(submitButton);
		submitButton.click();
		
		
		if (browserName.equalsIgnoreCase("chrome")) {
			String actualErrorMessage = nameTextField.getAttribute("validationMessage");
			System.out.println(actualErrorMessage);
			String expectedErrorMessage = "Please fill in this field.";
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String actualErrorMessage1 = nameTextField.getAttribute("validationMessage");
			System.out.println(actualErrorMessage1);
			String expectedErrorMessage1 = "Please fill out this field.";
			Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);

		}
				
		waitUntilElementClickable(name);
		name.click();
		
		waitUntilElementClickable(nameTextField);
		nameTextField.sendKeys("user");
		
		waitUntilElementClickable(emailTextField);
		emailTextField.sendKeys("test@yopmail.com");
		
		waitUntilElementClickable(passwordTextField);
		passwordTextField.sendKeys("test@123");
		
		waitUntilElementClickable(companyTextField);
		companyTextField.sendKeys("testcompany");
		
		waitUntilElementClickable(websiteTextField);
		websiteTextField.sendKeys("testwebsite");
		
		waitUntilElementClickable(countryDropDown);
		Select country = new Select(countryDropDown);
		country.selectByVisibleText("United States");
		
		waitUntilElementClickable(cityTextField);
		cityTextField.sendKeys("testcuty");
		
		waitUntilElementClickable(addressOneTextField);
		addressOneTextField.sendKeys("testaddressone");
		
		waitUntilElementClickable(addressTwoTextField);
		addressTwoTextField.sendKeys("testaddresstwo");
		
		waitUntilElementClickable(stateTextField);
		stateTextField.sendKeys("teststate");
		
		waitUntilElementClickable(zipCodeTextField);
		zipCodeTextField.sendKeys("12345");
		
		waitUntilElementClickable(submitButton);
		submitButton.click();
		
		waitUntilElementClickable(successMessage);
		String actualTextMessage = successMessage.getText();
		System.out.println(actualTextMessage);
		String expectedTextMessage = "Thanks for contacting us, we will get back to you shortly.";
		Assert.assertEquals(actualTextMessage, expectedTextMessage);
		
		
		
		
		
		
	}


	
		
	

}
