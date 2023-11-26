package Responses;

import Models.GameModel;

import java.util.Collection;

/**
 * represents a response to a ListGamesRequest
 */
public class ListGamesResponse {
    int status;
    public Collection<GameModel> games;
    String message;
    /**
     * creates an instance of a response for listing games
     */
    public ListGamesResponse(){}

    public void setGames(Collection<GameModel> games) {
        this.games = games;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
