package chess;

import java.util.ArrayList;
import java.util.Collection;
public class game implements ChessGame{
    public TeamColor color;
    public Board board = new Board();
    @Override
    public TeamColor getTeamTurn() {
        return color;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        color = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){
            ChessPosition possiblePos = new Position();
            possiblePos.setColumn(col);
            possiblePos.setRow(row);
            for(int left = startPosition.getColumn() - 1; left >= 0; left--){
                possiblePos.setColumn(left);
                if(board.getPiece(possiblePos) == null){
                    break;
                }

            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){

        }
        return null;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {

    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }

    @Override
    public void setBoard(ChessBoard board) {
        //this.board = board;
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }


}
