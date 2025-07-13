package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class Login_Pages {

    WebDriver driver;
    WebDriverWait wait;

    public Login_Pages(WebDriver driver) {    //setting up POM
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public WebElement performLogin(String user, String pass) {
        WebElement usernames = driver.findElement(By.xpath("//input[@id='user-name']"));

        if (usernames.isDisplayed() && usernames.isEnabled()) {
            System.out.println("It is displayed & clickable");
            usernames.sendKeys(user);
        }
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.name("login-button")).click();
        return usernames;

    }

}


