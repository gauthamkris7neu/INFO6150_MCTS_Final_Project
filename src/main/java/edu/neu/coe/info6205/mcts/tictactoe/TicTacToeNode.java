package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TicTacToeNode implements Node {

    private Node parent;
    private List<Node> childNodes = new ArrayList<>();
    private List<int[]> untriedMoves;
    private double wins = 0;
    private double visits = 0;
    private int[] move;
    private static final double C = Math.sqrt(2);

    public TicTacToeNode(Game game) {
        this.untriedMoves = new ArrayList<>(game.getAvailableMoves());
        this.parent = null;
        this.move = null;
    }

    public TicTacToeNode(Node parent, Game game, int[] move) {
        this(game);
        this.parent = parent;
        this.move = move;
        this.parent.getChildNodes().add(this);
        this.parent.getUntriedMoves().removeIf(m -> Arrays.equals(m, move));
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
}
