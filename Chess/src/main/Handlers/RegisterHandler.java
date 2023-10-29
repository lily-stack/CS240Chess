package Handlers;

import Models.AuthToken;
import Requests.LoginRequest;
import Requests.RegisterRequest;
import Responses.LoginResponse;
import Responses.RegisterResponse;
import Services.LoginService;
import Services.RegisterService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class RegisterHandler {
    public Object handleRequest(Request req, Response res) {
        RegisterService service = new RegisterService();
        int status = 200;
        String message = "";
        RegisterRequest registerRequest = new Gson().fromJson(req.body(), RegisterRequest.class);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse = service.register(registerRequest.getUsername(),registerRequest.getPassword(), registerRequest.getEmail());
        if (status == 400){
            message = "Error: bad request";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else if (status == 403){
            message = "Error: already taken";
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
            res.body(new Gson().toJson(Map.of("username",registerResponse.getUsername(),"password", registerResponse.getAuthToken())));
            return new Gson().toJson(Map.of("username",registerResponse.getUsername(),"password", registerResponse.getAuthToken()));
        }
    }
}
