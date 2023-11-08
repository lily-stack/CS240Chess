package Handlers;

import Models.GameModel;
import Responses.ListGamesResponse;
import Services.ListGamesService;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import spark.Request;
import spark.Response;

public class ListGameHandler {
    public Object handleRequest(Request req, Response res) throws DataAccessException {
        ListGamesService service = new ListGamesService();
        int status = 200;
        String message = "";
        String authToken = req.headers("Authorization");
        GameModel game = new Gson().fromJson(req.body(), GameModel.class);
        ListGamesResponse listResponse;

        listResponse = service.listGames(authToken);
        status = service.checkGameList(authToken);

        res.type("application/json");
        res.status(status);
        if (status == 401){
            res.status(status);
            listResponse.setMessage(message = "Error: unauthorized");
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }
        else if (status == 500){
            res.status(status);
            listResponse.setMessage(message = "Error: description");
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }
        else{
            res.status(status);
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }
    }
}
