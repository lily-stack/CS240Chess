import Handlers.*;
import dataAccess.DataAccessException;
import dataAccess.Database;
import spark.*;

import java.sql.SQLException;

public class Server {
    private void run() {
        Database database = new Database();
        try {
            database.configureDatabase();
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }

        Spark.port(8080);
        Spark.externalStaticFileLocation("src/web");

        // Register handlers for each endpoint using the method reference syntax
        Spark.post("/user", (req, res) -> (new RegisterHandler()).handleRequest(req, res));
        Spark.delete("/db", (req, res) -> (new ClearHandler()).handleRequest(req, res));
        Spark.post("/session", (req, res) -> (new LoginHandler()).handleRequest(req, res));
        Spark.delete("/session", (req, res) -> (new LogoutHandler()).handleRequest(req, res));
        Spark.get("/game", (req, res) -> (new ListGameHandler()).handleRequest(req, res));
        Spark.post("/game", (req, res) -> (new CreateGameHandler()).handleRequest(req, res));
        Spark.put("/game", (req, res) -> (new JoinGameHandler()).handleRequest(req, res));
    }
    public static void main(String[] args){
        new Server().run();
    }

}
