package testcases;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;

public class TestCase2 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.yahoo.com/?guccounter=1");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void validateTitles() throws IOException {
       InputStream is = null;
        driver.findElement(By.xpath("//*[@name='agree']")).click();
        driver.findElement(By.xpath(("//*[@class='loader-container']/p/a"))).click();
        String expected_title = "Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos1111";

        try {
            is = new FileInputStream("C:\\Users\\User\\OneDrive\\Bureaublad\\screenshot.png");
            Assert.assertTrue(expected_title.equals(driver.getTitle()));
        } catch (Throwable e){
         //   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //    FileUtils.copyFile(scrFile, new File("C:\\Users\\User\\OneDrive\\Bureaublad\\screenshot.png"));
            Allure.addAttachment("Screenshot",is);
        } finally {
            driver.quit();
        }

    }
}
