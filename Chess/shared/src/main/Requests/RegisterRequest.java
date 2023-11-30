package Requests;

import Models.User;

/**
 * represents a request to register a specific user and has the variables username and password
 */
public class RegisterRequest {
    /**
     * string representing a username
     */
    private String username;
    /**
     * string representing a password
     */
    private String password;
    /**
     * string representing a user's email
     */
    private String email;

    /**
     * creates an instance of a user's request to register
     *
     */
    public RegisterRequest() {}
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
