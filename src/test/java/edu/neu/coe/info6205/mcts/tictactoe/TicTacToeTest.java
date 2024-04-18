package edu.neu.coe.info6205.mcts.tictactoe;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    @Test
    void testMakeMove() {
        TicTacToe game = new TicTacToe();
        game.makeMove(new int[]{0, 0});
        assertEquals('X', game.board[0][0]);
        game.makeMove(new int[]{1, 1});
        assertEquals('O', game.board[1][1]);
    }

    @Test
    void testIsGameOver() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.isGameOver());
        game.makeMove(new int[]{0, 0});
        game.makeMove(new int[]{1, 1});
        game.makeMove(new int[]{0, 1});
        game.makeMove(new int[]{1, 0});
        assertFalse(game.isGameOver());
        game.makeMove(new int[]{0, 2});
        assertTrue(game.isGameOver());
        assertEquals(1, game.getWinner());
    }

    @Test
    void testGetAvailableMoves() {
        TicTacToe game = new TicTacToe();
        List<int[]> availableMoves = game.getAvailableMoves();
        assertEquals(9, availableMoves.size());
        game.makeMove(new int[]{0, 0});
        game.makeMove(new int[]{1, 1});
        availableMoves = game.getAvailableMoves();
        assertEquals(7, availableMoves.size());
    }

    @Test
    void testGetWinner() {
        TicTacToe game = new TicTacToe();
        assertEquals(0, game.getWinner());
        game.makeMove(new int[]{0, 0});
        game.makeMove(new int[]{1, 1});
        game.makeMove(new int[]{0, 1});
        game.makeMove(new int[]{1, 0});
        game.makeMove(new int[]{0, 2});
        assertEquals(1, game.getWinner());
    }
}
