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
    public JoinGameResponse join(int gameID, String authToken, String color) throws DataAccessException {
        GameModel game;
        JoinGameResponse response = new JoinGameResponse();
        //check if gameID is in list of games
        try {
            game = gameDao.Find(gameID);
        } catch(DataAccessException e){
            response.setMessage("Error: bad request");
            response.status = 400;
            return response;
        }
        if (game == null) {
            response.setMessage("Error: bad request");
            response.status = 400;
            return response;
        }
        String username = authDao.Find(authToken);
        //check if user is authorized
        if(username == null){
            response.status = 401;
            return response;
        }
        if(Objects.equals(color, "WHITE")){
            if(game.whiteUsername != null) {
                response.setMessage("Error: already taken");
                response.status = 403;
                return response;
            }
            else{
                //game.setWhiteUsername(username);
                response.whiteUser = username;
            }
        }
        else if(Objects.equals(color, "BLACK")){
            if(game.blackUsername != null) {
                response.setMessage("Error: already taken");
                response.status = 403;
                return response;
            }
            else{
                //game.setBlackUsername(username);
                response.blackUser = username;
            }
        }
        gameDao.claimSpot(color, gameID, username);
        response.status = 200;


        return response;
    }
}
