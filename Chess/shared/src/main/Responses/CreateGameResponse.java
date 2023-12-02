package Responses;

public class CreateGameResponse {
    private String gameName;
    private String authorization;
    private int gameId;
    public CreateGameResponse(){}

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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
