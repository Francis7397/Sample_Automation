package pageobjects;




import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import utilities.DockerBaseTest;

public class DockerActivity extends DockerBaseTest{
	
	RemoteWebDriver driver;
	
	public DockerActivity(RemoteWebDriver driver) {
		this.driver = driver;

	}

	@FindBy(id = "signin_button")
	public WebElement signButton;

	@FindBy(id = "user_login")
	public WebElement loginUserName;

	public void signIn() {

		
		signButton.click();

		
		loginUserName.sendKeys("username");

	}


}
