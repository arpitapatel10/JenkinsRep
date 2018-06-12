package com.mycompany.mavenprojectseleniumscripts;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CreatePageTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public CreatePageTest() {
        System.setProperty("webdriver.gecko.driver", "C:\\Arpita\\SoftwareTesting\\Sem2\\TWZ_Selenium\\JUNIT\\geckodriver-v0.17.0-win64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Arpita\\SoftwareTesting\\Sem2\\TWZ_Selenium\\JUNIT\\chromedriver_win32\\chromedriver.exe");
    }

    @Before
    public void setUp() throws Exception {

        File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        DesiredCapabilities desired = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
        driver = new ChromeDriver();

        // driver = new FirefoxDriver();
        baseUrl = "https://s1.demo.opensourcecms.com/";
        //driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
    }

    @Test
    public void test02CreatePageWithVariable() throws Exception {

        //------------------------------------------Login---------------------------------------
        driver.get(baseUrl + "/wordpress/wp-login.php");
        driver.findElement(By.xpath("//*[@id=\"user_login\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"user_login\"]")).sendKeys("opensourcecms");
        driver.findElement(By.xpath("//*[@id=\"user_pass\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"user_pass\"]")).sendKeys("opensourcecms");
        driver.findElement(By.xpath("//*[@id=\"rememberme\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"wp-submit\"]")).click();

        //driver.get(baseUrl + "/wordpress/wp-admin/");
        //Thread.sleep(2000);
        System.out.println("Click Login");
        // Wait for Dashboard
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if ("Dashboard ‹ My CMS — WordPress".equals(driver.getTitle())) {
                    System.out.println("Login succesfull n waiting for DashboardLoad");
                    break;
                }
            } catch (Exception e) {
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreatePageTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Dashboard-- Click on Pages");

 /*       // Click on "Pages"
        driver.findElement(By.xpath("//li[@id='menu-pages']/a/div[3]")).click();

        // Wait for Title "Pages"
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if ("Pages ‹ My CMS — WordPress".equals(driver.getTitle())) {
                    System.out.println("Pages-->opened");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }

        //driver.findElement(By.cssSelector("#menu-pages > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1)")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'post-new.php?post_type=page')]")).click();

        //--------------------------------New Page Load------------------------------------------
        // Wait for Title "Add New Page"
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if ("Add New Page ‹ My CMS — WordPress".equals(driver.getTitle())) {
                    System.out.println("\"New Page\" opened");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }

        String varTitle = "NewTestPage30";
        String varContent = "hiiii New Page";
        System.out.println("Before Entering Title");
        // driver.findElement(By.cssSelector("#title")).clear();
        // driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys(varTitle);
        driver.findElement(By.cssSelector("#content-html")).click();
        //  driver.findElement(By.id("content")).clear();
        driver.findElement(By.id("content")).sendKeys(varContent);
        System.out.println("Text in Titel and Content added");

        driver.findElement(By.id("publish")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
        for (int second = 0;; second++) {
            if (second >= 600) {
                fail("timeout");
            }
            try {
                if ("Pages".equals(driver.findElement(By.cssSelector("a.wp-has-current-submenu > div:nth-child(3)")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        //-----------------------------Edit Page----------------------------------------------
        driver.findElement(By.cssSelector("a.wp-has-current-submenu > div:nth-child(3)")).click();
        Thread.sleep(4000);
        driver.findElement(By.linkText("NewTestPage30")).click();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("#content-html")).click();
        // Thread.sleep(4000);
        varContent = "nur New Page";
        driver.findElement(By.cssSelector("#content")).clear();
        driver.findElement(By.cssSelector("#content")).sendKeys(varContent);
        driver.findElement(By.cssSelector("#publish")).click();
        System.out.println("Text in Content changed");                          

        //--------------------------------- New Post-------------------------------
        driver.findElement(By.cssSelector("#wp-admin-bar-new-content > a.ab-item > span.ab-label")).click();
        // Wait for Window Title "Add new Post"
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if ("Add New Post ‹ My CMS — WordPress".equals(driver.getTitle())) {
                    System.out.println("\"New Post\" opened");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }

        //driver.get(baseUrl + "/wordpress/wp-admin/index.php");
        driver.findElement(By.linkText("Add New")).click();
        String varPostTitle = "NewPost30";
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys(varPostTitle);
        System.out.println("Post Title added");

        driver.findElement(By.cssSelector("#content-html")).click();
        String varPostContent = "Hello New Post";
        // driver.findElement(By.cssSelector("#content")).clear();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#content")).sendKeys(varPostContent);
        System.out.println("Post Content added");
        driver.findElement(By.cssSelector("#publish")).click();
        System.out.println("Publish clicked");

        //---------------------------------Add Category-----------------------------------------------
        // Wait for Window Title "Add new Category"    
        driver.findElement(By.id("category-add-toggle")).click();
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if (isElementPresent(By.id("newcategory"))) {
                    System.out.println("\"New Category\" opened");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(2000);
        }

        //driver.findElement(By.id("newcategory")).clear();
        driver.findElement(By.id("newcategory")).sendKeys("NewCat31");
        driver.findElement(By.id("category-add-submit")).click();

//        driver.findElement(By.cssSelector("#newcategory")).sendKeys("NewCat31");
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("#category-add-submit")).click();
        System.out.println("New Categorie added in Post");

        driver.findElement(By.cssSelector("#new-tag-post_tag")).clear();
        driver.findElement(By.cssSelector("#new-tag-post_tag")).sendKeys("TWZ");
        driver.findElement(By.cssSelector("input.button.tagadd")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#publish")).click();
        Thread.sleep(2000);
        System.out.println("New Tag added in Post");                               */

        //------------------------------------- Check Page------------------------------------- 
        // driver.findElement(By.linkText("My CMS")).click();
        driver.get("https://s1.demo.opensourcecms.com/wordpress/");
        driver.findElement(By.cssSelector("#adminbar-search")).clear();
        driver.findElement(By.cssSelector("#adminbar-search")).sendKeys("NewTestPage30");

        driver.findElement(By.cssSelector("#adminbar-search")).click();
        driver.findElement(By.cssSelector("a.menu-scroll-down")).click();
        Thread.sleep(1000);
        System.out.println("Page searching");

        driver.findElement(By.cssSelector(".search-field")).sendKeys("NewTestPage30");
        driver.findElement(By.cssSelector(".search-submit")).click();
        Thread.sleep(2000);
        try {
            assertEquals("NewTestPage30", driver.findElement(By.linkText("NewTestPage30")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        assertEquals("hiiii New Page", driver.findElement(By.xpath("//article/div/p")).getText());

        //driver.findElement(By.linkText("NewTestPage30")).click();
        System.out.println("NewTestPage30 searched");
//        try {
//            assertEquals("NewTestPage30", driver.findElement(By.cssSelector("h1.entry-title")).getText());
//            System.out.println("NewTestPage30 checked");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        try {
//            assertEquals("hiiii New Page", driver.findElement(By.cssSelector("div.entry-content > p")).getText());
//            System.out.println("NewTestPage30 Content checked");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }

        //--------------------------------Check Post----------------------------------------
//        driver.findElement(By.cssSelector("#adminbar-search")).clear();
//        driver.findElement(By.cssSelector("#adminbar-search")).sendKeys("NewPost30");
//        driver.findElement(By.cssSelector("input.adminbar-button")).click();

        driver.findElement(By.cssSelector(".search-field")).clear();
        driver.findElement(By.cssSelector(".search-field")).sendKeys("NewPost30");
        driver.findElement(By.cssSelector("button.search-submit")).click();
        Thread.sleep(5000);

        try {
            assertEquals("Hello New Post", driver.findElement(By.xpath("//article/div/p")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.cssSelector("span.display-name")).click();

        //driver.findElement(By.linkText("NewPost30")).click();
        System.out.println("NewPost30 searched");
        try {
            assertEquals("NewPost30", driver.findElement(By.cssSelector("h1.entry-title")).getText());
            System.out.println("NewPost30 checked");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Hello New Post", driver.findElement(By.cssSelector("div.entry-content > p")).getText());
            System.out.println("NewPost30 content checked");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
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
