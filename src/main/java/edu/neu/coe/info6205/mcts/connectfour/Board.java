package edu.neu.coe.info6205.mcts.connectfour;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int ROWS = 6;
    private final int COLS = 7;
    private char[][] board;

    public Board() {
        board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '.';
            }
        }
    }

    public Board(char[][] board) {
        this.board = board;
    }

    public Board copy() {
        char[][] newBoard = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return new Board(newBoard);
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean addDisc(int col, char player) {
        if (col < 0 || col >= COLS) {
            return false;
        }
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == '.') {
                board[i][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row][col + 1] == player
                        && board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical

        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == player && board[row + 1][col] == player
                        && board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (bottom left to top right)

        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row - 1][col + 1] == player
                        && board[row - 2][col + 2] == player && board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top left to bottom right)

        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player
                        && board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }
        return (countX == countO) ? 'X' : 'O';
    }

    public List<Integer> getPossibleActions() {
        List<Integer> actions = new ArrayList<>();
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == '.') {
                actions.add(col);
            }
        }
        return actions;
    }

    public boolean isTerminal() {
        return checkWin('X') || checkWin('O') || isFull();
    }
}
