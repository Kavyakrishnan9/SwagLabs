package SwagLabs;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginN {
    WebDriver driver;
    WebDriverWait wait;

    public void performLogin(String user, String pass) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }
    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        // Disable password manager and automation info bar
        options.addArguments("--disable-infobars");
        options.addArguments("--incognito"); // optional, but helps prevent saved logins
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // Disable Chrome's password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void validLoginTest() {
        performLogin("standard_user", "secret_sauce");
        driver.findElement(By.name("login-button")).click();
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert alert= driver.switchTo().alert();
//               System.out.println(alert.getText());
//                alert.accept();

        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class = 'title']")));
        assertTrue(dashboard.isDisplayed());
    }

    @Test
    public void invalidLoginTest() {
        performLogin("locked_out_user", "secret_sauce");
        driver.findElement(By.name("login-button")).click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h3[@data-test='error']")));
        //WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Products")));
        assertTrue(error.isDisplayed());
    }

    @Test
    public void wrongcredentials() {
        performLogin("stand_user", "secret_sauce");
        driver.findElement(By.name("login-button")).click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h3[@data-test='error']")));
        //WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Products")));
        assertTrue(error.isDisplayed());
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
