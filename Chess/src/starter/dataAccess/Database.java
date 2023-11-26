package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Database is responsible for creating connections to the database. Connections are
 * managed with a simple pool in order to increase performance. To obtain and
 * use connections represented by this class use the following pattern.
 *
 * <pre>
 *  public boolean example(String selectStatement, Database db) throws DataAccessException{
 *    var conn = db.getConnection();
 *    try (var preparedStatement = conn.prepareStatement(selectStatement)) {
 *        return preparedStatement.execute();
 *    } catch (SQLException ex) {
 *        throw new DataAccessException(ex.toString());
 *    } finally {
 *        db.returnConnection(conn);
 *    }
 *  }
 * </pre>
 */
public class Database {

    // FIXME: Change these fields, if necessary, to match your database configuration
    public static final String DB_NAME = "chess";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "cs240";

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";

    private final LinkedList<Connection> connections = new LinkedList<>();

    /**
     * Get a connection to the database. This pulls a connection out of a simple
     * pool implementation. The connection must be returned to the pool after
     * you are done with it by calling {@link #returnConnection(Connection) returnConnection}.
     *
     * @return Connection
     */
    synchronized public Connection getConnection() throws DataAccessException {
        try {
            System.out.println("get connection");
            Connection connection;
            if (connections.isEmpty()) {
                connection = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
                connection.setCatalog(DB_NAME);
            } else {
                connection = connections.removeFirst();
            }
            return connection;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Return a previously acquired connection to the pool.
     *
     * @param connection previous obtained by calling {@link #getConnection() getConnection}.
     */
    synchronized public void returnConnection(Connection connection) {
        System.out.println("return connection");
        connections.add(connection);
    }

    public void configureDatabase() throws SQLException, DataAccessException {
        try (var conn = getConnection()) {
            var createDbStatement = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS chess");
            createDbStatement.executeUpdate();

            conn.setCatalog("chess");
            var createAuthDaoTable =

                """
                CREATE TABLE  IF NOT EXISTS authDao (
                    authToken varchar(150),
                    username varchar(150) not null,
                    PRIMARY KEY (authToken)
                )""";

            var createGameDaoTable =

                """
                CREATE TABLE  IF NOT EXISTS gameDao (
                    gameId int auto_increment,
                    whiteUsername varchar(150),
                    blackUsername varchar(150),
                    gameName varchar(150),
                    game varchar(1500),
                    PRIMARY KEY (gameId)
                )""";

            var createUserDaoTable =

                """
                CREATE TABLE  IF NOT EXISTS userDao (
                    username varchar(150),
                    password varchar(150) not null,
                    email varchar(150),
                    PRIMARY KEY (username)
                )""";

            try (var createTableStatement = conn.prepareStatement(createAuthDaoTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createGameDaoTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createUserDaoTable)) {
                createTableStatement.executeUpdate();
            }
        }
    }
}

