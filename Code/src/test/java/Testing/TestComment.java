package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestComment {
	WebDriver driver ;
	String login="http://localhost:8080/login";

	String trueUsername = "username";
	String truePassword = "123456789";

	String Comment = "Test Comment";
	
	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}

	// There is a Comment
	@Test(priority=0)
	public void CommentTest1() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);
		
		// Click On All Courses Button
		driver.findElement(By.xpath("//*[@id='mainav']/ul/li[2]/a")).click();
		
		// Click On Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click On Game Name
		driver.findElement(By.xpath("//*[@id='featured']/article[1]/a[2]/span")).click();
		
		// Comment
		driver.findElement(By.id("lastComment")).sendKeys(Comment);
		
		// Click On Comment Button
		driver.findElement(By.xpath("//*[@id='top']/div[3]/div/footer/form/button")).click();
		
		AssertJUnit.assertEquals(driver.getCurrentUrl(),driver.getCurrentUrl());
	}

	// Empty TextField 
	@Test(priority=1)
	public void CommentTest2() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);
		
		// Click On All Courses Button
		driver.findElement(By.xpath("//*[@id='mainav']/ul/li[2]/a")).click();
		
		// Click On Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click On Game Name
		driver.findElement(By.xpath("//*[@id='featured']/article[1]/a[2]/span")).click();
		
		// Click On Comment Button
		driver.findElement(By.xpath("//*[@id='top']/div[3]/div/footer/form/button")).click();
		
		AssertJUnit.assertEquals(driver.getCurrentUrl(),driver.getCurrentUrl());
	}

	@AfterMethod
	public void terminateBrowser() throws InterruptedException {
		driver.close();
	}
}
