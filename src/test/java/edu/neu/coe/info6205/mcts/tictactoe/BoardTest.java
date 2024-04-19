package edu.neu.coe.info6205.mcts.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import edu.neu.coe.info6205.mcts.tictactoe.Board;
import edu.neu.coe.info6205.mcts.tictactoe.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testPerformMove() {
        Board board = new Board();
        Position position = new Position(0, 0);
        board.performMove(Board.P1, position);
        assertEquals(Board.P1, board.getBoardValues()[0][0]);
    }

    @Test
    public void testCheckStatusInProgress() {
        Board board = new Board(new int[][]{{Board.P1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertEquals(Board.IN_PROGRESS, board.checkStatus());
    }

    @Test
    public void testCheckStatusDraw() {
        Board board = new Board(new int[][]{{Board.P1, Board.P2, Board.P1}, {Board.P1, Board.P2, Board.P2}, {Board.P2, Board.P1, Board.P1}});
        assertEquals(Board.DRAW, board.checkStatus());
    }

    @Test
    public void testCheckStatusPlayer1Wins() {
        Board board = new Board(new int[][]{{Board.P1, Board.P1, Board.P1}, {Board.P2, Board.P2, 0}, {0, 0, 0}});
        assertEquals(Board.P1, board.checkStatus());
    }

    @Test
    public void testCheckStatusPlayer2Wins() {
        Board board = new Board(new int[][]{{Board.P2, Board.P1, Board.P1}, {Board.P2, Board.P2, Board.P2}, {0, 0, 0}});
        assertEquals(Board.P2, board.checkStatus());
    }


}
