package Services;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import Models.AuthToken;
import Models.GameModel;
import Requests.JoinGameRequest;
import Responses.JoinGameResponse;
import dataAccess.DataAccessException;

import java.util.Objects;

/**
 * implements the logic for an HTTP PUT method for joining a game
 */
public class JoinGameService {
    private AuthDao authDao = new AuthDao();
    private GameDao gameDao = new GameDao();
    /**
     * Receives a JoinGameRequest object and returns a corresponding JoinGamerResponse object
     *
     * @param gameID of type int
     * @param authToken of type String
     * @param color of type String
     * @return int
     */
    public int join(int gameID, String authToken, String color) throws DataAccessException {
        GameModel game;
        //check if gameID is in list of games
        try{
            game = gameDao.Find(gameID);
            if (game == null){
                return 400;
            }
        }
        catch(DataAccessException e){
            return 400;
        }
        String username = authDao.Find(authToken);
        //check if user is authorized
        if(username == null){
            return 401;
        }
        if(Objects.equals(color, "WHITE")){
            if(game.whiteUsername != null) {
                return 403;
            }
            else{
                game.setWhiteUsername(username);
            }
        }
        else if(Objects.equals(color, "BLACK")){
            if(game.blackUsername != null) {
                return 403;
            }
            else{
                game.setBlackUsername(username);
            }
        }
        return 200;
    }
}
