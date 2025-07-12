package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Dashboard {
    WebDriver driver;
    WebDriverWait wait;
    LoginN login;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//        login = new LoginN(driver);
//        login.performLogin("standard_user", "secret_sauce");
//        driver.findElement(By.name("login-button")).click();

    }

    @Test
    public void lt() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class = 'inventory_item']")));
        String bag = firstItem.getText();
        firstItem.isDisplayed();
        firstItem.click();

        WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class = 'inventory_item_img']")));
         if(img.isDisplayed()){
             System.out.println("Img is shown");
         }

         WebElement itemname = wait.until(ExpectedConditions.visibilityOfElementLocated
                 (By.xpath("//div[normalize-space()='Sauce Labs Backpack']")));
          String itemName = itemname.getText();
          String aitemName = "Sauce Labs Backpack";
           if(itemName == aitemName){
               System.out.println("Item Name is correctly displayed");
           }

           WebElement addcart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
           addcart.isEnabled();
           addcart.click();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }
    @Test
    public void addcart(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        WebElement item = driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bolt T-Shirt']"));

        String expecteditem = item.getText();
        String actualitem = "Sauce Labs Bolt T-Shirt";
        if(expecteditem.contentEquals(actualitem)){
            System.out.println("Test Passed");
            WebElement addtocart = driver.findElement(By.xpath("//button[@id = 'add-to-cart-sauce-labs-bolt-t-shirt'] "));
            addtocart.click();
        } else {
            System.out.print("Test Failed");
        }

    }
    @Test
    public void detailpage(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

         WebElement itemclick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space() = 'Sauce Labs Fleece Jacket']")));
        itemclick.click();

        WebElement detailitem = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']"));
        String expecteditemn = detailitem.getText();
        System.out.print(expecteditemn);
        String actualitem = "Sauce Labs Fleece Jacket";
        if(actualitem.equals(expecteditemn)){
            System.out.println("Test Passed");
            WebElement addtocarts = driver.findElement(By.xpath("//button[@id ='add-to-cart'] "));
            addtocarts.click();
        } else {
            System.out.print("Test Failed");
        }

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
