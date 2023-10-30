package Responses;

/**
 * represents a response to a RegisterRequest and contains variables for an error message, authToken, and username
 */
public class RegisterResponse {
    /**
     * string representing an error message
     */
    private String message;
    /**
     * string representing a token for authentication
     */
    public String authToken;
    /**
     * string representing a username
     */
    private String username;

    private String password;
    private String email;
    public int status;

    /**
     * creates an instance of a response for a register request
     */
    public RegisterResponse() {}
    // … Getters and Setters for message, authToken, and username properties
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
