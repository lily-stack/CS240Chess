package Handlers;
import Requests.RegisterRequest;
import Responses.RegisterResponse;
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
        RegisterResponse registerResponse;
        registerResponse = service.register(registerRequest.getUsername(),registerRequest.getPassword(), registerRequest.getEmail());
        status = registerResponse.getStatus();
        if(registerRequest.getUsername()==null || registerRequest.getEmail()==null || registerRequest.getPassword()==null){
            status = 400;
        }
        if (status == 400){
            res.status(status);
            message = "Error: bad request";
            res.type("application/json");
            res.body(new Gson().toJson(Map.of("message",message)));
            return new Gson().toJson(Map.of("message", message));
        }
        else if (status == 403){
            message = "Error: already taken";
            res.status(status);
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
            res.type("application/json");
            //res.body(new Gson().toJson(Map.of("username",registerResponse.getUsername(),"authToken", registerResponse.getAuthToken())));
            res.body(new Gson().toJson(registerResponse));
            return new Gson().toJson(registerResponse);
        }
    }
}
