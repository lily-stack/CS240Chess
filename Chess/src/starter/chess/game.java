package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class game implements ChessGame{
    public TeamColor color;
    public ChessBoard board = new Board();
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
        Collection<ChessMove> allMoves = piece.pieceMoves(board, startPosition);
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
        Collection<ChessMove> possibleMoves = new HashSet<>();
        Collection<ChessMove> allValidMoves = new HashSet<>();
        ChessPosition possiblePos = new Position();
        ChessMove aMove = new Move();
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK) {

            possiblePos.setColumn(col);
            possiblePos.setRow(row);

            for(var move : allMoves) {
                ChessPiece tempPiece = board.getPiece(move.getStartPosition());
                possiblePos = move.getEndPosition();
                if (board.getPiece(possiblePos).getTeamColor() != tempPiece.getTeamColor()) {
                    allValidMoves.add(move);
                }
            }
        }
        return allValidMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition endPos = move.getEndPosition();
        ChessPosition startPos = move.getStartPosition();
        if(board.getPiece(endPos) == null){
            board.addPiece(endPos, board.getPiece(startPos));
            board.deletePiece(startPos);
        }
        else{
            throw new InvalidMoveException();
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition king = findKing(teamColor);
        ChessPosition tempPos = new Position();
        ChessPiece tempPiece;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tempPos.setRow(i);
                tempPos.setColumn(j);
                tempPiece = board.getPiece(tempPos);
                if(tempPiece == null){
                    continue;
                }
                for(ChessMove move:tempPiece.pieceMoves(board,tempPos)){
                    if(move.getEndPosition() == king){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {

        return false;
    }
    public ChessPosition findKing(TeamColor teamColor){
        ChessPosition king = new Position();
        for (int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                king.setRow(i);
                king.setColumn(j);
                if(board.getPiece(king) != null && board.getPiece(king).getPieceType() == ChessPiece.PieceType.ROOK && board.getPiece(king).getTeamColor() == color){
                    return king;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        ChessPosition pos = new Position();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                pos.setColumn(i);
                pos.setRow(j);
                ChessPiece piece = board.getPiece(pos);
                if (piece != null && piece.getTeamColor() == teamColor && !piece.pieceMoves(board,pos).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }


}
