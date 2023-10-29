package Responses;

import Models.AuthToken;
import Models.GameModel;

import java.util.Collection;

/**
 * represents a response to a ListGamesRequest
 */
public class ListGamesResponse {
    int status;
    public Collection<GameModel> gameList;
    /**
     * creates an instance of a response for listing games
     */
    public ListGamesResponse(){}
    /**
     * authorizes logging out of game
     * @param authToken
     */
    public void Authorization(AuthToken authToken){}

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
