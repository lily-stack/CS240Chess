package Handlers;

import Services.ClearService;
import dataAccess.DataAccessException;
import spark.*;
import com.google.gson.Gson;
import java.util.Map;

public class ClearHandler {
    public Object handleRequest(Request req, Response res) {
        ClearService service = new ClearService();
        int status = 200;
        String message = "";
        try{
            service.clear();
        }
        catch(DataAccessException e){
            status = 500;
        }
        if(status == 500){
            message = "Error: description";
        }
        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message",message)));
        return new Gson().toJson(Map.of("message", message));
    }
}
