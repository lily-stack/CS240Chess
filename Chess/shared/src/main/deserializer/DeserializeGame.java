package deserializer;

import chess.*;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;

public class DeserializeGame {
    public static ChessGame deserializeGame(String gameString){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessGame.class, new GameAdapter());
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new PieceAdapter());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new BoardAdapter());
        return gsonBuilder.create().fromJson(gameString, game.class);
    }

    public static <T> T deserializeGame(Reader reader, Class<T> responseClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Deserialize FriendList to ArrayFriendList if used.
        gsonBuilder.registerTypeAdapter(ChessGame.class, new GameAdapter());
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new PieceAdapter());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new BoardAdapter());
        return gsonBuilder.create().fromJson(reader, responseClass);
    }
}
