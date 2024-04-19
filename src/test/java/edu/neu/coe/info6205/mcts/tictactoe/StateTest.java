package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.tictactoe.Board;
import edu.neu.coe.info6205.mcts.tictactoe.MCTS.State;
import edu.neu.coe.info6205.mcts.tictactoe.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StateTest {


    @Test
    public void testSetBoard() {
        Board board = new Board();
        State state = new State();
        state.setBoard(board);
        assertEquals(board, state.getBoard());
    }

    @Test
    public void testGetPlayerNo() {
        State state = new State();
        state.setPlayerNo(1);
        assertEquals(1, state.getPlayerNo());
    }

    @Test
    public void testSetPlayerNo() {
        State state = new State();
        state.setPlayerNo(2);
        assertEquals(2, state.getPlayerNo());
    }

    @Test
    public void testGetOpponent() {
        State state = new State();
        state.setPlayerNo(1);
        assertEquals(2, state.getOpponent());
    }

    @Test
    public void testGetVisitCount() {
        State state = new State();
        state.setVisitCount(5);
        assertEquals(5, state.getVisitCount());
    }

    @Test
    public void testSetVisitCount() {
        State state = new State();
        state.setVisitCount(10);
        assertEquals(10, state.getVisitCount());
    }

    @Test
    public void testGetWinScore() {
        State state = new State();
        state.setWinScore(3.14);
        assertEquals(3.14, state.getWinScore());
    }

    @Test
    public void testSetWinScore() {
        State state = new State();
        state.setWinScore(2.71);
        assertEquals(2.71, state.getWinScore());
    }

    @Test
    public void testGetAllPossibleStates() {
        Board board = new Board();
        State state = new State(board);
        List<State> possibleStates = state.getAllPossibleStates();
        // Assuming an empty board has 9 empty positions
        assertEquals(9, possibleStates.size());
    }

    @Test
    public void testIncrementVisit() {
        State state = new State();
        state.setVisitCount(5);
        state.incrementVisit();
        assertEquals(6, state.getVisitCount());
    }

    @Test
    public void testAddScore() {
        State state = new State();
        state.setWinScore(3.0);
        state.addScore(1.5);
        assertEquals(4.5, state.getWinScore());
    }
    

    @Test
    public void testTogglePlayer() {
        State state = new State();
        state.setPlayerNo(1);
        state.togglePlayer();
        assertEquals(2, state.getPlayerNo());
    }


}

