package junit5tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonDemoTests {

  WebDriver driver;

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Execute One Time Before All Test Methods");
  }

  @BeforeEach
  public void beforeEach() {
    System.out.println("Execute Before Each Test Method");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
  }

  @Test
  public void testSelectedRadioButtonMessage() {
    System.out.println("1st Test Method");
    driver.findElement(
            By.xpath("//input[@value='Female' and @name='optradio']")).click();
    driver.findElement(By.id("buttoncheck")).click();
    String actualMessage = driver.findElement(
            By.xpath("//button[@id='buttoncheck']/following-sibling::p")).getText();
    String expectedMessage = "Radio Button 'Female' Is Checked";
    Assertions.assertEquals(expectedMessage, actualMessage,
            "\n Actual & Expected Messages Do Not Match");
  }

  @Test
  public void testDisabledRadioButton() {
    System.out.println("2nd Method");
    String actualLabel = driver.findElement(
      By.xpath("//label[text()='Disabled Radio Button']")).getText();
    assertTrue(actualLabel.contains("Disabled"),
            "\n Actual Label Does Not Contain Disabled");
  }

  @AfterEach
  public void afterEach() {
    System.out.println("Execute After Each Test Method \n");
    driver.quit();
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("Execute One Time After All Test Methods");
  }
}
