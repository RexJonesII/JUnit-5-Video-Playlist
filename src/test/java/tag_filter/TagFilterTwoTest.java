package tag_filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Smoke")
@Tag("Regression")
@DisplayName("Class: Smoke & Regression")
public class TagFilterTwoTest {

  @Test
  @Tag(value = "Performance")
  @DisplayName("Test 1 - Performance")
  public void test_1() {
    System.out.println("Test 1 - Performance");
  }

  @Test
  @Tag(value = "Performance")
  @Tag(value = "Stress")
  @DisplayName("Test 2 - Performance & Stress")
  public void test_2() {
    System.out.println("Test 2 - Performance & Stress");
  }

  @Test
  @Tag(value = "Stress")
  @DisplayName("Test 3 - Stress")
  public void test_3() {
    System.out.println("Test 3 - Stress");
  }
}
