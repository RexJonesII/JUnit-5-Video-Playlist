package tag_filter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Regression")
public class TagFilterOneTest {

  @Tag("Integration")
  @Test
  public void test_1() {
    System.out.println("Test 1 - Integration");
  }

  @Tag("Integration")
  @Tag("E2E")
  @Test
  public void test_2() {
    System.out.println("Test 2 - Integration & E2E");
  }

  @Tag("E2E")
  @Test
  public void test_3() {
    System.out.println("Test 3 - E2E");
  }

  @Tag("E2E")
  @Tag("API")
  @Test
  public void test_4() {
    System.out.println("Test 4 - E2E & API");
  }

  @Tag("API")
  @Test
  public void test_5() {
    System.out.println("Test 5 - API \n");
  }
}
