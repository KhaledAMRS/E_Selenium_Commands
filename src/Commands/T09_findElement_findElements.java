package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class T09_findElement_findElements {
    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/add_remove_elements/");

    }

    @Test
    public void AddElement()
    {
int clickCount=5;

        for (int x=0;x<clickCount;x++) {
            driver.findElement(By.cssSelector("button[onclick=\"addElement()\"]")).click();
        }

 int size = driver.findElements(By.cssSelector("button[onclick=\"deleteElement()\"]")).size();
 Assert.assertEquals(size,clickCount);

        List<WebElement> ele = driver.findElements(By.cssSelector("button[onclick=\\\"deleteElement()\\\"]"));
//        WebElement delete = driver.findElements(By.cssSelector(""));
Assert.assertTrue(driver.findElements(By.cssSelector("button[onclick=\"deleteElement()\"]")).isEmpty());


    }

    @AfterMethod
    public void QuitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
