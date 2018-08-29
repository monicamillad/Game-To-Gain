package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRegister {

	WebDriver driver ;
	String signUp="http://localhost:8080/sign_up";
	String URL = "http://localhost:8080/verify";

	String name = "user";
	String username = "username";
	String phoneNumber = "01111111111";
	String teacherEmail = "username@fci.edu.com";
	String password = "123456789";
	String cpassword = "123456789";
	String cpassword1 = "125698743";
	String date = "01/01/2001";

	String falseStudentEmail = "usernamefci.com";


	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(signUp);	
	}


	// Success Registration as a Teacher
	@Test(priority=0)
	public void LoginTest1() throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("email")).sendKeys(teacherEmail);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("cpassword")).sendKeys(cpassword);
		driver.findElement(By.id("dateOfBirth")).sendKeys(date);
		driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);

		Select select1 = new Select(driver.findElement(By.id("type")));
		select1.selectByValue("teacher");

		Select select2 = new Select(driver.findElement(By.id("gender")));
		select2.selectByValue("male");

		driver.findElement(By.id("submit1")).click();
		Thread.sleep(30, 0);

		if(driver.findElement(By.id("createcourse")) != null){
			AssertJUnit.assertEquals(driver.getCurrentUrl(),URL);
		}
	}


	// Failed Registration as a Teacher
	@Test(priority=1)
	public void LoginTest2() throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("email")).sendKeys(teacherEmail);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("cpassword")).sendKeys(cpassword);
		driver.findElement(By.id("dateOfBirth")).sendKeys(date);
		driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);

		Select select1 = new Select(driver.findElement(By.id("type")));
		select1.selectByIndex(1);

		Select select2 = new Select(driver.findElement(By.id("gender")));
		select2.selectByIndex(1);

		driver.findElement(By.id("submit1")).click();
		Thread.sleep(30, 0);

		if(driver.findElement(By.id("name")) != null){
			AssertJUnit.assertEquals(driver.getCurrentUrl(),URL);
		}
	}

	
	// Failed Registration as a student
	// false email
	@Test(priority=2)
	public void LoginTest3() throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("email")).sendKeys(falseStudentEmail);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("cpassword")).sendKeys(cpassword);
		driver.findElement(By.id("dateOfBirth")).sendKeys(date);
		driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);

		Select select1 = new Select(driver.findElement(By.id("type")));
		select1.selectByIndex(1);

		Select select2 = new Select(driver.findElement(By.id("gender")));
		select2.selectByIndex(1);

		driver.findElement(By.id("submit1")).click();
		Thread.sleep(30, 0);

		AssertJUnit.assertEquals(driver.getCurrentUrl(),signUp);
	}

	
	// Failed Registration as a Teacher
	// CPassword != Password
	@Test(priority=3)
	public void LoginTest4() throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("email")).sendKeys(teacherEmail);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("cpassword")).sendKeys(cpassword1);
		driver.findElement(By.id("dateOfBirth")).sendKeys(date);
		driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);

		Select select1 = new Select(driver.findElement(By.id("type")));
		select1.selectByIndex(1);

		Select select2 = new Select(driver.findElement(By.id("gender")));
		select2.selectByIndex(1);

		driver.findElement(By.id("submit1")).click();
		Thread.sleep(30, 0);

		if(driver.findElement(By.id("name")) != null){
			AssertJUnit.assertEquals(driver.getCurrentUrl(),URL);
		}
	}
	
	
	@AfterMethod
	public void terminateBrowser() {
		driver.close();
	}
}
