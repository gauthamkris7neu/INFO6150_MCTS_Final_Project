package edu.neu.coe.info6205.mcts.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MCTSTest {

    @Test
    void testFindNextMove() {
        TicTacToe game = new TicTacToe();
        MCTS mcts = new MCTS();

        int[] move = mcts.findNextMove(game);
        assertNotNull(move);
        assertTrue(move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3);
    }

    @Test
    void testNodeAddChild() {
        TicTacToe game = new TicTacToe();
        MCTS.Node root = new MCTS.Node(game);  // Root node initializes with all moves untried

        // Explicitly define a move to ensure control over the test
        int[] expectedMove = new int[]{0, 0};

        // Simulate the move being made in a game state to pass to the child
        TicTacToe childGameState = new TicTacToe(game);
        childGameState.makeMove(expectedMove);

        // Create the child node with this move
        root.untriedMoves.removeIf(m -> Arrays.equals(m, expectedMove)); // Manually remove the move
        MCTS.Node child = new MCTS.Node(root, childGameState, expectedMove);

        root.childNodes.add(child); // Manually add the child to root's child nodes

        assertNotNull(child, "Child node should not be null");
        assertNotNull(child.untriedMoves, "Child should have initialized untried moves");
        assertEquals(8, root.untriedMoves.size(), "One less move should be available in root after child addition");
        assertEquals(0.0, child.wins, "Initial wins should be 0");
        assertEquals(0.0, child.visits, "Initial visits should be 0");
        assertEquals(root, child.parent, "The parent of the child should be the root");
        assertTrue(root.childNodes.contains(child), "Root should contain the new child");
        assertArrayEquals(expectedMove, child.move, "The move associated with the child should match the expected move");
    }







    @Test
    void testNode() {
        TicTacToe game = new TicTacToe();
        MCTS.Node root = new MCTS.Node(game);  // Root node

        assertNotNull(root);
        assertNotNull(root.untriedMoves);
        assertEquals(9, root.untriedMoves.size());
        assertEquals(0.0, root.wins);
        assertEquals(0.0, root.visits);
        assertNull(root.parent);
        assertNotNull(root.childNodes);
        assertTrue(root.childNodes.isEmpty());

        int[] move = new int[]{0, 0};
        MCTS.Node child = new MCTS.Node(root, game, move);  // Proper child node with a parent

        assertNotNull(child);
        assertNotNull(child.untriedMoves);
        assertEquals(8, child.untriedMoves.size());
        assertEquals(0.0, child.wins);
        assertEquals(0.0, child.visits);
        assertEquals(root, child.parent);  // Ensure parent is correctly set
        assertNotNull(child.childNodes);
        assertTrue(child.childNodes.isEmpty());
        assertArrayEquals(move, child.move);
    }


    @Test
    void testNodeGetBestChild() {
        TicTacToe game = new TicTacToe();
        MCTS.Node parent = new MCTS.Node(game);
        TicTacToe childState1 = new TicTacToe(game);
        TicTacToe childState2 = new TicTacToe(game);
        int[] move1 = new int[]{0, 0};
        int[] move2 = new int[]{0, 1};

        MCTS.Node child1 = new MCTS.Node(parent, childState1, move1);
        MCTS.Node child2 = new MCTS.Node(parent, childState2, move2);

        parent.childNodes.add(child1);
        parent.childNodes.add(child2);

        child1.visits = 10;
        child1.wins = 5;
        child2.visits = 5;
        child2.wins = 2;

        MCTS.Node bestChild = parent.getBestChild();

        assertNotNull(bestChild);
        assertEquals(child1, bestChild);
    }

    @Test
    void testNodeUpdate() {
        TicTacToe game = new TicTacToe();
        MCTS.Node node = new MCTS.Node(game);

        node.update(1);
        assertEquals(1.0, node.wins);
        assertEquals(1.0, node.visits);

        node.update(-1);
        assertEquals(0.0, node.wins);
        assertEquals(2.0, node.visits);
    }
}
