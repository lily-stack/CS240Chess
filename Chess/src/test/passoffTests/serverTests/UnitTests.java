package passoffTests.serverTests;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import DataAccess.UserDao;
import Models.AuthToken;
import Models.GameModel;
import Models.User;
import Requests.LoginRequest;
import Responses.RegisterResponse;
import Services.*;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

public class UnitTests {
    String USER1 = "user1";
    String USER2 = "user2";
    String PASSWORD1 = "password1";
    String PASSWORD2 = "password2";
    String EMAIL1 = "email1";
    String EMAIL2 = "email2";
    String AUTHTOKEN = "token123";
    UserDao userDao = new UserDao();
    AuthDao authDao = new AuthDao();
    GameDao gameDao = new GameDao();
    ClearService clearService = new ClearService();
    @Test
    public void ClearAll(){
        int status = 200;
        try{
            userDao.CreateUser("clearuser","clearpassword","clearemail");
        }
        catch(DataAccessException e){
            Assertions.fail();
        }
        try {
            clearService.clear();
        }
        catch(DataAccessException e){
            Assertions.fail();
        }
        try {
            status = userDao.FindUser("clearuser", "clearpassword");
            if(status == 200){
                Assertions.fail("Found user when there should be no users");
            }
        }
        catch(DataAccessException e){
            Assertions.assertTrue(status==403);
        }
    }
    @Test
    public void CreateGameServicePassTest()throws DataAccessException{
        ClearAll();
        CreateGameService gameservice = new CreateGameService();
        GameModel game = new GameModel();
        game.gameID = 1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authToken = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertEquals(200, gameservice.create(game, AUTHTOKEN));
    }
    @Test
    public void CreateGameServiceFailTest()throws DataAccessException{
        ClearAll();
        CreateGameService gameservice = new CreateGameService();
        GameModel game = new GameModel();
        game.gameID = 1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authToken = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertEquals(401, gameservice.create(game, "notToken"));
    }
    @Test
    public void RegisterServicePassTest(){
        ClearAll();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertNotNull(response.authToken);
        try {
            userDao.FindUser(USER1, PASSWORD1);
        }
        catch(DataAccessException e){
            Assertions.fail("failed register test: could not find user");
        }
    }
    @Test
    public void RegisterServiceFailTest(){
        ClearAll();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = new RegisterResponse();
        response = registerService.register(USER1, PASSWORD1, EMAIL1);
        response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(403, response.getStatus());
    }
    @Test
    public void LoginPassTest()throws DataAccessException{
        ClearAll();
        RegisterService registerService = new RegisterService();
        registerService.register(USER1, PASSWORD1, EMAIL1);
        LoginService loginService = new LoginService();
        Assertions.assertEquals(200, loginService.loginTest(USER1, PASSWORD1));
    }
    @Test
    public void LoginFailTest()throws DataAccessException{
        ClearAll();
        RegisterService registerService = new RegisterService();
        registerService.register(USER1, PASSWORD1, EMAIL1);
        LoginService loginService = new LoginService();
        Assertions.assertEquals(401, loginService.loginTest(USER2,PASSWORD2));
    }
    @Test
    public void LogoutPassTest()throws DataAccessException{
        ClearAll();
        User user = new User();
        user.username = USER1;
        user.email = EMAIL1;
        user.password = PASSWORD1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authToken = AUTHTOKEN;
        authDao.Insert(authToken);
        RegisterService registerService = new RegisterService();
        registerService.register(USER1, PASSWORD1, EMAIL1);
        LogoutService logoutService = new LogoutService();
        Assertions.assertEquals(200, logoutService.logout(AUTHTOKEN));
    }
    @Test
    public void LogoutFailTest()throws DataAccessException{
        ClearAll();
        User user = new User();
        user.username = USER1;
        user.email = EMAIL1;
        user.password = PASSWORD1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authToken = AUTHTOKEN;
        authDao.Insert(authToken);
        RegisterService registerService = new RegisterService();
        registerService.register(USER1, PASSWORD1, EMAIL1);
        LogoutService logoutService = new LogoutService();
        Assertions.assertEquals(200, logoutService.logout("fakeAuth"));
    }
    @Test
    public void ListGamesPassTest()throws DataAccessException{
        ClearAll();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        GameModel game2 = new GameModel();
        game2.gameID = 2;
        gameDao.Insert(game2);
        AuthToken token = new AuthToken();
        token.authToken = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        ListGamesService listGamesService = new ListGamesService();
        Assertions.assertEquals(200, listGamesService.listGames(AUTHTOKEN).getStatus());
    }
    @Test
    public void ListGamesFailTest()throws DataAccessException{
        ClearAll();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        GameModel game2 = new GameModel();
        game2.gameID = 2;
        gameDao.Insert(game2);
        AuthToken token = new AuthToken();
        token.authToken = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        ListGamesService listGamesService = new ListGamesService();
        Assertions.assertEquals(401, listGamesService.listGames("notToken").getStatus());
    }
    @Test
    public void JoinGamePassTest()throws DataAccessException {
        ClearAll();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        AuthToken token = new AuthToken();
        token.authToken = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        JoinGameService joinService = new JoinGameService();
        Assertions.assertEquals(200, joinService.join(1,AUTHTOKEN,"WHITE"));
    }
    @Test
    public void JoinGameFailTest()throws DataAccessException {
        ClearAll();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        AuthToken token = new AuthToken();
        token.authToken = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        JoinGameService joinService = new JoinGameService();
        Assertions.assertEquals(400, joinService.join(4,AUTHTOKEN,"WHITE"));
    }
}
