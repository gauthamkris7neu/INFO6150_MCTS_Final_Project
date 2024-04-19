package edu.neu.coe.info6205.mcts.tictactoe;


import java.util.Scanner;



import edu.neu.coe.info6205.mcts.tictactoe.MCTS.MCTSAnalysis;


import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select game mode:");
        System.out.println("1. MCTS vs. MCTS");
        System.out.println("2. Human vs. MCTS");
        int choice = scanner.nextInt();

        Board board = new Board();
        MCTSAnalysis mcts = new MCTSAnalysis(); // Using MCTSAnalysis class

        if (choice == 1) {
            // MCTS vs. MCTS
            runMctsVsMcts(board, mcts);
        } else if (choice == 2) {
            // Human vs. MCTS
            runHumanVsMcts(board, mcts, scanner);
        } else {
            System.out.println("Invalid choice");
        }

        mcts.saveDataToCSV("mcts_game_data.csv"); // Save the CSV file at the end of the game
        scanner.close();
    }

    private static void runMctsVsMcts(Board board, MCTSAnalysis mcts) {
        int currentPlayer = 1;
        while (board.checkStatus() == Board.IN_PROGRESS) {
            System.out.println("Current board:");
            board.printBoard();
            board = mcts.findNextMove(board, currentPlayer);
            System.out.println("Player " + currentPlayer + " (MCTS) has made a move:");
            currentPlayer = (currentPlayer == Board.P1) ? Board.P2 : Board.P1;
        }
        board.printStatus();
    }

    private static void runHumanVsMcts(Board board, MCTSAnalysis mcts, Scanner scanner) {
        int currentPlayer = 1;  // Let's assume the human is player 1
        while (board.checkStatus() == Board.IN_PROGRESS) {
            System.out.println("Current board:");
            board.printBoard();
            if (currentPlayer == 1) {
                // Human move
                System.out.println("Your move (row and column):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Position move = new Position(row, col);
                board.performMove(Board.P1, move);
            } else {
                // MCTS move
                System.out.println("MCTS is making a move...");
                board = mcts.findNextMove(board, Board.P2);
            }
            currentPlayer = (currentPlayer == Board.P1) ? Board.P2 : Board.P1;
        }
        board.printStatus();
    }
}