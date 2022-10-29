package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T10_Static_Dynamic_dropdowns {
    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
    System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void test1() throws InterruptedException {
// Example on static dropdown list (tagName Select)
    driver.navigate().to("http://the-internet.herokuapp.com/dropdown");

    WebElement selectElement = driver.findElement(By.id("dropdown"));
    Select selectObject = new Select(selectElement);

    // Select an <option> based upon the <select> element's internal index
   selectObject.selectByIndex(1);		// counting start from 0 (Please select an option)  1 (Option 1)   2 (Option2)

// Select an <option> based upon its value attribute
   selectObject.selectByValue("1");	// This is an attribute where key name is value

// Select an <option> based upon its text
   selectObject.selectByVisibleText("Option 2");		// Link Text

//Note: you could use Select method or findelements method with static dropdown list
// but with dynamic dropdown list you could only use findelements method
    }

    @Test(priority = 2)
    public void test2() throws InterruptedException {
// you couldn't use Select with dynamic dropdown, instead you could use findElements to handle dynamic dropdown
// Example: go to this website https://www.ebay.com/ and try to select any option inside "My eBay" dropdown list on the top right of the screen
// This is the locator of "My eBay" list     ul[id="gh-ul-nav"]
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
