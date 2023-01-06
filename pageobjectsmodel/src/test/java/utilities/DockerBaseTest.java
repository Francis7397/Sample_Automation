package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import pageobjects.DockerActivity;



public class DockerBaseTest 

{
	public DockerActivity first;
	
	RemoteWebDriver driver;
	
	@BeforeMethod
	public  void test() throws MalformedURLException {
		
	
		
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.CHROME);
		
		URL url = new URL("http://localhost:4440/wd/hub");
		
		RemoteWebDriver driver = new RemoteWebDriver(url,cap);
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		first = PageFactory.initElements(driver, DockerActivity.class);
		
		
		
	}

   @Deprecated
	public interface BrowserType{
	   String CHROME = "chrome";
	   
   }
   @AfterMethod
	public void close() {

		driver.quit();

	}


}
