package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.tictactoe.Tree.Node;
import edu.neu.coe.info6205.mcts.tictactoe.Tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void testTreeInitialization() {
        Tree tree = new Tree();
        assertNotNull(tree.getRoot());
        assertNotNull(tree.getRoot().getState());
        assertNotNull(tree.getRoot().getChildArray());
        assertEquals(0, tree.getRoot().getChildArray().size());
    }

    @Test
    public void testTreeSetRoot() {
        Node node = new Node();
        Tree tree = new Tree();
        tree.setRoot(node);
        assertEquals(node, tree.getRoot());
    }

    @Test
    public void testTreeAddChild() {
        Node parent = new Node();
        Node child = new Node();
        Tree tree = new Tree(parent);
        tree.addChild(parent, child);
        assertEquals(1, parent.getChildArray().size());
        assertEquals(child, parent.getChildArray().get(0));
    }
}
