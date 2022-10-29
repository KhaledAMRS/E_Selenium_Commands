package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T02_difference_between_navigate_get {
    /*
Difference between driver.navigate and driver.get ?
Each one has an advantage and we simply could mix between them to get the best.

	* Advantage of navigate() is that it contains the following unlike get()
    driver.navigate().back();
    driver.navigate().forward();
    driver.navigate().refresh();

	* Advantage of get()
    It is responsible for loading the page and waits until the page has finished loading unlike navigate().
*/

    WebDriver driver = null;


    @BeforeMethod
    public void OpenBrowser() {
     System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
     driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    driver.get("https://www.google.com/");      // instead of driver.navigate().to("https://www.google.com/");

    }

    @Test(priority = 1)
    public void test() throws InterruptedException {
        driver.findElement(By.cssSelector("a[href*=\"https://accounts.google.com/ServiceLogin?hl=\"]")).click();
        Thread.sleep(1000);
    // here we should use navigate() method because get() method doesn't provide back, forward, refresh action and this is what's meant by mixing between advantages of get() & navigate()
        driver.navigate().back();
        Thread.sleep(1000);
        driver.navigate().forward();
        Thread.sleep(1000);
        driver.navigate().refresh();
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
