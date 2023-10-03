package chess;

import java.util.Collection;

public class Piece implements ChessPiece{
    public PieceType type = null;
    public ChessGame.TeamColor color = null;
    //setposition(int r,int c)
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    public void SetType(PieceType t){
        type = t;
    }
    public void SetColor(ChessGame.TeamColor c) {
        color = c;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}
