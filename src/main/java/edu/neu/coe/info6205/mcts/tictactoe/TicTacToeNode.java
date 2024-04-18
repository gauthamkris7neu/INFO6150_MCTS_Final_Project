package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TicTacToeNode implements Node {
    private static int totalNodes = 0;
    private Node parent;
    private List<Node> childNodes = new ArrayList<>();
    private List<int[]> untriedMoves;
    private double wins = 0;
    private double visits = 0;
    private int[] move;
    private static final double C = Math.sqrt(2);
    private int depth = 0;

    public TicTacToeNode(Game game) {
        totalNodes++;
        this.untriedMoves = new ArrayList<>(game.getAvailableMoves());
        this.parent = null;
        this.move = null;
        if(parent != null) {
            this.depth = parent.getDepth() + 1;
        }
    }

    public TicTacToeNode(Node parent, Game game, int[] move) {
        this(game);
        this.parent = parent;
        this.move = move;
        this.parent.getChildNodes().add(this);
        this.parent.getUntriedMoves().removeIf(m -> Arrays.equals(m, move));
        if(parent != null) {
            this.depth = parent.getDepth() + 1;
        }
    }

    @Override
    public Node addChild(Game state, int[] move) {
        Node child = new TicTacToeNode(this, state, move);
        this.childNodes.add(child);
        this.untriedMoves.removeIf(m -> Arrays.equals(m, move));
        return child;
    }

    @Override
    public Node getBestChild() {
        return childNodes.stream()
                .max(Comparator.comparingDouble(c -> c.getWins() / c.getVisits() + C * Math.sqrt(Math.log(this.visits) / c.getVisits())))
                .orElse(null);
    }

    @Override
    public List<Node> getChildNodes() {
        return this.childNodes;
    }

    @Override
    public List<int[]> getUntriedMoves() {
        return this.untriedMoves;
    }

    @Override
    public void removeMoveFromUntriedMoves(int[] move) {
        this.untriedMoves.removeIf(m -> Arrays.equals(m, move));
    }

    @Override
    public double getWins() {
        return this.wins;
    }

    @Override
    public double getVisits() {
        return this.visits;
    }

    @Override
    public int[] getMove() {
        return this.move;
    }

    @Override
    public void update(double result) {
        this.visits++;
        this.wins += result;
    }
    @Override
    public Node getParent() {
        return this.parent;  // Return the parent node
    }

    public static int getTotalNodes() {
        return totalNodes;
    }

    public static int getMaxDepth(Node node) {
        if (node == null) {
            return -1;  // Base case: empty node has depth -1
        }
        if (node instanceof TicTacToeNode) {
            TicTacToeNode tttNode = (TicTacToeNode) node;
            int maxDepth = tttNode.getDepth();  // Start with the current node's depth
            for (Node child : tttNode.getChildNodes()) {
                int childDepth = getMaxDepth(child);  // Recursively find the depth for each child
                if (childDepth > maxDepth) {
                    maxDepth = childDepth;  // Update if a deeper node is found
                }
            }
            return maxDepth;
        }
        return -1;  // Return -1 if the node isn't an instance of TicTacToeNode
    }

    @Override
    public int getDepth() {
        return this.depth;
    }
}
