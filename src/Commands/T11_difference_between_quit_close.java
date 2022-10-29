package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T11_difference_between_quit_close {
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
   // this will open new tab in the browser
    driver.findElement(By.cssSelector("a[href=\"http://elementalselenium.com/\"]")).click();
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
//  If we use driver.quit() while the two tabs are opening >> all tabs will be closed and the whole WebDriver session will be terminated
//  but for driver.close() it only closes the current tab and doesn't terminate the whole WebDriver session (you should close it from endTask Manager in this case)
//
/*
    Thread.sleep(2000);
    driver.close();
*/

/*
    Thread.sleep(2000);
    driver.quit();
*/
    }

}
