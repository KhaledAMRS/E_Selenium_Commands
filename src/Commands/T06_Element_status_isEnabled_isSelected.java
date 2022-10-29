package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class T06_Element_status_isEnabled_isSelected {

    WebDriver driver = null;

    @BeforeMethod
    public void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @Test(priority = 1)
    public void test1() throws InterruptedException {

        WebElement checkbox1 =
                driver.findElement(By.cssSelector("input[type=\"checkbox\"]:nth-child(1)"));

        if(checkbox1.isSelected() == false)
        {
            checkbox1.click();
        }

        WebElement checkbox2 =
                driver.findElement(By.cssSelector("input[type=\"checkbox\"]:nth-child(3)"));

        if(checkbox2.isSelected() == false)
        {
            checkbox2.click();
        }


        checkbox1= driver.findElement(By.cssSelector("form#checkboxes input[type=\"checkbox\"]:nth-child(1)"));
        checkbox2= driver.findElement(By.cssSelector("form#checkboxes input[type=\"checkbox\"]:nth-child(3)"));


        SoftAssert soft = new SoftAssert();
        soft.assertTrue(checkbox1.isSelected());
        soft.assertTrue(checkbox2.isSelected());
        soft.assertAll();
    }


    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
