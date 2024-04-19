package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.tictactoe.MCTS.State;
import edu.neu.coe.info6205.mcts.tictactoe.Tree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    public void testNodeInitialization() {
        Node node = new Node();
        assertNotNull(node.getState());
        assertNotNull(node.getChildArray());
        assertEquals(0, node.getChildArray().size());
    }

}
