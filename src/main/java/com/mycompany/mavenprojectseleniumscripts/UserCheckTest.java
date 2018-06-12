package com.mycompany.mavenprojectseleniumscripts;

//package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UserCheckTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String username = "abcd76";
    private String pwd = "abcd76";
    private String email = "abcd76@example.com";

    public UserCheckTest() {
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
        driver.findElement(By.xpath("//li[@id='menu-users']/a/div[3]")).click();
        Thread.sleep(3000);
        driver.get("http://localhost/wordpress/wp-admin/users.php");
        driver.findElement(By.cssSelector("a.page-title-action")).click();
        //  driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("first_name")).clear();
        driver.findElement(By.id("first_name")).sendKeys("Abcd");
        driver.findElement(By.id("last_name")).clear();
        driver.findElement(By.id("last_name")).sendKeys("Abcd");
        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
        driver.findElement(By.id("pass1-text")).clear();
        driver.findElement(By.id("pass1-text")).sendKeys(pwd);
        driver.findElement(By.name("pw_weak")).click();
        driver.findElement(By.id("send_user_notification")).click();
        new Select(driver.findElement(By.id("role"))).selectByVisibleText("Contributor");
        driver.findElement(By.id("createusersub")).click();
        System.out.println("User Created");

        Thread.sleep(5000);
        try {
            assertEquals(driver.findElement(By.id("message")).getText(), "New user created. Edit user\nDismiss this notice.");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        //driver.findElement(By.cssSelector("span.display-name")).click();
        //driver.findElement(By.xpath("//img[contains(@src,'http://1.gravatar.com/avatar/ddbd6aee25148ef1d15296c530387d4b?s=26&d=mm&r=g')]")).click();
        Thread.sleep(3000);
        // driver.findElement(By.cssSelector("#wp-admin-bar-logout > a:nth-child(1)")).click();   //funktioniert nicht
        //driver.findElement(By.linkText("Log Out")).click();
        driver.get("http://localhost/wordpress/wp-login.php?loggedout=true");
        System.out.println("Admin Logged out");
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys(pwd);
        driver.findElement(By.id("wp-submit")).click();
        Thread.sleep(3000);
        System.out.println("Contributor Logged in");
        Thread.sleep(3000);
        driver.get(baseUrl + "/wordpress/wp-admin/users.php");
        Thread.sleep(4000);
        try {
            assertEquals("You need a higher level of permission.", driver.findElement(By.cssSelector("h1")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
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
