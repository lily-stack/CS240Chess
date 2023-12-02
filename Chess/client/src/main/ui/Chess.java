package ui;

import Models.GameModel;
import chess.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

import static ui.EscapeSequences.*;
public class Chess {
    static game chessGame= new game();
    static ChessBoard board = chessGame.board;
    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final String P = " P ";
    private static final String K = " K ";
    private static final String Q = " Q ";
    private static final String R = " R ";
    private static final String N = " N ";
    private static final String B = " B ";

    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        //reverse board
        out.print(ERASE_SCREEN);
        board.resetBoard();

        drawReverseChessBoard(out, board);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);

        out.print("\n");
        //normaal board
        out.print(ERASE_SCREEN);
        board.resetBoard();

        drawChessBoard(out, board);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }
    private static void drawChessBoard(PrintStream out, ChessBoard board) {

        drawColumnHeaders(out);
        String color = "w";
        for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {
            if(color.equals("b")){
                color = "w";
            }
            else{
                color = "b";
            }
            drawRowHeader(out, boardRow);
            drawRowOfSquares(out, color, boardRow, board);
        }
        drawColumnHeaders(out);
    }
    private static void drawReverseChessBoard(PrintStream out, ChessBoard board) {
        drawReverseHeaders(out);
        String color = "w";
        for (int boardRow = 7; boardRow >= 0; --boardRow) {
            if(color.equals("b")){
                color = "w";
            }
            else{
                color = "b";
            }
            drawRowHeader(out, boardRow);
            drawReverseRowOfSquares(out, color, boardRow, board);
        }
        drawReverseHeaders(out);
    }
    private static void drawReverseHeaders(PrintStream out) {
        // Draw an empty space for the top-left corner
        setBlack(out);
        out.print("   ");

        // Draw column headers (letters)
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, String.valueOf((char) ('a' + boardCol)));
        }

        out.println(); // Move to the next line for the rows
    }
    private static void drawColumnHeaders(PrintStream out) {
        // Draw an empty space for the top-left corner
        setBlack(out);
        out.print("   ");

        // Draw column headers (letters)
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, String.valueOf((char) ('h' - boardCol)));
        }

        out.println(); // Move to the next line for the rows
    }

    private static void drawRowHeader(PrintStream out, int rowNumber) {
        // Draw row header (number)
        drawHeader(out, String.valueOf(rowNumber+1));
    }

    private static void drawHeader(PrintStream out, String headerText) {
        out.print(" ");
        printHeaderText(out, headerText);
        out.print(" ");
    }
    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_GREEN);

        out.print(player);

        setBlack(out);
    }

    private static void drawRowOfSquares(PrintStream out, String color, int row, ChessBoard board) {
        if(Objects.equals(color, "b")) {
            //setBlack(out);
            out.print(SET_BG_COLOR_MAGENTA);
        }
        else{
            setWhite(out);
        }
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES ; boardCol++) {
            String pieceLetter = " ";
            Position pos = new Position();
            pos.setColumn(boardCol);
            pos.setRow(row);
            ChessPiece piece;
            piece = board.getPiece(pos);
            //if (boardCol != 0) {
                if((Objects.equals(color, "b"))){
                    out.print(SET_BG_COLOR_DARK_GREEN);
                    color = "w";
                }
                else{
                    //setBlack(out);
                    out.print(SET_BG_COLOR_MAGENTA);
                    color = "b";
                }

                out.print(" ");
                if (piece != null){
                    if(Objects.equals(piece.getTeamColor().toString(), "BLACK")){
                        out.print(SET_TEXT_COLOR_BLACK);
                    }
                    else if (Objects.equals(piece.getTeamColor().toString(), "WHITE")){
                        out.print(SET_TEXT_COLOR_WHITE);
                    }
                    if(piece.getPieceType() == ChessPiece.PieceType.PAWN){
                        pieceLetter = "P";
                    }
                    else if(piece.getPieceType() == ChessPiece.PieceType.KING){
                        pieceLetter = "K";
                    }
                    else if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){
                        pieceLetter = "Q";
                    }
                    else if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){
                        pieceLetter = "B";
                    }
                    else if(piece.getPieceType() == ChessPiece.PieceType.ROOK){
                        pieceLetter = "R";
                    }
                    else if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){
                        pieceLetter = "N";
                    }

                }
                printPlayer(out, pieceLetter);

                out.print(" ");
        }
        setBlack(out);
        drawRowHeader(out,row);
        out.println();
    }
    private static void drawReverseRowOfSquares(PrintStream out, String color, int row, ChessBoard board) {
        if(Objects.equals(color, "b")) {
            //setBlack(out);
            out.print(SET_BG_COLOR_MAGENTA);
        }
        else{
            setWhite(out);
        }
        for (int boardCol = 7; boardCol >= 0 ; boardCol--) {
            String pieceLetter = " ";
            Position pos = new Position();
            pos.setColumn(boardCol);
            pos.setRow(row);
            ChessPiece piece;
            piece = board.getPiece(pos);
            if((Objects.equals(color, "b"))){
                out.print(SET_BG_COLOR_DARK_GREEN);
                color = "w";
            }
            else{
                //setBlack(out);
                out.print(SET_BG_COLOR_MAGENTA);
                color = "b";
            }

            out.print(" ");
            if (piece != null){
                if(Objects.equals(piece.getTeamColor().toString(), "BLACK")){
                    out.print(SET_TEXT_COLOR_BLACK);
                }
                else if (Objects.equals(piece.getTeamColor().toString(), "WHITE")){
                    out.print(SET_TEXT_COLOR_WHITE);
                }
                if(piece.getPieceType() == ChessPiece.PieceType.PAWN){
                    pieceLetter = "P";
                }
                else if(piece.getPieceType() == ChessPiece.PieceType.KING){
                    pieceLetter = "K";
                }
                else if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){
                    pieceLetter = "Q";
                }
                else if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){
                    pieceLetter = "B";
                }
                else if(piece.getPieceType() == ChessPiece.PieceType.ROOK){
                    pieceLetter = "R";
                }
                else if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){
                    pieceLetter = "N";
                }

            }
            printPlayer(out, pieceLetter);

            out.print(" ");
        }
        setBlack(out);
        drawRowHeader(out,row);
        out.println();
    }



    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        //out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        //out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void printPlayer(PrintStream out, String player) {
        //out.print(SET_BG_COLOR_WHITE);
        //out.print(SET_TEXT_COLOR_RED);

        out.print(player);

    }
}
