package Handlers;

import Models.GameModel;
import Requests.CreateGameRequest;
import Responses.CreateGameResponse;
import Services.CreateGameService;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import spark.Request;
import spark.Response;

import java.util.Map;

public class CreateGameHandler {
    int gameID = 0;
    public Object handleRequest(Request req, Response res) throws DataAccessException {
        CreateGameService service = new CreateGameService();
        int status = 200;
        String message = "";
        String authToken = req.headers("Authorization");

        GameModel newGame = new Gson().fromJson(req.body(), GameModel.class);
        if(newGame.getGameName() == null){
            status = 400;
            message = "Error: bad request";
        }
        else{
            gameID += 66;
            newGame.setGameID(this.gameID);
            status = service.create(newGame, authToken);
        }
        if(status == 401){
            message = "Error: unauthorized";
        }
        else if(status == 500){
            message = "Error: description";
        }
        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message",message)));
        return new Gson().toJson(Map.of("message", message));
    }
   /** public Response Request(Request req, Response res){return null;}

    public Object handleRequest(Request req, Response res) throws DataAccessException {
        Gson gson = new Gson();
        CreateGameRequest request = (CreateGameRequest)gson.fromJson(req.body(), CreateGameRequest.class);
        CreateGameService service = new CreateGameService();

        CreateGameResponse result = service.create(request);

        return gson.toJson(result);
    }**/
}
