package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T01_implicitlyWait {
    /*
	* The Implicit Wait in Selenium is used to tell the web driver to wait for a certain amount of time as long as the element still not present in DOM Page.

	* The mechanism is to check if the element is present in DOM Page every 0.5 second, if the element is present in DOM Page then the code will proceed without having to wait the whole timeout assigned.

	* If the element still not exist in DOM page after the timeout then there are two cases
		- If you are using findElement()  	 >>     then the code will throw a “No Such Element Exception” error and the test case will be failed
		- If you are using findElements()	 >> 	No Error will be thrown and test case will continue the next step without any failure


	* Keep in Mind: implicitlyWait has only one condition which is waiting until the element is exist in DOM Page
	but explicitlyWait has multiple conditions unlike implicitlyWait
	For example
		wait unitl element is visible on UI Page
		wait until element is clickable on UI Page
		wait until the url of the browser to be...
		wait until number of browser tabs to equal...

		We will discuss all of those conditions in explicitlyWait section
		
		
    * Note: ImplicitlyWait & explicitlyWait are reducing the needs to use Thread.sleep in the code but don't eliminate it, which means we still need to use Thread.sleep() but within a small range.
	
 */

    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
     System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
     driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    driver.get("https://www.google.com/");      // we will discuss the difference between driver.get("") and driver.navigate().to("");

    }

    @Test(priority = 1)
    public void test() throws InterruptedException {
        // without implicitlyWait, we will have to set Thread.sleep() to wait until the search field is present in DOM Page
        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys("Selenium");
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    // If we want the driver to wait for 2 seconds before quiting the driver to observe the last step, then implicitlyWait won't help here, so we will have to use Thread.sleep().
    // As we mentioned before, implicitlyWait & explicitlyWait don't eliminate Thread.sleep() but reduce it
    Thread.sleep(2000);
    driver.quit();
    }

}