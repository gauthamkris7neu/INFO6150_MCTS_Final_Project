package edu.neu.coe.info6205.mcts.connectfour;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testAddDisc() {
        assertTrue(board.addDisc(0, 'X'));
        assertTrue(board.addDisc(0, 'O'));
    }

    @Test
    public void testAddDiscToFullColumn() {
        for (int i = 0; i < 6; i++) {
            board.addDisc(0, 'X');
        }
        assertFalse(board.addDisc(0, 'X'));
    }

    @Test
    public void testIsFull() {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board.addDisc(col, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    public void testCheckHorizontalWin() {
        board.addDisc(0, 'X');
        board.addDisc(1, 'X');
        board.addDisc(2, 'X');
        board.addDisc(3, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckVerticalWin() {
        for (int i = 0; i < 4; i++) {
            board.addDisc(0, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testCheckDiagonalWin() {
        board.addDisc(0, 'X'); // Player X
        board.addDisc(1, 'O'); // Player O
        board.addDisc(1, 'X'); // Player X
        board.addDisc(2, 'O'); // Player O
        board.addDisc(2, 'X'); // Player X
        board.addDisc(3, 'O'); // Player O
        board.addDisc(2, 'X'); // Player X
        board.addDisc(3, 'X'); // Player X
        board.addDisc(3, 'O'); // Player O
        board.addDisc(3, 'X'); // Player X
        assertTrue(board.checkWin('X'));
    }
}
