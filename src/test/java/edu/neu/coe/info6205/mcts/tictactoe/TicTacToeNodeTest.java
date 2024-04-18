package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeNodeTest {
    private Game game;
    private TicTacToeNode node;
    private int[] move = {1, 1};  // A typical move

    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        when(game.getAvailableMoves()).thenReturn(new ArrayList<>(List.of(new int[]{0, 0}, new int[]{1, 1}, new int[]{2, 2})));
        node = new TicTacToeNode(game);
    }

    @Test
    public void testInitialization() {
        List<int[]> untriedMoves = node.getUntriedMoves();
        assertNotNull(untriedMoves);
        assertTrue(containsMove(untriedMoves, new int[]{1, 1}), "Should contain the move {1, 1}");
        assertNull(node.getParent());
        assertEquals(0, node.getWins());
        assertEquals(0, node.getVisits());
    }

    private boolean containsMove(List<int[]> moves, int[] target) {
        return moves.stream().anyMatch(m -> Arrays.equals(m, target));
    }

    @Test
    public void testAddChild() {
        Node child = node.addChild(game, move);
        assertNotNull(child);
        assertEquals(2, node.getUntriedMoves().size());  // One move should be removed
        assertFalse(node.getUntriedMoves().contains(move));
        assertTrue(node.getChildNodes().contains(child));
    }

    @Test
    public void testGetBestChild() {
        Node child1 = node.addChild(game, new int[]{0, 0});
        Node child2 = node.addChild(game, new int[]{1, 1});
        Node child3 = node.addChild(game, new int[]{2, 2});

        child1.update(10);  // Simulate a win scenario
        child1.update(10);
        child2.update(5);
        child3.update(3);

        Node bestChild = node.getBestChild();
        assertEquals(child1, bestChild);
    }

    @Test
    public void testUpdate() {
        node.update(1);
        assertEquals(1, node.getVisits());
        assertEquals(1, node.getWins());

        node.update(-1);
        assertEquals(2, node.getVisits());
        assertEquals(0, node.getWins());  // Assuming win calculation might differ
    }

    @Test
    public void testRemoveMoveFromUntriedMoves() {
        node.removeMoveFromUntriedMoves(move);
        assertFalse(node.getUntriedMoves().contains(move));
    }

    @Test
    public void testParentNode() {
        Node parent = new TicTacToeNode(game);
        TicTacToeNode child = new TicTacToeNode(parent, game, move);
        assertEquals(parent, child.getParent());
    }
}
