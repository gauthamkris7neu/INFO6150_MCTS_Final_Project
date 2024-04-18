package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe implements Game {
    char[][] board;
    private char currentPlayer;
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int BOARD_SIZE = 3;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        currentPlayer = PLAYER_X;
    }

    public TicTacToe(TicTacToe game) {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        copyBoard(game.board);
        currentPlayer = game.currentPlayer;
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void copyBoard(char[][] originalBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = originalBoard[i][j];
            }
        }
    }

    public List<int[]> getAvailableMoves() {
        List<int[]> availableMoves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }
        return availableMoves;
    }

    public boolean isGameOver() {
        return isBoardFull() || checkForWin(PLAYER_X) || checkForWin(PLAYER_O);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkForWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public void makeMove(int[] move) {
        int row = move[0];
        int col = move[1];
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
    }

    @Override
    public TicTacToe clone() {
        return new TicTacToe(this);
    }

    public int getWinner() {
        if (checkForWin(PLAYER_X)) {
            return 1; // Player X wins
        } else if (checkForWin(PLAYER_O)) {
            return -1; // Player O wins
        } else {
            return 0; // Draw
        }
    }

    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        MCTS mcts = new MCTS();

        while (!game.isGameOver()) {
            int[] move = mcts.findNextMove(game);
            game.makeMove(move);
            game.displayBoard();
        }

        int winner = game.getWinner();
        if (winner == 1) {
            System.out.println("Player X wins!");
        } else if (winner == -1) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}