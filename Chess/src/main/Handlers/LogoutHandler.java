package Handlers;

import Models.AuthToken;
import Models.GameModel;
import Requests.ListGamesRequest;
import Requests.LogoutRequest;
import Responses.LoginResponse;
import Responses.LogoutResponse;
import Services.ListGamesService;
import Services.LogoutService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class LogoutHandler {
    public Object handleRequest(Request req, Response res) {
        LogoutService service = new LogoutService();
        int status = 200;
        String message = "";
        String authToken = req.headers("Authorization");
        status = service.logout(authToken);
        if (status == 401){
            res.status(status);
            message = "Error: unauthorized";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else if (status == 500){
            res.status(status);
            message = "Error: description";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else{
            res.status(status);
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
    }
}
