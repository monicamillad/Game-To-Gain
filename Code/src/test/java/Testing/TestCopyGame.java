package Testing;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestCopyGame {
	WebDriver driver ;
	String login="http://localhost:8080/login";


	String trueCourseName = "Course";
	String falseCourseName = "falseCourse";

	String trueUsername = "username";
	String truePassword = "123456789";


	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}

	// Success
	@Test(priority=0)
	public void CopyGameTest1() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);

		driver.findElement(By.id("allcourses")).click();
		
		// Click on Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click on Copy Button
		driver.findElement(By.xpath("//*[@id='featured']/article/a[3]/span")).click();

		driver.findElement(By.id("name")).sendKeys(trueCourseName);

		// Click on Paste Button
		driver.findElement(By.xpath("//*[@id='copygame']")).click(); 
	}


	// Failed
	@Test(priority=1)
	public void CopyGameTest2() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);

		driver.findElement(By.id("allcourses")).click();
		
		// Click on Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click on Copy Button
		driver.findElement(By.xpath("//*[@id='featured']/article/a[3]/span")).click();
		
		driver.findElement(By.id("name")).sendKeys(falseCourseName);

		// Click on Paste Button
		driver.findElement(By.xpath("//*[@id='copygame']")).click(); 
	}

	@AfterMethod
	public void terminateBrowser() throws InterruptedException {
		driver.close();
	}
}
