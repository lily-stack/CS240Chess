package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Requests.JoinGameRequest;
import Responses.CreateGameResponse;
import Responses.RegisterResponse;
import Server.ServerFacade;
import chess.ChessGame;
import com.google.gson.Gson;
import Models.*;
import exception.ResponseException;
import Client.websocket.NotificationHandler;

//import server.ServerFacade;
import Client.websocket.WebSocketFacade;
import ui.Chess;


public class ChessClient {
    private String userName = null;
    private final ServerFacade server;
    private final String serverUrl;
    //private final NotificationHandler notificationHandler;
    private WebSocketFacade ws;

    private State state = State.SIGNEDOUT;

    public String token;
    List<Integer> gameList = new ArrayList<>();


    public ChessClient(String serverUrl) {
        server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
        //this.notificationHandler = notificationHandler;
    }

    public String eval(String input) {
        try {
            var tokens = input.toLowerCase().split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "login" -> logIn(params);
                case "register" -> register(params);
                case "list" -> listGames();
                case "logout" -> logOut();
                case "create" -> create(params);
                case "join" -> join(params);
                case "observe" -> observe(params);
                case "quit" -> "quit";
                default -> help();
            };
        } catch (ResponseException ex) {
            return ex.getMessage();
        }
    }

    private String observe(String ... params) throws ResponseException {
        assertSignedIn();
        int gameNumber = Integer.parseInt(params[0]) - 1;
        int gameID = gameList.get(gameNumber);
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(gameID);
        server.joinGame(gameID, null, token);
        //Chess.main();
        return String.format("observing game %s", gameNumber + 1);
    }

    private String join(String ... params) throws ResponseException {
        assertSignedIn();
        String color = params[1];
        int gameNumber = Integer.parseInt(params[0]) - 1;
        int gameID = gameList.get(gameNumber);
        JoinGameRequest request = new JoinGameRequest();
        request.setPlayerColor(color);
        request.setGameID(gameID);
        server.joinGame(gameID, color, token);
        //Chess.main();
        return String.format("joined game %s", gameNumber + 1);
    }

    public String create(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length < 1) {
            throw new ResponseException(400, "Expected: <gameName>");
        }
        String gameName = params[0];
        server.createGame(gameName, token);

        return String.format("Game '%s' created successfully.", gameName);

    }

    private String logOut() throws ResponseException{
        assertSignedIn();
        //ws.logOut(userName);
        //ws = null;
        server.logoutUser(token);
        state = State.SIGNEDOUT;
        return String.format("%s is signed out", userName);
    }

    public String listGames() throws ResponseException {
        assertSignedIn();
        gameList.clear();
        var games = server.listGames(token);
        var result = new StringBuilder();
        int itr = 1;
        for (var game : games) {
            result.append(itr).append("---")
                    .append("GameName: ").append(game.gameName).append(", ")
                    .append("WhitePlayer: ").append(game.whiteUsername).append(", ")
                    .append("BlackPlayer: ").append(game.blackUsername)
                    .append('\n');
            itr++;
            gameList.add(game.gameID);
        }
        return result.toString();
    }

    public String register(String... params) throws ResponseException {
        if (params.length >= 2) {
            state = State.SIGNEDIN;
            userName = params[0];
            var password = params[1];
            var email = params[2];
            var user = new User(userName, password, email);
            setToken(server.registerUser(user));
            return String.format("You registered %s", user.username);
        }
        throw new ResponseException(400, "Expected: <username> <password> <email>");
    }

    public String logIn(String... params) throws ResponseException {
        if (params.length >= 1) {
            state = State.SIGNEDIN;
            userName = params[0];
            String password = params[1];
            setToken(server.loginUser(userName, password));
            return String.format("You signed in as %s.", userName);
        }
        throw new ResponseException(400, "Expected: <userName>");
    }

    public String help() {
        if (state == State.SIGNEDOUT) {
            return """
                    - register <USERNAME> <PASSWORD> <EMAIL> - to create an account
                    - login <USERNAME> <PASSWORD> - to play chess
                    - quit  - playing chess
                    - help - with possible commands
                    """;
        }
        return """
                - create <NAME> - a game
                - list - games
                - join <ID> [WHITE|BLACK|<empty>] <color> - a game
                - observe <ID> - a game
                - logout - when you are done
                - quit - playing chess
                - help - with possible commands
                """;
    }

    private void assertSignedIn() throws ResponseException {
        if (state == State.SIGNEDOUT) {
            throw new ResponseException(400, "You must sign in");
        }
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
