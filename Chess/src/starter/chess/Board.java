package chess;

public class Board implements ChessBoard{
    int BOARD_LENGTH = 8;
    int BOARD_WIDTH = 8;
    private ChessPiece[][] theBoard = new ChessPiece[BOARD_WIDTH][BOARD_LENGTH];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int column = position.getColumn();
        int row = position.getRow();
        theBoard[row][column] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow();
        int column = position.getColumn();
        return theBoard[row][column];
    }

    @Override
    public void resetBoard() {
        for(int i = 0; i < BOARD_WIDTH; i++) {
            for(int j = 0; j < BOARD_LENGTH; j++){
                Position thePosition = new Position();
                thePosition.row = i;
                thePosition.column = j;
                ChessPiece piece = new Piece();
                if(i < 2){
                    // thePiece.setColor
                    piece.SetColor(ChessGame.TeamColor.WHITE);
                }
                if(i > 5){
                    // thePiece.setColor
                    piece.SetColor(ChessGame.TeamColor.BLACK);
                }
                if(i == 1 || i == 6) {
                    //pawn enum
                    piece.SetType(ChessPiece.PieceType.PAWN);
                    //theBoard[i][j] = piece;
                    addPiece(thePosition,piece);
                }
                if(i == 0 || i == 7){
                    if(j == 0 || j == 7){
                        //castle
                        piece.SetType(ChessPiece.PieceType.ROOK);
                        addPiece(thePosition,piece);
                    }
                    if(j == 1 || j == 6){
                        //knight
                        piece.SetType(ChessPiece.PieceType.KNIGHT);
                        addPiece(thePosition,piece);
                    }
                    if(j == 2 || j == 5){
                        //bishop
                        piece.SetType(ChessPiece.PieceType.BISHOP);
                        addPiece(thePosition,piece);
                    }
                    if(j == 4) {
                        //king
                        piece.SetType(ChessPiece.PieceType.KING);
                        addPiece(thePosition,piece);
                    }
                    if(j == 3){
                        //queen
                        piece.SetType(ChessPiece.PieceType.QUEEN);
                        addPiece(thePosition,piece);
                    }
                }
            }
        }
    }
}
