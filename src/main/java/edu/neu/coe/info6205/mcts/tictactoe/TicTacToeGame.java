package edu.neu.coe.info6205.mcts.tictactoe;


import edu.neu.coe.info6205.mcts.tictactoe.MCTS.MonteCarloTreeSearch;

public class TicTacToeGame {
    public static void main(String[] args) {
        Board board = new Board();  // Assuming the default constructor sets up a new game
        MonteCarloTreeSearch mctsPlayer1 = new MonteCarloTreeSearch();
        MonteCarloTreeSearch mctsPlayer2 = new MonteCarloTreeSearch();

        // Player 1 starts
        int currentPlayer = 1;

        // Run the game loop until the game is over
        while (board.checkStatus() == Board.IN_PROGRESS) {
            if (currentPlayer == 1) {
                board = mctsPlayer1.findNextMove(board, currentPlayer); // MCTS makes the move directly on the board
                System.out.println("Player 1 (MCTS) has made a move:");
            } else {
                board = mctsPlayer2.findNextMove(board, currentPlayer); // MCTS makes the move directly on the board
                System.out.println("Player 2 (MCTS) has made a move:");
            }

            board.printBoard(); // Print the board after each move
            currentPlayer = (currentPlayer == 1) ? 2 : 1; // Switch players
        }

        // Print the final status of the game
        board.printStatus();
    }
}
