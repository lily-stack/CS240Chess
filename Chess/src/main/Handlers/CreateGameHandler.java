package Handlers;

import Models.GameModel;
import Services.CreateGameService;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.Map;

public class CreateGameHandler {
    public Object handleRequest(Request req, Response res) throws DataAccessException, SQLException {
        CreateGameService service = new CreateGameService();
        int status = 200;
        String message = "";
        String authToken = req.headers("authorization");

        GameModel newGame = new Gson().fromJson(req.body(), GameModel.class);
        if(newGame.getGameName() == null){
            status = 400;
            message = "Error: bad request";
        }
        else{
            status = service.create(newGame, authToken);
        }
        if(status == 401){
            message = "Error: unauthorized";
        }
        else if(status == 500){
            message = "Error: description";
        }
        if(status == 200){
            res.type("application/json");
            res.status(status);
            res.body(new Gson().toJson(Map.of("gameID",newGame.gameID)));
            return new Gson().toJson(Map.of("gameID", newGame.gameID));
        }
        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message",message)));
        return new Gson().toJson(Map.of("message", message));
    }
}
