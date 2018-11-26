package automation_framework.test_suites;

import automation_framework.Pages.Homepage;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class automation_test_suite {
	
  private WebDriver webdriver;
  private String baseURL = "http://automation-sandbox.herokuapp.com";
  private Homepage homepage;
  
  @DataProvider(name = "login_data")
  public static Object[][] credentials() {
         return new Object[][] { 
        	 { "demouser", "abc123", null },
        	 { "Demouser", "", "Wrong username or password." },
        	 { "demouser_", "xyz", "Wrong username or password." },
        	 { "demouser", "nananana", "Wrong username or password." },
        	 { "Demouser", "abc123", "Wrong username or password." }
         };
  }
  
  @BeforeTest
  public void beforeTest() {
	  webdriver = new FirefoxDriver();
	  webdriver.get(baseURL);
  }

 
  @Test(dataProvider = "login_data")
  public void validateLogin(String username, String password, String message) throws InterruptedException {
	  homepage = new Homepage (webdriver);
	  Thread.sleep(5000);
	  homepage.fill_textbox_username(username);
	  Thread.sleep(1000);
	  homepage.fill_textbox_password(password);
	  Thread.sleep(1000);
	  homepage.click_button_submit();
	  Thread.sleep(7000);
	  WebElement login_message = homepage.getLogin_failure_msgt();
	  
	  if (login_message == null && message == null) {
		  //webdriver.findElement(By.linkText("Logout")).click();
		  assertEquals(login_message,message);
	  }
	  else
		  assertEquals(login_message.getText(), message);
  }
  
  @AfterMethod
  public void goBackToHomepage ( ) throws InterruptedException {
	    webdriver.manage().deleteAllCookies();
        webdriver.navigate().to(baseURL) ;
        Thread.sleep(5000);
  }
  
  /*
  @Test
  public void validateLoginNegative() throws InterruptedException {
	  homepage = new Homepage (webdriver);
	  Thread.sleep(5000);
	  homepage.fill_textbox_username("Demouser");
	  Thread.sleep(1000);
	  homepage.fill_textbox_password("");
	  Thread.sleep(1000);
	  homepage.click_button_submit();
	  Thread.sleep(7000);
	  
	  WebElement login_message = homepage.getLogin_failure_msgt();
	  assertEquals(login_message.getText(), "Wrong username or password.");
  }
  
  @Test
  public void validateLoginPositive() throws InterruptedException {
	  homepage = new Homepage (webdriver);
	  Thread.sleep(5000);
	  homepage.fill_textbox_username("demouser");
	  Thread.sleep(1000);
	  homepage.fill_textbox_password("abc123");
	  Thread.sleep(1000);
	  homepage.click_button_submit();
	  Thread.sleep(7000);
	  
	  WebElement login_message = homepage.getLogin_failure_msgt();
	  assertEquals(login_message, null,"Failed to login");
	 
  }
  */  
  
  @AfterTest
  public void afterTest() {
	  webdriver.quit();
  }
  
}
