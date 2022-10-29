package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class T09_findElements {
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
    driver.navigate().to("http://the-internet.herokuapp.com/checkboxes");
    List<WebElement> inputWebElements = driver.findElements(By.cssSelector("input[type=\"checkbox\"]"));
    int optionCount = inputWebElements.size();
    System.out.println(optionCount);

    System.out.println(inputWebElements.isEmpty()); // this should be false because optionCount is bigger than zero

    // click on checkbox 1
    inputWebElements.get(0).click();

    // click on checkbox 2
    inputWebElements.get(1).click();
    }

    // another example
    @Test(priority = 2)
    public void test2() throws InterruptedException {
    driver.get("http://the-internet.herokuapp.com/login");

    By inputFields = By.cssSelector("div[class=\"large-6 small-12 columns\"] > input[type]");
    List<WebElement> inputFieldsEles = driver.findElements(inputFields);

      // Count the number of input fields
     System.out.println(inputFieldsEles.size());

     // first element which is username field
     inputFieldsEles.get(0).sendKeys("tomsmith");

    // second element which is password field
     inputFieldsEles.get(1).sendKeys("SuperSecretPassword!");

    }



    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
