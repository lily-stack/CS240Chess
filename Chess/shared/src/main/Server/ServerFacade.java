package Server;

import Models.GameModel;
import Requests.*;
import Responses.CreateGameResponse;
import Responses.JoinGameResponse;
import Responses.LoginResponse;
import Responses.RegisterResponse;
import com.google.gson.Gson;
import exception.ResponseException;
import deserializer.DeserializeGame;
import Models.User;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }
    public void clear() throws ResponseException{
        var path = "/db";
        this.makeRequest("DELETE", path, null, null);
    }

    public void createGame(String gameName, String authToken) throws ResponseException {
        var path = "/game";
        CreateGameRequest request = new CreateGameRequest();
        request.setAuthToken(authToken);
        request.setGameName(gameName);
        this.makeRequest("POST", path, request, CreateGameResponse.class);
    }
    public String registerUser(User user) throws ResponseException {
        //clear();//get rid of
        var path = "/user";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(user.username);
        request.setPassword(user.password);
        request.setEmail(user.email);
        RegisterResponse response = this.makeRequest("POST", path, request, RegisterResponse.class);
        return response.getAuthToken();
    }
    public String loginUser(String userName, String password) throws ResponseException {
        var path = "/session";
        LoginRequest request = new LoginRequest();
        request.setUsername(userName);
        request.setPassword(password);
        LoginResponse response = this.makeRequest("POST", path, request, LoginResponse.class);
        return response.getAuthToken();
    }

    public void logoutUser(String username) throws ResponseException {
        //var path = "/session";
        var path = String.format("/session/%s", username);
        this.makeRequest("DELETE", path, null, null);
    }

    /*public void deleteAllUsers() throws ResponseException {
        var path = "/user";
        this.makeRequest("DELETE", path, null, null);
    }*/

    public GameModel[] listGames() throws ResponseException {
        var path = "/game";
        //maybe get rid of
        record listGameResponse(GameModel[] game) { }
        var response = this.makeRequest("GET", path, null, listGameResponse.class);
        return response.game();
    }
    public void joinGame(int gameNumber, String color) throws ResponseException {
        var path = "/game";
        JoinGameRequest request = new JoinGameRequest();
        request.setPlayerColor(color);
        request.setGameID(gameNumber);
        this.makeRequest("PUT", path, request, JoinGameResponse.class);
    }
    public void observeGame(int gameNumber) throws ResponseException {
        joinGame(gameNumber, null);
    }

    public <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            var json = new Gson().toJson(request);
            var token = new Gson().fromJson(json, AuthenticateToken.class);
            if(token != null) {
                http.addRequestProperty("authorization", token.getToken());
            }

            if (!Objects.equals(http.getRequestMethod(), "GET")){
                http.addRequestProperty("Content-Type", "application/json");
                String reqData = new Gson().toJson(request);
                try (OutputStream reqBody = http.getOutputStream()) {
                    reqBody.write(reqData.getBytes());
                }
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new ResponseException(status, "failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = DeserializeGame.deserializeGame(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}
