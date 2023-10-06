package chess;

import java.util.Collection;
import java.util.HashSet;

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
        ChessPiece piece = board.getPiece(myPosition);
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> possibleMoves = new HashSet<>();

        //ChessMove aMove = new Move();
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){
            possibleMoves.clear();
            if(piece.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(myPosition.getRow() == 6){
                    for(int i = 1; i < 3; i++) {
                        ChessPosition possiblePos = new Position();
                        possiblePos.setRow(row - i);
                        possiblePos.setColumn(col);
                        if (board.getPiece(possiblePos) != null) {
                            break;
                        }
                        else{
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                    }
                }
                if(row-1 > 0){
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(row-1);
                    possiblePos.setColumn(col);
                    if(board.getPiece(possiblePos) == null){
                        ChessPiece promotionPiece = new Piece();
                        /*if(possiblePos.getRow() == 0){
                            promotionPiece =
                        }*/
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                    }
                    for(int i = 0; i < 2; i++){
                        ChessPosition possiblePos2 = new Position();
                        if(i == 0 && col+1 < 8){
                            possiblePos2.setRow(row-1);
                            possiblePos2.setColumn(col+1);
                            //right take piece
                            if(board.getPiece(possiblePos2) != null){
                                if(board.getPiece(possiblePos2).getTeamColor() != color) {
                                    ChessMove aMove = new Move();
                                    aMove.setMove(myPosition, possiblePos2, null);
                                    possibleMoves.add(aMove);
                                    if (possiblePos2.getRow() == 0) {
                                        //promote
                                    }
                                }
                            }
                        }
                        if(i == 1 && col-1 > 0){
                            possiblePos2.setRow(row-1);
                            possiblePos2.setColumn(col-1);
                            //left take piece
                            if(board.getPiece(possiblePos2) != null){
                                if(board.getPiece(possiblePos2).getTeamColor() != color) {
                                    ChessMove aMove = new Move();
                                    aMove.setMove(myPosition, possiblePos, null);
                                    possibleMoves.add(aMove);
                                    if (possiblePos2.getRow() == 0) {
                                        //promote
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(myPosition.getRow() == 1){
                    for(int i = 1; i < 3; i++) {
                        ChessPosition possiblePos = new Position();
                        possiblePos.setRow(row + i);
                        possiblePos.setColumn(col);
                        if (board.getPiece(possiblePos) != null) {
                            break;
                        }
                        else{
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                    }
                }
                if(row+1 < 8){
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(row+1);
                    possiblePos.setColumn(col);
                    if(board.getPiece(possiblePos) == null){
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                    }
                    for(int i = 0; i < 2; i++){
                        ChessPosition possiblePos2 = new Position();
                        if(i == 0 && col+1 < 8){
                            possiblePos2.setRow(row+1);
                            possiblePos2.setColumn(col+1);
                            //right take piece
                            if(board.getPiece(possiblePos2) != null){
                                if(board.getPiece(possiblePos2).getTeamColor() != color) {
                                    ChessMove aMove = new Move();
                                    aMove.setMove(myPosition, possiblePos2, null);
                                    possibleMoves.add(aMove);
                                }
                            }
                        }
                        if(i == 1 && col-1 > 0){
                            possiblePos2.setRow(row+1);
                            possiblePos2.setColumn(col-1);
                            //left take piece
                            if(board.getPiece(possiblePos2) != null){
                                if(board.getPiece(possiblePos2).getTeamColor() != color) {
                                    ChessMove aMove = new Move();
                                    aMove.setMove(myPosition, possiblePos2, null);
                                    possibleMoves.add(aMove);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK) {
            for (int left = myPosition.getColumn() - 1; left >= 0; left--) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(left);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                }
                else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }


            for (int right = myPosition.getColumn() + 1; right < 8; right++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(right);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int up = myPosition.getRow() + 1; up < 8; up++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setColumn(col);
                possiblePos.setRow(up);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int down = myPosition.getRow() - 1; down >= 0; down--) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(down);
                possiblePos.setColumn(col);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){
            possibleMoves.clear();
            for(int i = 0; i < 8; i++){
                ChessPosition possiblePos = new Position();
                if(i == 0){
                    if(row+2 >= 8 || col+1 >= 8){continue;}
                    possiblePos.setRow(row+2);
                    possiblePos.setColumn(col+1);
                }
                else if(i == 1){
                    if(row+1 >= 8 || col+2 >= 8){continue;}
                    possiblePos.setRow(row+1);
                    possiblePos.setColumn(col+2);
                }
                else if(i == 2){
                    if(row-1 < 0 || col+2 >= 8){continue;}
                    possiblePos.setRow(row-1);
                    possiblePos.setColumn(col+2);
                }
                else if(i == 3){
                    if(row-2 < 0 || col+1 >= 8){continue;}
                    possiblePos.setRow(row-2);
                    possiblePos.setColumn(col+1);
                }
                else if(i == 4){
                    if(row-2 < 0 || col-1 < 0){continue;}
                    possiblePos.setRow(row-2);
                    possiblePos.setColumn(col-1);
                }
                else if(i == 5){
                    if(row-1 < 0 || col-2 < 0){continue;}
                    possiblePos.setRow(row-1);
                    possiblePos.setColumn(col-2);
                }
                else if(i == 6){
                    if(row+1 >= 8 || col-2 < 0){continue;}
                    possiblePos.setRow(row+1);
                    possiblePos.setColumn(col-2);
                }
                else if(i == 7){
                    if(row+2 >= 8 || col-1 < 0){continue;}
                    possiblePos.setRow(row+2);
                    possiblePos.setColumn(col-1);
                }
                if(board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                    }
                }
                else{
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){
            possibleMoves.clear();
            boolean breakLoop = false;
            for(int left = myPosition.getColumn() - 1; left >= 0; left--) {
                //up left diagnal
                if(left < 0 || breakLoop == true){
                    breakLoop = false;
                    break;
                }
                for (int up = myPosition.getRow() + 1; up < 8; up++) {
                    if(up >= 8 || left < 0){
                        left = -1;
                        breakLoop = true;
                        break;
                    }
                    if(up == 7){
                        breakLoop = true;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(up);
                    possiblePos.setColumn(left);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        left = -1;
                        breakLoop = true;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        left -= 1;
                    }
                }
            }
            for(int left = myPosition.getColumn() - 1; left >= 0; left--) {
                //down left diagnal
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(left < 0){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setColumn(left);
                    possiblePos.setRow(down);

                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color){
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        left = -1;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        left -= 1;
                    }
                }
            }
            for(int right = myPosition.getColumn() + 1; right < 8; right++) {
                //up right diagnal
                for (int up = myPosition.getRow() + 1; up < 8; up++) {
                    if(right >= 8){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(up);
                    possiblePos.setColumn(right);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        right = 8;
                        break;
                    } else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        right += 1;
                    }
                }
            }
            for(int right = myPosition.getColumn() + 1; right < 8; right++) {
                //down left diagnal

                for (int down = myPosition.getRow() - 1; down >= 0; down--) {
                    if(right >= 8){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setColumn(right);
                    possiblePos.setRow(down);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        right = 8;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        right += 1;
                    }
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){
            possibleMoves.clear();
            boolean breakLoop = false;
            for (int left = myPosition.getColumn() - 1; left >= 0; left--) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(left);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int right = myPosition.getColumn() + 1; right < 8; right++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(right);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int up = myPosition.getRow() + 1; up < 8; up++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setColumn(col);
                possiblePos.setRow(up);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int down = myPosition.getRow() - 1; down >= 0; down--) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(down);
                possiblePos.setColumn(col);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for(int left = myPosition.getColumn() - 1; left >= 0; left--) {
                //up left diagnal
                if(left < 0 || breakLoop == true){
                    breakLoop = false;
                    break;
                }
                for (int up = myPosition.getRow() + 1; up < 8; up++) {
                    if(up >= 8){
                        left = -1;
                        break;
                    }
                    if(up == 7){
                        breakLoop = true;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(up);
                    possiblePos.setColumn(left);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        left = -1;
                        breakLoop = true;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        left -= 1;
                    }
                }
            }
            for(int left = myPosition.getColumn() - 1; left >= 0; left--) {
                //down left diagnal
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(left < 0){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setColumn(left);
                    possiblePos.setRow(down);

                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color){
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        left = -1;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        left -= 1;
                    }
                }
            }
            for(int right = myPosition.getColumn() + 1; right < 8; right++) {
                //up right diagnal
                for (int up = myPosition.getRow() + 1; up < 8; up++) {
                    if(right >= 8){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setRow(up);
                    possiblePos.setColumn(right);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        right = 8;
                        break;
                    } else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        right += 1;
                    }
                }
            }
            for(int right = myPosition.getColumn() + 1; right < 8; right++) {
                //down left diagnal

                for (int down = myPosition.getRow() - 1; down >= 0; down--) {
                    if(right >= 8){
                        break;
                    }
                    ChessPosition possiblePos = new Position();
                    possiblePos.setColumn(right);
                    possiblePos.setRow(down);
                    if (board.getPiece(possiblePos) != null) {
                        if (board.getPiece(possiblePos).getTeamColor() != color) {
                            ChessMove aMove = new Move();
                            aMove.setMove(myPosition, possiblePos, null);
                            possibleMoves.add(aMove);
                        }
                        right = 8;
                        break;
                    }
                    else {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                        right += 1;
                    }
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){
            possibleMoves.clear();
            for(int i = 0; i < 8; i++){//right
                ChessPosition possiblePos = new Position();
                if(i == 0){ //right
                    if(myPosition.getColumn() == 7){continue;}
                    possiblePos.setColumn(col + 1);
                    possiblePos.setRow(row);
                }
                else if (i == 1) { //left
                    if(myPosition.getColumn() == 0){continue;}
                    possiblePos.setColumn(col - 1);
                    possiblePos.setRow(row);
                }
                else if (i == 2) { //up
                    if(myPosition.getRow() == 7){continue;}
                    possiblePos.setColumn(col);
                    possiblePos.setRow(row + 1);
                }
                else if (i == 3) { //down
                    if(myPosition.getRow() == 0){continue;}
                    possiblePos.setColumn(col);
                    possiblePos.setRow(row - 1);
                }
                else if (i == 4) { //up left diagnal
                    if(myPosition.getRow() == 7 || myPosition.getColumn() == 0){continue;}
                    possiblePos.setColumn(col - 1);
                    possiblePos.setRow(row + 1);
                }
                else if (i == 5) { //down left diagnal
                    if(myPosition.getRow() == 0 || myPosition.getColumn() == 0){continue;}
                    possiblePos.setColumn(col - 1);
                    possiblePos.setRow(row - 1);
                }
                else if (i == 6) { //up right diagnal
                    if(myPosition.getRow() == 7 || myPosition.getColumn() == 7){continue;}
                    possiblePos.setColumn(col + 1);
                    possiblePos.setRow(row + 1);
                }
                else if (i == 7){ //down right diagnal
                    if(myPosition.getRow() == 0 || myPosition.getColumn() == 7){continue;}
                    possiblePos.setColumn(col + 1);
                    possiblePos.setRow(row - 1);
                }
                if(board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setMove(myPosition, possiblePos, null);
                        possibleMoves.add(aMove);
                    }
                }
                else{
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
                possiblePos.setColumn(col);
                possiblePos.setRow(row);
            }
            /*
            //right
            possiblePos.setColumn(col + 1);
            possiblePos.setRow(row);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //left
            possiblePos.setColumn(col - 1);
            possiblePos.setRow(row);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //up
            possiblePos.setColumn(col);
            possiblePos.setRow(row + 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //down
            possiblePos.setColumn(col);
            possiblePos.setRow(row - 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //up left diagnal
            possiblePos.setColumn(col - 1);
            possiblePos.setRow(row + 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //down left diagnal
            possiblePos.setColumn(col - 1);
            possiblePos.setRow(row - 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //up right diagnal
            possiblePos.setColumn(col + 1);
            possiblePos.setRow(row + 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }
            //down right diagnal
            possiblePos.setColumn(col + 1);
            possiblePos.setRow(row - 1);
            if(board.getPiece(possiblePos) != null) {
                if (board.getPiece(possiblePos).getTeamColor() != color) {
                    ChessMove aMove = new Move();
                    aMove.setMove(myPosition, possiblePos, null);
                    possibleMoves.add(aMove);
                }
            }
            else{
                ChessMove aMove = new Move();
                aMove.setMove(myPosition, possiblePos, null);
                possibleMoves.add(aMove);
            }*/
        }
        return possibleMoves;
    }
}
