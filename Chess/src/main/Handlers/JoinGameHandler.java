package Handlers;
import Models.GameModel;
import Requests.JoinGameRequest;
import Services.JoinGameService;
import dataAccess.DataAccessException;
import spark.*;
import com.google.gson.Gson;
import java.util.Map;

public class JoinGameHandler {
    public Object handleRequest(Request req, Response res) throws DataAccessException {
        JoinGameService service = new JoinGameService();
        int status = 200;
        String message = "";
        String authToken = req.headers("Authorization");

        JoinGameRequest joinRequest = new Gson().fromJson(req.body(), JoinGameRequest.class);
        GameModel game = new GameModel();
        game.setGameID(joinRequest.getGameID());
        if(game.getGameID() == -1){
            status = 400;
            message = "Error: bad request";
        }
        else{
            status = service.join(game.gameID, authToken, joinRequest.getPlayerColor());
        }
        if(status == 401){
            message = "Error: unauthorized";
        }
        else if (status == 400){
            message = "Error: bad request";
        }
        else if(status == 403){
            message = "Error: already taken";
        }
        else if(status == 500){
            message = "Error: description";
        }
        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message",message)));
        return new Gson().toJson(Map.of("message", message));
    }
}
