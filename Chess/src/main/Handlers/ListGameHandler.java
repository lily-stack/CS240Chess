package Handlers;

import Models.GameModel;
import Requests.JoinGameRequest;
import Requests.ListGamesRequest;
import Responses.ListGamesResponse;
import Services.ListGamesService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.Map;

public class ListGameHandler {
    public Object handleRequest(Request req, Response res) {
        ListGamesService service = new ListGamesService();
        int status = 200;
        String message = "";
        String authToken = req.headers("Authorization");
        GameModel game = new Gson().fromJson(req.body(), GameModel.class);
        ListGamesResponse listResponse = new Gson().fromJson(req.body(), ListGamesResponse.class);
        ListGamesRequest listRequest = new Gson().fromJson(req.body(), ListGamesRequest.class);

        listResponse = service.listGames(authToken);
        status = service.checkGameList(authToken);

        res.type("application/json");
        res.status(status);
        if (status == 401){
            message = "Error: unauthorized";
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }
        else if (status == 500){
            message = "Error: description";
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }
        else{
            res.type("application/json");
            res.body(new Gson().toJson(listResponse));
            return new Gson().toJson(listResponse);
        }

    }
}
