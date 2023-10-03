package chess;

public class Move implements ChessMove{
    ChessPosition position;
    ChessPiece piece;
    Move(ChessPosition pos, ChessPiece piece){
        this.position = pos;
        this.piece = piece;
    }
    @Override
    public ChessPosition getStartPosition() {
        return this.position;
    }

    @Override
    public ChessPosition getEndPosition() {
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        return null;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return null;
    }
}
