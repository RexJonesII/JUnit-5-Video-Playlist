package junit4tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnit4Tests {

  WebDriver driver;

  @BeforeClass
  public static void beforeClass() {
    System.out.println("Execute Before Each Class");
  }

  @Before
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
    Assert.assertEquals
            ("Actual & Expected Messages Do Not Match \n",
                    expectedMessage, actualMessage);
  }

  @Test
  public void executeJUnitTestMethod() {
    System.out.println("2nd Test Method");
  }

  @After
  public void afterMethod() {
    System.out.println("Execute After Each Test Method \n");
    driver.quit();
  }

  @AfterClass
  public static void afterClass() {
    System.out.println("Execute After Each Class");
  }
}
