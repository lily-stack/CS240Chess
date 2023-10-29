package Responses;

import Models.AuthToken;

/**
 * represents a response to a CreateGameRequest and contains variable gameName
 */
public class CreateGameResponse {
    /**
     * string representing the name of a game
     */
    private String gameName;
    /**
     * creates an instance of a response for creating a game that takes a parameter of String name
     */
    public CreateGameResponse(String name){
        gameName = name;
    }
    /**
     * authorizes creating a game
     * @param authToken
     */
    public void Authorization(AuthToken authToken){}

    public String getGameName() {
        return gameName;
    }
}
