package Handlers;

import Models.AuthToken;
import Models.GameModel;
import Requests.JoinGameRequest;
import Requests.LoginRequest;
import Responses.LoginResponse;
import Services.LoginService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class LoginHandler {

    public Object handleRequest(Request req, Response res) {
        LoginService service = new LoginService();
        int status = 200;
        String message = "";
        LoginRequest loginRequest = new Gson().fromJson(req.body(), LoginRequest.class);
        LoginResponse loginResponse = new Gson().fromJson(res.body(), LoginResponse.class);
        AuthToken token = service.login(loginRequest.getUsername(),loginRequest.getPassword());
        status = service.loginTest(loginRequest.getUsername(),loginRequest.getPassword());
        loginResponse.setUsername(token.username);
        loginResponse.setAuthToken(token.authToken);
        if (status == 401){
            message = "Error: unauthorized";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else if (status == 500){
            message = "Error: description";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else{
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("username",loginResponse.getUsername(),"password", loginResponse.getAuthToken())));
            return new Gson().toJson(Map.of("username",loginResponse.getUsername(),"password", loginResponse.getAuthToken()));
        }
    }
}
