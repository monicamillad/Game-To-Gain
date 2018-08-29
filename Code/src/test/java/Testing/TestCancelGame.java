package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class TestCancelGame {
	WebDriver driver ;
	String login="http://localhost:8080/login";

	String trueCourseName = "Course";

	String trueUsername = "username";
	String truePassword = "123456789";

	String trueCollaborator = "username2";
	String falseCollaborator = "falseUsername";
	
	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}

	// Success
	@Test(priority=0)
	public void CancelGameTes1() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);

		driver.findElement(By.id("allcourses")).click();
		
		// Click On Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click On Cancel Button
		driver.findElement(By.xpath("//*[@id='featured']/article[1]/a[4]/span")).click();
		
		//AssertJUnit.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/cancel_game/");
	}

	@AfterMethod
	public void terminateBrowser() throws InterruptedException {
		driver.close();
	}
}
