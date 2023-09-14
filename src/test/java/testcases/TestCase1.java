package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase1 {
    WebDriver driver;

    @BeforeMethod
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.get("https://admin-demo.nopcommerce.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();

    }
    @Test(priority = 2,dependsOnMethods = "doLogin")
    public void doUserReg(){
        String actual = driver.findElement(By.xpath("//*[@class='page-title']/h1")).getText();
        String expected = "Admin area demo";
        Assert.assertTrue(expected.equals(actual));
    }

    @Test(priority = 1)
    public void doLogin(){
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Password")).clear();

        driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.id("Password")).sendKeys("admin");

        driver.findElement(By.cssSelector(".login-button")).click();
        String expected = "Dashboard";
        String actual = driver.findElement(By.xpath("//*[@class='content-header']")).getText();
        Assert.assertEquals(actual,expected);

    }
}
