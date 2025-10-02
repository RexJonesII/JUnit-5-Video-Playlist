package nested;

public class User {
  private String username;
  private String password;
  private boolean loggedIn = false;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public boolean checkPassword(String attempt) {
    return this.password.equals(attempt);
  }

  public void login(String passwordAttempt) {
    if (checkPassword(passwordAttempt)) {
      this.loggedIn = true;
    }
  }

  public void logout() {
    this.loggedIn = false;
  }

  public boolean isLoggedIn() {
    return this.loggedIn;
  }

  public void changePassword(String oldPassword, String newPassword) {
    if (!isLoggedIn()) {
      throw new IllegalStateException("User must be logged in to change password.");
    }
    if (!checkPassword(oldPassword)) {
      throw new IllegalArgumentException("Old password does not match.");
    }
    this.password = newPassword;
  }
}
