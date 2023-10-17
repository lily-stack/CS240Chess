package Services;

import Models.AuthToken;

/**
 * represents a response to a ListGamesRequest
 */
public class ListGamesResponse {
    /**
     * creates an instance of a response for listing games
     */
    public ListGamesResponse(){}
    /**
     * authorizes logging out of game
     * @param authToken
     */
    public void Authorization(AuthToken authToken){}
}
