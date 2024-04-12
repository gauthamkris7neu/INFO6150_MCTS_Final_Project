package edu.neu.coe.info6205.mcts.connectfour;

import java.util.Random;

public class ConnectFour {
    private static Board board;
    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    static Random random = new Random();

    public static void main(String[] args) {
        intializeGame();
        runGame();
    }

    static void intializeGame() {
        board = new Board();
        player1 = new Player('X');
        player2 = new Player('O');
        chooseStartingPlayer();
    }

    private static void chooseStartingPlayer() {
        if (random.nextBoolean()) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        System.out.println("Player " + currentPlayer.getSymbol() + " starts the game");
    }

    static void runGame() {
        boolean gameEnded = false;
        while (!gameEnded) {
            gameEnded = processTurn();
        }
    }

    private static boolean processTurn() {
        board.printBoard();
        int col = getRandomColumn();
        if (!board.addDisc(col, currentPlayer.getSymbol())) {
            System.out.println("Column is full, try again.");
            return false;
        }

        System.out.println("Player " + currentPlayer.getSymbol() + " added disc to column " + col);

        if (board.checkWin(currentPlayer.getSymbol())) {
            board.printBoard();
            System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
            return true;
        } else if (board.isFull()) {
            board.printBoard();
            System.out.println("It's a draw!");
            return true;
        } else {
            switchPlayer();
            return false;
        }
    }

    private static int getRandomColumn() {
        return random.nextInt(7);
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
