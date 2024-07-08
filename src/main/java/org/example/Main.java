package org.example;


import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
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
            playermoveorcomputermove(gameBoard, playerMove);
            playerMove = !playerMove;
            printBoard(gameBoard);
        }
        printBoard(gameBoard);
    }

    private static void playermoveorcomputermove(char[][] gameBoard, boolean playerMove) {
        if (playerMove) {
            playerMove(gameBoard);
        } else {
            computerMove(gameBoard);
        }
    }

    private static void playerMove(char[][] gameBoard) {

        System.out.println("Please make a move. (1-9)");
        int move = input.nextInt();

        while(!updateBoard(H, move, gameBoard)){
            System.out.println("Sorry! Invalid Move. Try again");
            move = input.nextInt();
        }
    }

    private static void computerMove(char[][] gameBoard) {
        Random rand = new Random();
        while (!updateBoard(C, rand.nextInt(9) + 1, gameBoard)) {
//            System.out.println("computer moved to" + move);
        }
    }

    private static boolean winOrLoseOrDraw(char[][] gameBoard) {
        boolean wasThereAWin = true;
//                checkDiagonalsForWin(gameBoard)
//                && checkHorizontalsForWin(gameBoard)
//                && checkVerticalsForWin();
        return wasThereAWin || itIsADrawNoMoreSpotsLeftNoWinenrs(gameBoard);
//        add game over logic and return true
//        for (char[] row : gameBoard) {
//            for (char c : row) {
//                if (c == '-') return false;
//
//            }
//        }
//
//        // lets define if there is a winner
//
//        if (((gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[0][0] == gameBoard[2][2])) ||
//                ((gameBoard[0][0] == gameBoard[1][0]) && (gameBoard[0][0] == gameBoard[2][0])) ||
//                ((gameBoard[0][0] == gameBoard[0][1]) && (gameBoard[0][0] == gameBoard[0][2])) ||
//                ((gameBoard[1][0] == gameBoard[1][1]) && (gameBoard[1][0] == gameBoard[1][2])) ||
//                ((gameBoard[2][0] == gameBoard[1][1]) && (gameBoard[2][0] == gameBoard[0][2])) ||
//                ((gameBoard[2][0] == gameBoard[2][1]) && (gameBoard[2][0] == gameBoard[2][2])) ||
//                ((gameBoard[0][1] == gameBoard[1][1]) && (gameBoard[0][1] == gameBoard[2][1])) ||
//                ((gameBoard[0][2] == gameBoard[1][2]) && (gameBoard[0][2] == gameBoard[2][2]))
//        ) {
//            System.out.println("HURREY WE HAVE A WINNER");
//        }
//        return true;
    }

    private static boolean itIsADrawNoMoreSpotsLeftNoWinenrs(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                if (c == '-') return false;

            }
        }
        return true;
    }

    private static boolean updateBoard(char type, int position, char[][] gameBoard) {

        System.out.println("move coming from " + type + " to position " + position);

        return switch (position) {
            case 1:
                yield putPlayerMoveOnBoard(gameBoard, 0,0, type);
            case 2:
                yield putPlayerMoveOnBoard(gameBoard, 1,0, type);
            case 3:
                yield putPlayerMoveOnBoard(gameBoard, 2,0, type);
            case 4:
                yield putPlayerMoveOnBoard(gameBoard, 0,1, type);
            case 5:
                yield putPlayerMoveOnBoard(gameBoard, 1,1, type);
            case 6:
                yield putPlayerMoveOnBoard(gameBoard, 2,1, type);
            case 7:
                yield putPlayerMoveOnBoard(gameBoard, 0,2, type);
            case 8:
                yield putPlayerMoveOnBoard(gameBoard, 1,2, type);
            case 9:
                yield putPlayerMoveOnBoard(gameBoard, 2,2, type);
            default:
                System.out.println("wrong value");
                yield false;
        };

    }

    private static boolean putPlayerMoveOnBoard(char[][] gameBoard, int y, int x, char type) {
        if (gameBoard[y][x] == '-') {
            gameBoard[y][x] = type;
            return true;
        } else {
            System.out.println(" " + y + " " + x + " is already busy please retry the move" );
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
