//package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestLoginLogout {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String username = "abcd60";
    private String pwd = "abcd60";
    private String email = "abcd60@example.com";

    public TestLoginLogout() {
        System.setProperty("webdriver.gecko.driver", "C:\\Arpita\\SoftwareTesting\\Sem2\\TWZ_Selenium\\JUNIT\\geckodriver-v0.17.0-win64\\geckodriver.exe");
         System.setProperty("webdriver.chrome.driver", "C:\\Arpita\\SoftwareTesting\\Sem2\\TWZ_Selenium\\JUNIT\\chromedriver_win32\\chromedriver.exe");
    }

    @Before
    public void setUp() throws Exception {
         driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUserCheck() throws Exception {
        driver.get(baseUrl + "/wordpress/wp-login.php?loggedout=true");
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys("arpita");
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys("arpita");
        driver.findElement(By.id("wp-submit")).click();
        System.out.println("Admin Logged in");
       // driver.findElement(By.xpath("//li[@id='menu-users']/a/div[3]")).click();
        Thread.sleep(3000);
        
        
        //"Log Out" can not be clicked directly -->  use Url with action=Log Out. Attention: 2 clicks on "log out"
        //Thread.sleep(1000);
        driver.get(baseUrl + "/wordpress/wp-login.php?action=logout");
// Attention: "log out" must be clicked twice/2 times!!!
        driver.findElement(By.xpath("//a[contains(text(),'log out')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'log out')]")).click();
        Thread.sleep(1000);
        try {
            assertEquals("You are now logged out.", driver.findElement(By.cssSelector("p.message")).getText());
            System.out.println("Log out is successful!");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println(" ");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            System.out.println("Error by logout");
        }
    
       
    }

    @After
    public void tearDown() throws Exception {
        // driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
