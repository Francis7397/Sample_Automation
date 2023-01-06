package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;



import utilities.LambdaTestBaseTest;

public class SecondScenario extends LambdaTestBaseTest {
	
	//private WebDriver driver;

	public SecondScenario(WebDriver driver) {
		this.driver = driver;

	}
	
	@FindBy(xpath = "//a[contains(text(),'Drag & Drop Sliders')]")
	public WebElement dragDropSliders;
	
	@FindBy(xpath = "//input[@value='15']")
	public WebElement greenSliders;
	
	@FindBy(id = "rangeSuccess")
	public WebElement sliderOutput;
	
	
	
	
	public void scenarioTwo()   {
		waitUntilElementClickable(dragDropSliders);
		dragDropSliders.click();
		
		
		if (browserName.contains("chrome")) {
			Actions action = new Actions(driver);
			action.dragAndDropBy(greenSliders, 118, 0).perform();
			
			waitUntilElementClickable(sliderOutput);
			String actualValue = sliderOutput.getText();
			System.out.println(actualValue);
			String expectedValue = "95";
			Assert.assertEquals(actualValue, expectedValue);
			

		} 
		else if (browserName.contains("firefox")) {
			Actions act = new Actions(driver);
			act.dragAndDropBy(greenSliders, 115, 0).perform();
			
			waitUntilElementClickable(sliderOutput);
			String actualValue1 = sliderOutput.getText();
			String expectedValue1 = "95";
			Assert.assertEquals(actualValue1, expectedValue1);
			System.out.println(actualValue1);
		}
		
//		Actions action = new Actions(driver);
//		action.dragAndDropBy(greenSliders, 118, 0).perform();
//		
//		waitUntilElementClickable(sliderOutput);
//		String actualValue = sliderOutput.getText();
//		System.out.println(actualValue);
//		String expectedValue = "95";
//		Assert.assertEquals(actualValue, expectedValue);
		
	
	}

}
