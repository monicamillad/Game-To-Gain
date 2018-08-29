package Testing;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCreateCourse {
	WebDriver driver ;
	String login="http://localhost:8080/login";
	String createCourse="http://localhost:8080/create_course/username";
	String saveCouseURL = "http://localhost:8080/save_course/username";
	
	String trueCourseName = "Course Name";
	String courseDescription = "Course Description";
	String lowerAge = "10" ;
	
	String trueUsername = "username";
	String truePassword = "123456789";
	
	
	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}
	
	
	@Test(priority=0)
	public void CreatCourseTest1() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
        driver.findElement(By.id("password")).sendKeys(truePassword);
        driver.findElement(By.id("login1")).click();
        Thread.sleep(200);
     
		driver.findElement(By.id("createcourse")).click();
				
		driver.findElement(By.id("name")).sendKeys(trueCourseName);
        
        Select select1 = new Select(driver.findElement(By.id("category")));
        select1.selectByValue("Programming");
        
        driver.findElement(By.id("description")).sendKeys(courseDescription);
        driver.findElement(By.id("lowerAge")).sendKeys(lowerAge);
        
        driver.findElement(By.id("createcourse1")).click();
        Thread.sleep(30, 0);
        
        AssertJUnit.assertEquals(driver.getCurrentUrl(),saveCouseURL);
	}
	
	
	@Test(priority=1)
	public void CreatCourseTest2() throws InterruptedException{
		driver.findElement(By.id("username")).sendKeys(trueUsername);
        driver.findElement(By.id("password")).sendKeys(truePassword);
        driver.findElement(By.id("login1")).click();
        Thread.sleep(200);
        
        
		driver.findElement(By.id("createcourse")).click();
				
        
        Select select1 = new Select(driver.findElement(By.id("category")));
        select1.selectByValue("Programming");
        
        driver.findElement(By.id("description")).sendKeys(courseDescription);
        driver.findElement(By.id("lowerAge")).sendKeys(lowerAge);
        
        driver.findElement(By.id("createcourse1")).click();
        Thread.sleep(30, 0);
        
        AssertJUnit.assertEquals(driver.getCurrentUrl(),createCourse);
	}
	
	 @AfterMethod
	 public void terminateBrowser() throws InterruptedException {
		  driver.close();
	 }
}
