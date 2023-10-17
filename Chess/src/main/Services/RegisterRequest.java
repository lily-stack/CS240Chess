package Services;

import Models.User;

/**
 * represents a request to register a specific user and has the variables username and password
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    /**
     * creates an instance of a user's request to register
     * @param user of type User
     */
    public RegisterRequest(User user) {}
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
