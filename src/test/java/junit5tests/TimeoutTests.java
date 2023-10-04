package junit5tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Timeout(value = 5)
public class TimeoutTests {

  WebDriver driver;

  @BeforeEach
  public void setUpTest() {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.get("https://www.lambdatest.com/selenium-playground/jquery-download-progress-bar-demo");
  }

  @AfterEach
  public void tearDownTest() {
    driver.quit();
  }

  @Timeout(1)
  @Test
  public void testDownloadProgress_1() {
    System.out.println(Thread.currentThread().getId());
    System.out.println(Thread.currentThread().getName());

    driver.findElement(By.id("downloadButton")).click();

    String completeMessage = driver.findElement(
            By.xpath("//div[@id='dialog']/div[text()='Complete!']")).getText();

    Assertions.assertEquals("Complete!", completeMessage,
            "\n Expected & Actual Messages Do Not Match \n");
    System.out.println("Test Passed");
  }

  @Test
  @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
  public void testDownloadProgress_2() {
    System.out.println(Thread.currentThread().getId());
    System.out.println(Thread.currentThread().getName());

    driver.findElement(By.id("downloadButton")).click();

    String completeMessage = driver.findElement(
            By.xpath("//div[@id='dialog']/div[text()='Complete!']")).getText();

    Assertions.assertEquals("Complete!", completeMessage,
            "\n Expected & Actual Messages Do Not Match \n");
    System.out.println("Test Passed");
  }

  @Test
  @Timeout(value = 1,
          unit = TimeUnit.MINUTES,
          threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
  public void testTimeoutThreadMode() {
    System.out.println(Thread.currentThread().getId());
    System.out.println(Thread.currentThread().getName());

    driver.findElement(By.id("downloadButton")).click();

    String completeMessage = driver.findElement(
            By.xpath("//div[@id='dialog']/div[text()='Complete!']")).getText();

    Assertions.assertEquals("Complete!", completeMessage,
            "\n Expected & Actual Messages Do Not Match \n");
    System.out.println("Test Passed");
  }

  @Test
  public void testTimeoutAssertion() {
    Assertions.assertTimeout(Duration.ofMillis(3000),
            () -> hardWait(4000),
            "\n Test Failed Due To Timeout \n");
  }

  private void hardWait(int milliseconds) throws InterruptedException {
//    Thread.sleep(milliseconds);
    TimeUnit.MILLISECONDS.sleep(milliseconds);
  }
}
