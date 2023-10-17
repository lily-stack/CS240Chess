package Services;

import Models.User;

/**
 * Represents a request from a user to login to the game and contains the variables username and password
 */
public class LoginRequest {
    private String username;
    private String password;

    /**
     * creates an instance of a login request for a specific user
     */
    public LoginRequest(User user) {}
    // … Getters and Setters for username and password properties

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}