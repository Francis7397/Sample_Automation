package pageobjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import utilities.BaseTest;

public class ScreenShot extends BaseTest {
	
	public ScreenShot(WebDriver driver) {
		this.driver = driver;
		
		
//		PageFactory.initElements(driver, this);

	}
	@FindBy(id = "twotabsearchtextbox")
	public WebElement textField;
	
	
	public void activityScreen() throws IOException {
		
		textField.sendKeys("BTS");
		
		File f = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f, new File("/Users/francisbell.ajaycdw.com/Documents/screenshots/amazon.png"));
	}


	public void log(Status pass, String string) {
		// TODO Auto-generated method stub
		
	}

}
