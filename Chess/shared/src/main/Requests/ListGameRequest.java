package Requests;

public class ListGameRequest {
    public String authorization;

    public ListGameRequest(){}
    public String getAuthToken() {
        return authorization;
    }

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }
}
