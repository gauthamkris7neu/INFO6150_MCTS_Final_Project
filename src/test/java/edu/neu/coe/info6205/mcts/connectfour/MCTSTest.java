package edu.neu.coe.info6205.mcts.connectfour;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MCTSTest {

    private MCTS mcts;
    private Board board;
    private Node rootNode;
    private Random mockRandom;

    @BeforeEach
    void setup() {
        mcts = new MCTS();
        board = mock(Board.class);
        rootNode = new Node(board);
        mockRandom = mock(Random.class);
        mcts.random = mockRandom; // Inject the mock random into the MCTS instance
    }

    @Test
    void testSelectWithLeafNode() {
        when(rootNode.isLeaf()).thenReturn(true);
        Node selected = mcts.select(rootNode);
        assertEquals(rootNode, selected);
    }

    @Test
    void testExpandWithNoPossibleActions() {
        when(board.getPossibleActions()).thenReturn(Arrays.asList());
        Node expanded = mcts.expand(rootNode);
        assertEquals(rootNode, expanded);
    }

    @Test
    void testExpandWithPossibleActions() {
        when(board.getPossibleActions()).thenReturn(Arrays.asList(1, 2, 3));
        when(mockRandom.nextInt(anyInt())).thenReturn(1); // Choose the second action
        when(board.copy()).thenReturn(board);
        mcts.expand(rootNode);

        verify(board).addDisc(2, board.getCurrentPlayer());
    }

    @Test
    void testSimulateWin() {
        when(board.isTerminal()).thenReturn(true);
        when(board.checkWin(anyChar())).thenReturn(true);
        int result = mcts.simulate(rootNode);
        assertEquals(1, result);
    }

    @Test
    void testBackpropagate() {
        Node childNode = new Node(board);
        rootNode.addChild(childNode);
        childNode.setParent(rootNode);
        mcts.backpropagate(childNode, 1);

        assertEquals(1, rootNode.getVisits());
        assertEquals(1, rootNode.getScore());
    }
    
}

