package ui;

import Models.GameModel;
import chess.Board;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

import static ui.EscapeSequences.*;
public class Chess {
    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final String P = " P ";
    private static final String K = " K ";
    private static final String Q = " Q ";
    private static final String R = " R ";
    private static final String N = " N ";
    private static final String B = " B ";


    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        //drawHeaders(out);

       drawChessBoard(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);

    }
    private static void drawChessBoard(PrintStream out) {
        Board gameBoard = new Board();
        gameBoard.

        drawHeaders(out);
        String color = "w";
        for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {
            if(color.equals("b")){
                color = "w";
            }
            else{
                color = "b";
            }
            drawRowHeader(out, boardRow);
            drawRowOfSquares(out, color, boardRow);
        }
        drawHeaders(out);
    }
    private static void drawColumnHeaders(PrintStream out) {
        // Draw an empty space for the top-left corner
        setBlack(out);
        out.print("     ");

        // Draw column headers (letters)
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, String.valueOf((char) ('a' + boardCol)));
        }

        out.println(); // Move to the next line for the rows
    }

    private static void drawRowHeader(PrintStream out, int rowNumber) {
        // Draw row header (number)
        drawHeader(out, String.valueOf(rowNumber));


    }
    private static void drawHeaders(PrintStream out) {
        // Draw column headers
        drawColumnHeaders(out);

        out.println(); // Ensure a newline after the last row
    }

    private static void drawHeader(PrintStream out, String headerText) {
        out.print("  ");
        printHeaderText(out, headerText);
        out.print("  ");
    }
    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_GREEN);

        out.print(player);

        setBlack(out);
    }

    private static void drawRowOfSquares(PrintStream out, String color, int row, GameModel game) {
        if(Objects.equals(color, "b")) {
            setBlack(out);
        }
        else{
            setWhite(out);
        }
        for (int boardCol = 0; boardCol <= BOARD_SIZE_IN_SQUARES ; boardCol++) {
            if (boardCol != 0) {
                if((Objects.equals(color, "b"))){
                    setWhite(out);
                    color = "w";
                }
                else{
                    setBlack(out);
                    color = "b";
                }

                out.print(" ");
                if()
                printPlayer(out, P);

                out.print(" ");
            }
        }
        setBlack(out);
        drawRowHeader(out,row);
        out.println();
    }



    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setRed(PrintStream out) {
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_RED);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void printPlayer(PrintStream out, String player) {
        //out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_RED);

        out.print(player);

    }
}
