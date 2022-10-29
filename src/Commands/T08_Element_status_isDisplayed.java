package Commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T08_Element_status_isDisplayed {
/*
	This method is used to check if the Element is displayed on a UI Page. Returns a Boolean value  True/False.

	Note: The element should be existed on DOM Page first and the developer will be able to control whether the element will be visible or Hidden on UI Page

	So what if the element is not exist on DOM Page and tester decided to use isDisplayed()
	if the element is not exist in DOM page >> the code will throw a “No Such Element Exception”
	org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element:

	So if you want to assert that the element should be visible on UI Page then you should use assertTrue with isDisplayed()

	But if you want to assert that the element should not be visible on UI Page then there are two ways depending on the way that developer follows to make this element is not visible on UI Page

	Case1: if the developer choose to hide this element from UI but keep it in DOM Page >> you will use assertFalse with isDisplayed()

	Example on Case1: go to google "https://www.google.com/" before searching for anything, make sure thar auto suggestion section is hidden  (div[jsname="UUbT9"])
	after that type anything in search field like "Selenium" and don't hit Enter then make sure auto suggestion section is visible now on UI Page

	Case2: if the developer choose to hide this element by removing this element from DOM Page >> you should not use isDisplayed() because the code will throw the error "NoSuch Element Exception" So the code wants to tell you that I couldn't find this element in DOM Page to check whether it's visible or hidden on UI Page
	in this case we assert that the element is not exist in DOM page using findElements (not findElement) and we will discuss it later

	Example on Case2: go to http://the-internet.herokuapp.com/add_remove_elements/ and click on Add Element and verify Delete isDisplayed
	then click on Delete and try to use assertFalse with isDisplayed  you will get error in the code, so you need to confirm that element is removed from DOM Page using findElements()

 */


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

    driver.navigate().to("https://www.google.com/");

// Case1: you should use isDisplayed() with assertFalse if the element is existed in DOM Page but hidden from UI
// Case2: you should not use isDisplayed() with assertFalse if the element is not existed in DOM Page
// in this case, the element is exist in DOM but hidden on UI, so we will use Case 1

    Assert.assertFalse(driver.findElement(By.cssSelector("div[jsname=\"UUbT9\"]")).isDisplayed());

    driver.findElement(By.name("q")).sendKeys("Selenium");
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.cssSelector("div[jsname=\"UUbT9\"]")).isDisplayed());

    }

    @Test(priority = 2)
    public void test2() throws InterruptedException {
    driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
    driver.findElement(By.cssSelector("button[onclick=\"addElement()\"]")).click();

    Assert.assertTrue(driver.findElement(By.cssSelector("button[onclick=\"deleteElement()\"]")).isDisplayed());

    driver.findElement(By.cssSelector("button[onclick=\"deleteElement()\"]")).click();
    Thread.sleep(1000);
// Case1: you should use isDisplayed() with assertFalse if the element is existed in DOM Page but hidden from UI
// Case2: you should not use isDisplayed() with assertFalse if the element is not existed in DOM Page
// in this case, the element is removed from DOM Page after click on "Delete" button so we will use Case2
    Assert.assertTrue(driver.findElements(By.cssSelector("button[onclick=\"deleteElement()\"]")).isEmpty());

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
    }

}
