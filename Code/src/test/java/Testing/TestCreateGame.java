package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCreateGame {

	WebDriver driver ;
	String login="http://localhost:8080/login";
	String createCourse="http://localhost:8080/create_course/username";
	String saveCouseURL = "http://localhost:8080/save_course/username";
	
//	String saveCouseURL = "http://localhost:8080/save_course/username";
	
	String trueCourseName = "Course Name";
	String courseDescription = "Course Description";
	String lowerAge = "10" ;
	
	String trueCourseName2 = "Course Name2";
	
	
	String trueUsername = "username";
	String truePassword = "123456789";
	
	String GameName="Game Name";
	String gameDescription="Game Description";
	
	@BeforeMethod
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login);	
	}
	
	
	@Test(priority=0)
	public void CreatCourseTest1MCQ() throws InterruptedException{
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
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("name")).sendKeys(GameName);
        
        driver.findElement(By.id("description")).sendKeys(gameDescription);
        
        Select select2 = new Select(driver.findElement(By.id("type")));
        select2.selectByValue("MultipleChoice");
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("q")).sendKeys("q");
        
        driver.findElement(By.id("answer")).sendKeys("a");
        
        driver.findElement(By.id("c1")).sendKeys("b");
        
        driver.findElement(By.id("c2")).sendKeys("c");
        
        driver.findElement(By.id("c3")).sendKeys("d");
        
        driver.findElement(By.id("c4")).sendKeys("a");
        
        driver.findElement(By.id("NewQuestion")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("q")).sendKeys("q");
        
        driver.findElement(By.id("answer")).sendKeys("a");
        
        driver.findElement(By.id("c1")).sendKeys("b");
        
        driver.findElement(By.id("c2")).sendKeys("a");
        
        driver.findElement(By.id("c3")).sendKeys("d");
        
        driver.findElement(By.id("c4")).sendKeys("e");
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame"));
	}
	
	
	@Test(priority=1)
	public void CreatCourseTest2TF() throws InterruptedException{
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
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("name")).sendKeys(GameName);
        
        driver.findElement(By.id("description")).sendKeys(gameDescription);
        
        Select select2 = new Select(driver.findElement(By.id("type")));
        select2.selectByValue("TrueFalse");
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("q")).sendKeys("q1");
        
        driver.findElement(By.id("answer1")).click();
        
        driver.findElement(By.id("NewQuestion")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("q")).sendKeys("q2");
        
        driver.findElement(By.id("answer2")).click();;
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame"));
        
	}
	//game name=null
	@Test(priority=2)
	public void CreatCourseTest3() throws InterruptedException{
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
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame")).click();
        Thread.sleep(200);
        
       // driver.findElement(By.id("name")).sendKeys(GameName); 
        
        driver.findElement(By.id("description")).sendKeys(gameDescription);
        
        Select select2 = new Select(driver.findElement(By.id("type")));
        select2.selectByValue("TrueFalse");
        
        String s=driver.getCurrentUrl();
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        AssertJUnit.assertEquals(driver.getCurrentUrl(),s);
	}
	
	//game without questions TF
	@Test(priority=3)
	public void CreatCourseTest4() throws InterruptedException{
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
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("name")).sendKeys(GameName); 
        
        driver.findElement(By.id("description")).sendKeys(gameDescription);
        
        Select select2 = new Select(driver.findElement(By.id("type")));
        select2.selectByValue("TrueFalse");
        
        
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        String s=driver.getCurrentUrl();
        
        driver.findElement(By.id("CreateGame")).click();
        Thread.sleep(200);
        
        AssertJUnit.assertEquals(driver.getCurrentUrl(),s);
	}
	
	//MCQ with empty question 
	@Test(priority=4)
	public void CreatCourseTest5() throws InterruptedException{
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
        Thread.sleep(200);
        
        driver.findElement(By.id("addgame")).click();
        Thread.sleep(200);
        
        driver.findElement(By.id("name")).sendKeys(GameName); 
        
        driver.findElement(By.id("description")).sendKeys(gameDescription);
        
        Select select2 = new Select(driver.findElement(By.id("type")));
        select2.selectByValue("MultipleChoice");
      
       
       driver.findElement(By.id("CreateGame")).click();
       Thread.sleep(200);
       
       driver.findElement(By.id("q")).sendKeys("q1");
       
       driver.findElement(By.id("answer")).sendKeys("a");
       
       driver.findElement(By.id("c1")).sendKeys("b");
       
       driver.findElement(By.id("c2")).sendKeys("c");
       
       driver.findElement(By.id("c3")).sendKeys("d");
       
       driver.findElement(By.id("c4")).sendKeys("a");
       
       driver.findElement(By.id("NewQuestion")).click();
       Thread.sleep(200);
       
      // driver.findElement(By.id("q")).sendKeys("q2"); question=null
       
       driver.findElement(By.id("answer")).sendKeys("a");
       
       driver.findElement(By.id("c1")).sendKeys("b");
       
       driver.findElement(By.id("c2")).sendKeys("a");
       
       driver.findElement(By.id("c3")).sendKeys("d");
       
       driver.findElement(By.id("c4")).sendKeys("e");
       
       String s=driver.getCurrentUrl();
       driver.findElement(By.id("CreateGame")).click();
       Thread.sleep(200);
        
       AssertJUnit.assertEquals(driver.getCurrentUrl(),s);
    
       driver.findElement(By.id("q")).sendKeys("q2");
    
       driver.findElement(By.id("CreateGame")).click();
       Thread.sleep(200);
       driver.findElement(By.id("addgame"));
        
	}	
	 @AfterMethod
	 public void terminateBrowser() throws InterruptedException {
		  driver.quit();
	 }
}
