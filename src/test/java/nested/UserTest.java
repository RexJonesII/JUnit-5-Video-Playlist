package nested;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  private User user;

  @BeforeEach
  public void createUser() {
    System.out.println("Outer @BeforeEach: Create User Object");
    user = new User("LambdaTestUser", "initialPassword");
  }

  @AfterEach
  public void cleanUpUser() {
    System.out.println("Outer @AfterEach: Clean Up User Object");
    user = null;
  }

  @Test
  @DisplayName("user is created successfully")
  void isCreated() {
    assertNotNull(user);
    assertFalse(user.isLoggedIn(), "A new user should not be logged in");
  }

  @Nested
  @DisplayName("when newly created")
  class WhenNew {

    @Test
    @DisplayName("can log in with the correct password")
    void canLogIn() {
      user.login("initialPassword");
      assertTrue(user.isLoggedIn());
    }

    @Test
    @DisplayName("cannot log in with an incorrect password")
    void cannotLogInWithWrongPassword() {
      user.login("wrongPassword");
      assertFalse(user.isLoggedIn());
    }

    @Test
    @DisplayName("cannot change password")
    void cannotChangePassword() {
      assertThrows(IllegalStateException.class, () -> {
        user.changePassword("initialPassword", "newPassword");
      });
    }
  }

  @Nested
  @DisplayName("when logged in")
  class WhenLoggedIn {

    @BeforeEach
    void loginUser() {
      System.out.println("  Inner @BeforeEach: Log In User");
      user.login("initialPassword");
    }

    @AfterEach
    void logoutUser() {
      System.out.println("  Inner @AfterEach: Logging user out");
      if (user != null && user.isLoggedIn()) {
        user.logout();
      }
    }

    @Test
    @DisplayName("can be logged out")
    void canLogOut() {
      assertTrue(user.isLoggedIn());
      user.logout();
      assertFalse(user.isLoggedIn());
    }

    @Test
    @DisplayName("can change password with correct old password")
    void canChangePassword() {
      user.changePassword("initialPassword", "newPassword");
      user.logout();
      user.login("newPassword");
      assertTrue(user.isLoggedIn());
    }

    @Test
    @DisplayName("cannot change password with incorrect old password")
    void cannotChangePasswordWithWrongOldPassword() {
      assertThrows(IllegalArgumentException.class, () -> {
        user.changePassword("incorrectPassword", "newPassword");
      });
    }
  }
}
