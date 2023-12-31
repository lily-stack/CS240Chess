package Responses;

/**
 * represents a response to a LoginRequest and contains variables for an error message, authToken, and username
 */

public class LoginResponse {
    /**
     * string representing an error message
     */
    private String message;
    /**
     * string representing a token for authorization
     */
    private String authorization;
    /**
     * string representing a username
     */
    private String username;

    /**
     * creates an instance of a response for a login request
     */
    public LoginResponse() {}
    // … Getters and Setters for message, authToken, and username properties
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authorization;
    }

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
