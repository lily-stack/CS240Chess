package chess;

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
        TeamColor teamColor = piece.getTeamColor();
        Collection<ChessMove> allMoves = piece.pieceMoves(board, startPosition);
        Collection<ChessMove> allValidMoves = new HashSet<>();
        ChessPosition possiblePos = new Position();
        ChessMove aMove = new Move();
        ChessPosition kingPos = findKing(teamColor);
        ChessPiece testPiece = new Piece();
        boolean tookEnemy = false;
        for(ChessMove move: allMoves){
            if(board.getPiece(move.getEndPosition()) == null) {
                board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
                board.deletePiece(move.getStartPosition());
            }
            else{
                testPiece = board.getPiece(move.getEndPosition());
                board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
                board.deletePiece(move.getStartPosition());
                tookEnemy = true;
            }
            boolean inCheck = isInCheck(teamColor);
            if(!isInCheck(teamColor)){
                allValidMoves.add(move);
            }
            board.addPiece(move.getStartPosition(), board.getPiece(move.getEndPosition()));
            board.deletePiece(move.getEndPosition());
            if(tookEnemy == true){
                tookEnemy = false;
                board.addPiece(move.getEndPosition(), testPiece);
            }
        }

        return allValidMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition endPos = move.getEndPosition();
        ChessPosition startPos = move.getStartPosition();
        ChessPiece piece = board.getPiece(startPos);
        Collection<ChessMove> possibleMoves = validMoves(move.getStartPosition());
        if (possibleMoves.contains(move) && piece.getTeamColor() == color) {
            if (move.getPromotionPiece() != null) {
                piece.SetType(move.getPromotionPiece());
            }
            board.addPiece(endPos, board.getPiece(startPos));
            board.deletePiece(startPos);
            if (piece.getTeamColor() == TeamColor.WHITE) {
                color = TeamColor.BLACK;
            } else {
                color = TeamColor.WHITE;
            }
        }

        else{
            throw new InvalidMoveException();
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition king = findKing(teamColor);
        if(king == null){
            return false;
        }
        ChessPiece tempPiece;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ChessPosition tempPos = new Position();
                tempPos.setRow(i);
                tempPos.setColumn(j);
                tempPiece = board.getPiece(tempPos);
                if(tempPiece == null){
                    continue;
                }
                Collection<ChessMove> tests = tempPiece.pieceMoves(board,tempPos);
                for(ChessMove move:tempPiece.pieceMoves(board,tempPos)){
                    if(move.getEndPosition().getColumn() == king.getColumn() && move.getEndPosition().getRow() == king.getRow()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPosition king = findKing(teamColor);
        ChessPiece tempPiece;
        if(isInCheck(teamColor)){
            Collection<ChessMove> kingMoves = validMoves(king);
            ChessPosition kingStart = king;
            for(ChessMove move: kingMoves){
                board.addPiece(move.getEndPosition(), board.getPiece(king));
                board.deletePiece(king);
                if(!isInCheck(teamColor)){
                    board.addPiece(kingStart, board.getPiece(move.getEndPosition()));
                    board.deletePiece(move.getEndPosition());
                    return false;
                }
                else{
                    board.addPiece(kingStart, board.getPiece(move.getEndPosition()));
                    board.deletePiece(move.getEndPosition());
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
    public ChessPosition findKing(TeamColor teamColor){
        for (int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                ChessPosition king = new Position();
                king.setRow(i);
                king.setColumn(j);
                ChessPiece test = board.getPiece(king);
                if(board.getPiece(king) != null && test.getPieceType() == ChessPiece.PieceType.KING && test.getTeamColor() == teamColor){
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
                if (piece != null && piece.getTeamColor() == teamColor && !validMoves(pos).isEmpty()){
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
