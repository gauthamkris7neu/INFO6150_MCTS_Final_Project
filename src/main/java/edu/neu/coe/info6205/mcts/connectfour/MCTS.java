package edu.neu.coe.info6205.mcts.connectfour;

import java.util.List;
import java.util.Random;

public class MCTS {
    private static final int MAX_ITERATIONS = 1000;

    private static final double C = Math.sqrt(2); // Exploration constant
    Random random;

    public MCTS() {
        random = new Random();
    }

    public int search(Board board) {
        Node rootNode = new Node(board.copy()); // Create a copy of the current board state as the root node
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            Node selectedNode = select(rootNode);
            if (selectedNode == null) {
                // Handle the case where no suitable node is found
                break;
            }
            Node expandedNode = expand(selectedNode);
            int simulationResult = simulate(expandedNode);
            backpropagate(expandedNode, simulationResult);
        }
        // Choose the best move based on the most visited child
        return rootNode.getChildWithMaxVisits().getAction();
    }

    Node select(Node node) {
        while (node != null && !node.isLeaf()) {
            node = node.getChildWithMaxUCB();
        }
        return node;
    }

    Node expand(Node node) {
        List<Integer> possibleActions = node.getState().getPossibleActions();
        if (possibleActions.isEmpty()) {
            // If there are no possible actions, return the node itself
            return node;
        }
        int randomActionIndex = random.nextInt(possibleActions.size());
        int action = possibleActions.get(randomActionIndex);
        Board nextState = node.getState().copy();
        nextState.addDisc(action, nextState.getCurrentPlayer());
        Node childNode = new Node(nextState);
        childNode.setAction(action);
        node.addChild(childNode);
        return childNode;
    }

    int simulate(Node node) {
        Board board = node.getState();
        char currentPlayer = board.getCurrentPlayer();
        while (!board.isTerminal()) {
            List<Integer> possibleActions = board.getPossibleActions();
            int randomActionIndex = random.nextInt(possibleActions.size());
            int action = possibleActions.get(randomActionIndex);
            board.addDisc(action, currentPlayer);
            currentPlayer = board.getCurrentPlayer();
        }
        if (board.checkWin(node.getState().getCurrentPlayer())) {
            return 1; // Win
        } else {
            return 0; // Draw
        }
    }

    void backpropagate(Node node, int result) {
        while (node != null) {
            node.incrementVisits();
            node.addToScore(result);
            node = node.getParent();
        }
    }

    public static void main(String[] args) {
        // Example usage
        MCTS mcts = new MCTS();
        Board board = new Board();
        int bestMove = mcts.search(board);
        System.out.println("Best move: " + bestMove);
    }
}
