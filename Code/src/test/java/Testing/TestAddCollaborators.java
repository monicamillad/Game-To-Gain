package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAddCollaborators {
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
	public void CopyGameTest1() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);

		driver.findElement(By.id("allcourses")).click();
		
		// Click On Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click On Game Name
		driver.findElement(By.xpath("//*[@id='featured']/article[1]/a[2]/span")).click();
		
		// Click On Add Collaborator Button
		driver.findElement(By.xpath("//*[@id='top']/div[3]/div/div[1]/footer[3]/form/button")).click();
		
		driver.findElement(By.name("name")).sendKeys(trueCollaborator);
		
		// Click On Add Button
		driver.findElement(By.xpath("//*[@id='addcollaborator']")).click();
	}


	// Failed
	@Test(priority=1)
	public void AddCollaboratorTest2() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
		driver.findElement(By.id("password")).sendKeys(truePassword);
		driver.findElement(By.id("login1")).click();
		Thread.sleep(200);

		driver.findElement(By.id("allcourses")).click();
		
		// Click On Course Name
		driver.findElement(By.xpath("//*[@id='gallery']/figure/article[1]/a[2]/span")).click();

		// Click On Game Name
		driver.findElement(By.xpath("//*[@id='featured']/article[1]/a[2]/span")).click();
		
		// Click On Add Collaborator Button
		driver.findElement(By.xpath("//*[@id='top']/div[3]/div/div[1]/footer[3]/form/button")).click();
		
		driver.findElement(By.name("name")).sendKeys(falseCollaborator);
		
		// Click On Add Button
		driver.findElement(By.xpath("//*[@id='addcollaborator']")).click();
	}

	@AfterMethod
	public void terminateBrowser() throws InterruptedException {
		driver.close();
	}
}
