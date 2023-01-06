package pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import com.beust.jcommander.Parameter;

import utilities.BaseTest;

public class Activity extends BaseTest {

	public Activity(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(id = "signin_button")
	public WebElement signButton;

	@FindBy(id = "user_login")
	public WebElement loginUserName;

	public void signIn() throws IOException {

		waitUntilElementClickable(signButton);
		signButton.click();

		waitUntilElementClickable(loginUserName);
		loginUserName.sendKeys("username");

	}

}
