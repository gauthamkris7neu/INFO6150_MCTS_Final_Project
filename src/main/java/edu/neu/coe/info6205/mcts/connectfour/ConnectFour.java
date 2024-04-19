package edu.neu.coe.info6205.mcts.connectfour;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConnectFour {
    private static Board board;
    static int player1Wins = 0;
    static int player2Wins = 0;
    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    static Random random = new Random();
    static MCTS mcts = new MCTS(); // Create an instance of MCTS

    static int totalGames = 100; // Example: Run 100 simulations
    static List<String[]> gameResults = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < totalGames; i++) {
            initializeGame();
            runGame();
            recordResult(i); // Record result after each game
        }
        writeResultsToCSV();
    }

    private static void recordResult(int gameIndex) {
        String[] result = new String[]{
                String.valueOf(gameIndex),
                String.valueOf(player1Wins),
                String.valueOf(player2Wins),
                String.valueOf(MCTS.totalNodesCreated / (gameIndex + 1)), // Average nodes per game
                String.valueOf(MCTS.averageDecisionTime / (gameIndex + 1)) // Average decision time per game
        };
        gameResults.add(result);
    }

    private static void writeResultsToCSV() {
        try (FileWriter csvWriter = new FileWriter("game_results.csv")) {
            csvWriter.append("Game No,Player1 Wins,Player2 Wins,Average Nodes,Average Time\n");
            for (String[] result : gameResults) {
                csvWriter.append(String.join(",", result));
                csvWriter.append("\n");
            }
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        int col;
        if (currentPlayer == player1) {
            col = mcts.search(board); // Use MCTS to find the best move
        } else {
            // If current player is player2, choose a random move
            col = mcts.search(board);
        }

        if (!board.addDisc(col, currentPlayer.getSymbol())) {
            System.out.println("Column is full, try again.");
            return false;
        }

        System.out.println("Player " + currentPlayer.getSymbol() + " added disc to column " + col);

        if (board.checkWin(currentPlayer.getSymbol())) {
            board.printBoard();
            if(currentPlayer.getSymbol() == 'X') {
                player1Wins++;
            } else {
                player2Wins++;
            }
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


    public static void initializeGame() {
        board = new Board();
        player1 = new Player('X');
        player2 = new Player('O');
        chooseStartingPlayer();
    }
}
