package com.mycompany.mavenprojectseleniumscripts;


import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocatorsWordPressArpita {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public LocatorsWordPressArpita() {
        System.setProperty("webdriver.gecko.driver", "C:\\Arpita\\SoftwareTesting\\Sem2\\TWZ_Selenium\\JUNIT\\geckodriver-v0.17.0-win64\\geckodriver.exe");
    }

    @Before
    public void setUp() throws Exception {

        File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        DesiredCapabilities desired = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
        driver = new FirefoxDriver(options);
//        driver = new FirefoxDriver();
        baseUrl = "http://localhost//";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    // --------------------- LogIn ---------------------------------------------
    public void testCssLoginWordPress() throws Exception {

        driver.get(baseUrl + "/wordpress/wp-login.php");
        driver.findElement(By.cssSelector("#user_login")).clear();
        driver.findElement(By.cssSelector("#user_login")).sendKeys("arpita");    //Username
        driver.findElement(By.cssSelector("#user_pass")).clear();
        driver.findElement(By.cssSelector("#user_pass")).sendKeys("arpita");      //Passwort
        driver.findElement(By.cssSelector("#wp-submit")).click();
        Thread.sleep(2000);
        try {
            assertEquals("arpita", driver.findElement(By.cssSelector("span.display-name")).getText());
        } catch (Error e) {
            System.out.println("Login nicht erfolgreich, Username kann nicht gefunden werden!");
            verificationErrors.append(e.toString());
        }

//---------------- Add New Page 1 --------------------------------------        
        driver.findElement(By.xpath("//li[@id='menu-pages']/a/div[3]")).click();    // Click auf Page
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a.page-title-action")).click();          // Click auf Button AddNew
        Thread.sleep(1000);
        
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
        
        
//        driver.findElement(By.id("title")).sendKeys("MeineSeite1");                 //Titel
//        driver.findElement(By.id("content")).sendKeys("MeinSeitentext1");           //Text
//        driver.findElement(By.id("publish")).click();                               //veröffentlichen
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Erstellen der Seite 1");
                fail("timeout");
            }
            try {
                if ("Page published. View page".equals(driver.findElement(By.id("message")).getText())) {
                    System.out.println("Seite 1 wurde erstellt");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
// ------------------ Add Page 2 -----------------------------------------------       
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a.page-title-action")).click();          // Click auf Button AddNew
        Thread.sleep(1000);
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("MeineSeite2");                 //Titel
        driver.findElement(By.id("content")).clear();
        driver.findElement(By.id("content")).sendKeys("MeinSeitentext2");           //Text
        driver.findElement(By.id("publish")).click();                               //veröffentlichen
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Erstellen der Seite 2");
                fail("timeout");
            }
            try {
                if ("Page published. View page".equals(driver.findElement(By.id("message")).getText())) {
                    System.out.println("Seite 2 wurde erstellt");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
//----------------- Edit Page -----------------------------------------
        Thread.sleep(1000);
        driver.findElement(By.linkText("All Pages")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'MeineSeite1')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("MeineNeueSeite1");
        driver.findElement(By.id("content")).clear();
        driver.findElement(By.id("content")).sendKeys("MeinNeuerSeitentext1");
        driver.findElement(By.id("publish")).click();
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Bearbeiten der Seite 1");
                fail("timeout");
            }
            try {
                if ("Page updated. View page".equals(driver.findElement(By.id("message")).getText())) {
                    System.out.println("Seite 1 wurde geändert");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
// ------------------ add new tag ---------------------------------------
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@id='menu-posts']/a/div[3]")).click();
        driver.findElement(By.linkText("Tags")).click();
        driver.findElement(By.cssSelector("#tag-name")).sendKeys("MeinTagTitel");
        driver.findElement(By.cssSelector("#tag-slug")).sendKeys("MeinSlugTitel");
        driver.findElement(By.cssSelector("#tag-description")).sendKeys("MeineTagTitelBeschreibung");
        driver.findElement(By.cssSelector("#submit")).click();
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Erstellen des Tags");
                fail("timeout");
            }
            try {
                if ("MeinTagTitel".equals(driver.findElement(By.cssSelector("a.row-title")).getText())) {
                    System.out.println("Der Tag wurde gefunden");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
//-------------- add new category ----------------------------------------
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@id='menu-posts']/a/div[3]")).click();
        driver.findElement(By.linkText("Categories")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#tag-name")).sendKeys("MeinKategorieTitel");
        driver.findElement(By.cssSelector("#submit")).click();
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Erstellen der Kategorie");
                fail("timeout");
            }
            try {
                if ("MeinKategorieTitel".equals(driver.findElement(By.cssSelector("a.row-title")).getText())) {
                    System.out.println("Die Kategorie wurde erstellt");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
//------------------- Add new Post --------------------------------------
        Thread.sleep(1000);
        driver.findElement(By.linkText("Add New")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#title")).sendKeys("MeinPostTitel");
        driver.findElement(By.cssSelector("#content")).sendKeys("MeinPostText");
        driver.findElement(By.cssSelector("#new-tag-post_tag")).sendKeys("MeinTagTitel");
        driver.findElement(By.cssSelector("input.button.tagadd")).click();
//!!!!!!!!!xPath-Locator + Text-Kombination!!!!!!!!!!!!!!!!!!!!!!!!        
        driver.findElement(By.xpath("//label[@class='selectit' and contains(text(), 'MeinKategorieTitel')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#publish")).click();
        for (int second = 0;; second++) {
            if (second >= 10) {
                System.out.println("Fehler beim Erstellen des Posts");
                fail("timeout");
            }
            try {
                if ("Post published. View post".equals(driver.findElement(By.cssSelector("#message > p")).getText())) {
                    System.out.println("Der Post wurde erstellt");
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
//--------------- Search and Verify Page ----------------------------
        Thread.sleep(1000);
        String seitenTITELsuche = "MeineSeite2";
        String seitenTEXTsuche = "MeinSeitentext2";
//        Variablen zum Testen für nicht vorhandenen Titel und Text        
//        String seitenTITELsuche = "falscheSeite";
//        String seitenTEXTsuche = "falscherText";
        driver.get(baseUrl + "/wordpress");
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(seitenTITELsuche);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        // Seitentitel suchen
//        int fehler = 1;         // 0, wenn Seitentext gefunden
        try {
            if (seitenTITELsuche.equals(driver.findElement(By.xpath("//h2//a")).getText())) {       //Seitentitel vergleichen
                System.out.println("Der gesuchte Seitentitel wurde gefunden");
                driver.findElement(By.xpath("//h2//a[text()='MeineSeite2']")).click();              //wenn vorhanden öffnen
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Eine Seite mit diesem Titel wurde nicht gefunden");
        }
        //Seitentext suchen
        try {
            if (seitenTEXTsuche.equals(driver.findElement(By.xpath("//div[@class='entry-content']//p")).getText())) {      //Seitentext vergleichen
                System.out.println("Die gesuchte SeitenTEXT wurde auch gefunden");
//                fehler = 0;                                   // Seitentext gefunden
            }
        } catch (Exception e) {
                   System.out.println("Der gesuchte SeitenTEXT wurde NICHT gefunden");
        }
       
//        if (fehler == 1) {
//            System.out.println("Der gesuchte SeitenTEXT wurde NICHT gefunden");
//        }

// ------------------ LogOut --------------------------------------------- 
// Logout kann nicht direkt geklickt werden, Alternative via Link und !!! 2 x klicken auf log out
        Thread.sleep(1000);
        driver.get(baseUrl + "/wordpress/wp-login.php?action=logout");
// log out muss 2 x geklickt werden!!!
        driver.findElement(By.xpath("//a[contains(text(),'log out')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'log out')]")).click();
        Thread.sleep(1000);
        try {
            assertEquals("You are now logged out.", driver.findElement(By.cssSelector("p.message")).getText());
            System.out.println("Log Out erfolgreich");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            System.out.println("Fehler beim Log Out");
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
