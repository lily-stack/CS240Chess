package deserializer;

import Models.GameModel;
import chess.Board;
import chess.ChessBoard;
import chess.ChessPiece;
import chess.Piece;
import com.google.gson.*;

import java.lang.reflect.Type;
public class GameAdapter implements JsonDeserializer<GameModel>{
    public GameModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new BoardAdapter());
        return gsonBuilder.create().fromJson(jsonElement, GameModel.class);
    }
}
