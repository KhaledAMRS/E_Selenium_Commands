package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T03_String_By_WebElement_Action {
    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
        // 1- Bridge
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");

        // 2- Create new object of WebDriver
        driver = new ChromeDriver();
        // 3- Configure your driver using driver.manage()
        driver.manage().window().maximize();
        // check only one condition: if the element is exist on DOM Page or not
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://the-internet.herokuapp.com");
    }

    @Test(priority = 1)
    public void findelement() throws InterruptedException {
        driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
// we should know that this line consist of 	>>  String, By, WebElement, Action   

        driver.navigate().back();
        Thread.sleep(1000);


        // String, By, WebElement, Action			below lines are exact equal to the line above
        String LocatorTxt = "a[href=\"/login\"]";
        By LocatorBy = By.cssSelector(LocatorTxt);
        WebElement LocatorEle = driver.findElement(LocatorBy);
        LocatorEle.click();

// Why to learn something like this? 
/*
This is an introduction to one of the most important design patterns in Selenium called POM Design Pattern
The main idea of applying this pattern is to separate between WebElements and Actions 
*/

// Why should we separate between WebElements and Actions ?
/*
It is useful in reducing code duplication and improves test case maintenance, we will learn this topic in the following chapters.
*/

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}