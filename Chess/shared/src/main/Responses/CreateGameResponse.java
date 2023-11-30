package Responses;

public class CreateGameResponse {
    private String gameName;
    private String authorization;
    public CreateGameResponse(){}

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }

    public String getAuthToken() {
        return authorization;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
