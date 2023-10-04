package junit4tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConvertJUnit4ToJUnit5Tests_2 {

  WebDriver driver;

  @BeforeAll
  public static void beforeClass() {
    System.out.println("Execute Before Each Class");
  }

  @BeforeEach
  public void beforeMethod() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    System.out.println("Execute Before Each Test Method");
  }

  @Test
  public void testSimpleFormDemo() {
    System.out.println("1st Test Method");
    driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
    driver.findElement(By.xpath("//input[@id='user-message']"))
      .sendKeys("JUnit 5 Is An Awesome Test Framework");
    driver.findElement(By.id("showInput")).click();
    String actualMessage = driver.findElement(By.id("message")).getText();
    String expectedMessage = "JUnit 5 Is An Awesome Test Framework";
    Assertions.assertEquals
            (expectedMessage, actualMessage,
                    "Actual & Expected Messages Do Not Match \n");
  }

  @Test
  public void executeJUnitTestMethod() {
    System.out.println("2nd Test Method");
  }

  @AfterEach
  public void afterMethod() {
    System.out.println("Execute After Each Test Method \n");
    driver.quit();
  }

  @AfterAll
  public static void afterClass() {
    System.out.println("Execute After Each Class");
  }
}
