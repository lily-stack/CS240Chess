package Services;

/**
 * represents a response to a JoinGameRequest and contains variables for an error message, authToken, and username
 */

public class JoinGameResponse {
    private String message;
    private String authToken;
    private String username;

    /**
     *creates an instance of a response for joining a game
     */
    public JoinGameResponse() {}
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
}
