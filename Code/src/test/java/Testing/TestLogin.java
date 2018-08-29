package Testing;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestLogin {
	WebDriver driver ;
	String login="http://localhost:8080/login";
	String URL = "http://localhost:8080/verifylogin";
	
	String trueUsername = "username" ;
	String truePassword = "123456789" ;
	
	String falseUsername = "falseUsername" ;
	String falsePassword = "123456987" ;
	
	@BeforeMethod
	public void OpenBrowser(){
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}
	
	
	// Success Login as a Teacher
	@Test(priority=0)
	public void LoginTest1() throws InterruptedException{
        driver.findElement(By.id("username")).sendKeys(trueUsername);
        driver.findElement(By.id("password")).sendKeys(truePassword);
        driver.findElement(By.id("login1")).click();
        Thread.sleep(30, 0);
        
        if(driver.findElement(By.id("createcourse")) != null){
        	AssertJUnit.assertEquals(driver.getCurrentUrl(),URL);
        }
	}
	
	
	// Failed Login as a Teacher
	@Test(priority=1)
	public void LoginTest2() throws InterruptedException{
		
		driver.findElement(By.id("username")).sendKeys(falseUsername);
        driver.findElement(By.id("password")).sendKeys(falsePassword);
        driver.findElement(By.id("login1")).click();
        Thread.sleep(30, 0);
        
        if(driver.findElement(By.id("login1")) != null){
        	AssertJUnit.assertEquals(driver.getCurrentUrl(),URL);
        } 
	}
	
	 @AfterMethod
	 public void terminateBrowser() {
		  driver.close();
	 }
}
