package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.MonteCarloTreeSearch;
import edu.neu.coe.info6205.mcts.core.Node;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MCTSTest {

    @Test
    void testNodeAddChild() {
        Game game = new TicTacToe();
        Node root = Mockito.mock(Node.class);
        Node child = Mockito.mock(Node.class);

        List<int[]> initialMoves = new ArrayList<>();
        initialMoves.add(new int[]{0, 0});
        initialMoves.add(new int[]{0, 1});

        // Create a mutable list that reflects the current state
        List<int[]> currentUntriedMoves = new ArrayList<>(initialMoves);

        when(root.getUntriedMoves()).thenAnswer(invocation -> new ArrayList<>(currentUntriedMoves));
        when(root.addChild(any(Game.class), any(int[].class))).thenAnswer(invocation -> {
            int[] move = invocation.getArgument(1);
            currentUntriedMoves.removeIf(m -> Arrays.equals(m, move));
            return child;
        });

        int[] expectedMove = new int[]{0, 0};

        // Call the method under test
        Node actualChild = root.addChild(game, expectedMove);

        // Assertions
        verify(root).addChild(game, expectedMove);
        assertEquals(child, actualChild, "The returned child node should be the expected child instance.");

        // Verify that the move was actually removed
        assertEquals(1, root.getUntriedMoves().size(), "Should have one less untried move after addition.");
        assertFalse(root.getUntriedMoves().contains(expectedMove), "The list should not contain the added move.");
    }

    @Test
    void testNodeUpdate() {
        Node node = Mockito.mock(Node.class);
        Mockito.doNothing().when(node).update(anyDouble());
        node.update(1.0);
        Mockito.verify(node).update(1.0);
    }

    @Test
    void testFindNextMove() {
        Game game = new TicTacToe();
        MonteCarloTreeSearch mcts = new MCTS();
        int[] move = mcts.findNextMove(game);

        assertNotNull(move, "The move should not be null.");
        assertTrue(move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3, "The move should be within the board's bounds.");
    }
}
