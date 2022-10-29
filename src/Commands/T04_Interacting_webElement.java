package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T04_Interacting_webElement {
    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/login");

    }

    @Test(priority = 0)
    public void test() throws InterruptedException {

  // First step: Enter valid username
   WebElement username = driver.findElement(By.cssSelector("input[id=\"username\"]"));
   username.clear();       // not necessary but in case there was old value inside this field from another test case
   username.sendKeys("tomsmith");

  // Second step: Enter valid password
   WebElement password = driver.findElement(By.cssSelector("input[id=\"password\"]"));
   password.clear();     // same thing like username (not necessary)
   password.sendKeys("SuperSecretPassword!");

  // Third step: Login button
   password.sendKeys(Keys.ENTER);    
   
   //or
	/*
	WebElement SignInBtn = driver.findElement(By.className("fa-sign-in"));
	SignInBtn.click();
	*/
   
  // Note: with sendKeys(Keys.ENTER) is used with password field while click() is used with Login button

    WebElement successMsgEle = driver.findElement(By.id("flash"));
    System.out.println(successMsgEle.getText());

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
