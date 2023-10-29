package Handlers;
import Models.GameModel;
import Requests.JoinGameRequest;
import Responses.JoinGameResponse;
import Services.JoinGameService;
import dataAccess.DataAccessException;
import spark.*;
import com.google.gson.Gson;

import java.util.Map;

public class JoinGameHandler {
    public Response Request(Request req, Response res){return null;}

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
/**
        Gson gson = new Gson();
        JoinGameRequest request = (JoinGameRequest)gson.fromJson(req.body(), JoinGameRequest.class);
        JoinGameService service = new JoinGameService();

        JoinGameResponse result = service.join(request);

        return gson.toJson(result);
 **/
    }
}
