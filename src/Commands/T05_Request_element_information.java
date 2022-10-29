package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class T05_Request_element_information {
    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/login");
    }

    @Test(priority = 1)
    public void test() throws InterruptedException {
	/*
Request Element Information means that there are a number of details you can query about a specific element like.
	- You could get text of the element
	- You could get attribute of the element 
	- You could get css value of the element 

We will have examples below about all of this.
	*/



        // First step: Enter valid username
        WebElement username = driver.findElement(By.cssSelector("input[id=\"username\"]"));
        username.clear();       // not necessary but in case there was old value inside this field from another test case
        username.sendKeys("tomsmith");

        // Second step: Enter valid password
        WebElement password = driver.findElement(By.cssSelector("input[id=\"password\"]"));
        password.clear();     // same thing like username (not necessary)
        password.sendKeys("SuperSecretPassword!");

        // Third step: Login button
        password.sendKeys(Keys.ENTER);    // or   driver.findElement(By.className("fa-sign-in")).click();
        // Note: with sendKeys(Keys.ENTER) is used with password field while click() is used with Login button

        // getText()
        WebElement successMsgEle = driver.findElement(By.id("flash"));
        String successMsg = successMsgEle.getText();
        System.out.println("successMsg:  "+successMsg);

        // getAtrribute("")
        String hrefLink = driver.findElement(By.linkText("Elemental Selenium")).getAttribute("href");
        System.out.println("hrefLink:  "+hrefLink);

        // getCssValue("")		using CssValue you could get different details like element color, background color, font size, etc
        String backgroundColor_RGBA = driver.findElement(By.id("flash")).getCssValue("background-color");
        System.out.println("backgroundColor_RGBA:  "+backgroundColor_RGBA);
		
		// if you want to convert value from RGBA to Hex 	 	Note: this Color.fromString below belongs to 	import org.openqa.selenium.support.Color;
        String backgroundColor_Hex = Color.fromString(backgroundColor_RGBA).asHex();
        System.out.println("backgroundColor_Hex:  "+backgroundColor_Hex);

        String TextColor_RGBA = driver.findElement(By.id("flash")).getCssValue("color");
        System.out.println("TextColor_RGBA:  "+TextColor_RGBA);

        String TextColor_Hex = Color.fromString(TextColor_RGBA).asHex();
        System.out.println("TextColor_Hex:  "+TextColor_Hex);

        // Soft Assert: new object should be created
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(successMsg.contains("You logged into a secure area"),"successMsg");
        soft.assertTrue(hrefLink.contains("http://elementalselenium.com/"),"hrefLink");

        soft.assertTrue(backgroundColor_RGBA.contains("(93, 164, 35, 1)"),"background color RGBA");
        soft.assertTrue(backgroundColor_Hex.contains("#5da423"),"background color Hex");

        soft.assertTrue(TextColor_RGBA.contains("(255, 255, 255, 1)"), "white text color RGBA");
        soft.assertTrue(TextColor_Hex.contains("#ffffff"), "white text color Hex");

        soft.assertAll();

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
