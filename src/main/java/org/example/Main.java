package org.example;


import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Scanner input = new Scanner(System.in);

    static int NUMBER_OF_SIDES = 3;
    static final char H = 'H';
    static final char C = 'C';

    public static void main(String[] args) {
        System.out.println("Hello world!");
        char[][] gameBoard = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        //show start of the game board
        printBoard(gameBoard);
        boolean playerMove = true;  // simplify for human to start
        while (!winOrLoseOrDraw(gameBoard)) {
            if (playerMove) {
                playerMove(gameBoard);
            } else {
                computerMove(gameBoard);
            }
            playerMove = !playerMove;
            printBoard(gameBoard);
        }
    }

    private static void playerMove(char[][] gameBoard) {

        System.out.println("Please make a move.Column (1-" + NUMBER_OF_SIDES + ")");
        int moveColumn = input.nextInt();

        System.out.println("Please make a move.Raw (1-" + NUMBER_OF_SIDES + ")");
        int moveRaw = input.nextInt();


        while (!updateBoard(H, moveColumn, moveRaw, gameBoard)) {
            System.out.println("Sorry! Invalid Move. Try again");
            moveColumn = input.nextInt();
            moveRaw = input.nextInt();

        }
    }

    private static void computerMove(char[][] gameBoard) {
        Random rand = new Random();
        while (!updateBoard(C, rand.nextInt(NUMBER_OF_SIDES) + 1, rand.nextInt(NUMBER_OF_SIDES) + 1, gameBoard)) {
        }
    }

    private static boolean winOrLoseOrDraw(char[][] gameBoard) {
        return checkColumnsAndRawsAndDiagonalsForWin(gameBoard) || itNoMoreSpotsLeftNoWinners(gameBoard);
    }

    private static boolean checkColumnsAndRawsAndDiagonalsForWin(char[][] gameBoard) {
        for (int i = 0; i < NUMBER_OF_SIDES; i++) {
            if (checkForColumns(gameBoard, i)) return true;
            if (checkForRaws(gameBoard, i)) return true;
        }
        return checkDiagonalsForWin(gameBoard);
    }

    private static boolean checkDiagonalsForWin(char[][] gameBoard) {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_SIDES; i++) {
            charSet.add(gameBoard[i][i]);
        }
        if (analyseRawColDiagSetData(charSet, "DIAGONALS ")) return true;

        for (int i = NUMBER_OF_SIDES - 1; i >= 0; i--) {
            charSet.add(gameBoard[NUMBER_OF_SIDES - 1 - i][i]);
        }
        return analyseRawColDiagSetData(charSet, "DIAGONALS ");

    }

    private static boolean checkForRaws(char[][] gameBoard, int i) {
        Set<Character> charSet = new HashSet<>();

        for (int j = 0; j < NUMBER_OF_SIDES; j++) {
            charSet.add(gameBoard[j][i]);
        }
        return analyseRawColDiagSetData(charSet, "RAWS");
    }

    private static boolean checkForColumns(char[][] gameBoard, int i) {
        Set<Character> charSet = new HashSet<>();
        for (int j = 0; j < NUMBER_OF_SIDES; j++) {
            charSet.add(gameBoard[i][j]);
        }
        return analyseRawColDiagSetData(charSet, "COLUMNS");
    }

    private static boolean analyseRawColDiagSetData(Set<Character> charSet, String whathasWon) {
        if (charSet.size() == 1 && !charSet.contains('-')) {
            System.out.println("HURREY WE HAD A WINNER it is " + charSet.toString() + " COLUMNS " + whathasWon);
            return true;
        }
        charSet.clear();
        return false;
    }


    private static boolean itNoMoreSpotsLeftNoWinners(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                if (c == '-') return false;

            }
        }
        System.out.println("  -----  BOARD IS FULL  ----  ");
        return true;
    }

    private static boolean updateBoard(char type, int column, int raw, char[][] gameBoard) {

        System.out.println("move coming from " + type + " to column " + column + " to raw " + raw);
        if (column > 0 && column <= NUMBER_OF_SIDES && raw > 0 && raw <= NUMBER_OF_SIDES) {
            return putPlayerMoveOnBoard(gameBoard, column, raw, type);
        } else {
            System.out.println("wrong value");
            return false;
        }
    }

    private static boolean putPlayerMoveOnBoard(char[][] gameBoard, int column, int raw, char type) {
        if (gameBoard[column - 1][raw - 1] == '-') {
            gameBoard[column - 1][raw - 1] = type;
            return true;
        } else {
            System.out.println(" " + column + " " + raw + " is already busy please retry the move");
            return false;
        }
    }

    private static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            System.out.print("||");
            for (char c : row) {
                System.out.print(" " + c + " ");
            }
            System.out.print("||");
            System.out.println();
        }

    }
}
