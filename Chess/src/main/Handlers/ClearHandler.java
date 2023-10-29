package Handlers;

import Requests.ClearRequest;
import Responses.ClearResponse;
import Services.ClearService;
import dataAccess.DataAccessException;
import spark.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

public class ClearHandler {
    //public Response Request(Request req, Response res){return null;}

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
        /**Gson gson = new Gson();
        ClearRequest request = (ClearRequest)gson.fromJson(req.body(), ClearRequest.class);
        ClearService service = new ClearService();
        try {
            res.status(200);
            ClearResponse result = service.clear(request);
            return gson.toJson(result);
        }
        catch(DataAccessException e){
            res.status(500);
            ClearResponse result = new ClearResponse();
            //result.
            return gson.toJson(result);
        }**/
    }
}
