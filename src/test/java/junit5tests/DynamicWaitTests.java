package junit5tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicWaitTests {
  WebDriver driver;

  @BeforeEach
  public void setUpTest() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-download-progress-demo");
  }

  @AfterEach
  public void tearDownTest() {
    driver.quit();
  }

  @Test
  public void explicitWaitTest() {
    driver.findElement(By.id("dwnBtn")).click();
    By complete100Percent = By.xpath("//div[@id='__next']//p[text()='100%']");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.textToBe(complete100Percent, "100%"));

    String expectedMessage = "100%";
    String actualMessage = driver.findElement(complete100Percent).getText();
    Assertions.assertEquals(expectedMessage, actualMessage,
            "\n Expected & Actual Messages Do Not Match \n");
  }

  @Test
  public void fluentWaitTest() {
    driver.findElement(By.id("dwnBtn")).click();
    By complete100Percent = By.xpath("//div[@id='__next']//p[text()='100%']");

    FluentWait wait = new FluentWait(driver)
      .withTimeout(Duration.ofSeconds(10)) // 10 Seconds For The Element To Be Present
      .pollingEvery(Duration.ofMillis(1000)) // Poll Every 1,000 MilliSeconds
      .ignoring(NoSuchElementException.class) // Ignore 1 Or More Exceptions
      .ignoring(ElementClickInterceptedException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(complete100Percent));

    String expectedMessage = "100";
    String actualMessage = driver.findElement(complete100Percent).getText();
    Assertions.assertEquals(expectedMessage, actualMessage,
            "\n Expected & Actual Messages Do Not Match \n");
  }
}
