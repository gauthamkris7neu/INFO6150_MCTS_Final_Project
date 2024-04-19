package edu.neu.coe.info6205.mcts.connectfour;


import java.util.ArrayList;
import java.util.List;

public class Node {
    private Board state;
    private Node parent;
    private List<Node> children;
    private int visits;
    private double score;
    private int action;

    public Node(Board state) {
        this.state = state;
        this.children = new ArrayList<>();
        this.visits = 0;
        this.score = 0.0;
        MCTS.totalNodesCreated++;
    }

    public Board getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getVisits() {
        return visits;
    }

    public void incrementVisits() {
        this.visits++;
    }

    public double getScore() {
        return score;
    }

    public void addToScore(double score) {
        this.score += score;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public Node getChildWithMaxVisits() {
        Node maxChild = null;
        int maxVisits = Integer.MIN_VALUE;
        for (Node child : children) {
            if (child.getVisits() > maxVisits) {
                maxChild = child;
                maxVisits = child.getVisits();
            }
        }
        return maxChild;
    }

    public Node getChildWithMaxUCB() {
        Node selectedNode = null;
        double maxUCB = Double.MIN_VALUE;
        for (Node child : children) {
            double ucb = calculateUCB(child);
            if (ucb > maxUCB) {
                selectedNode = child;
                maxUCB = ucb;
            }
        }
        return selectedNode;
    }

    private double calculateUCB(Node node) {
        if (node.getVisits() == 0) {
            return Double.MAX_VALUE;
        }
        return (node.getScore() / node.getVisits()) + 1.41 * Math.sqrt(Math.log(visits) / node.getVisits());
    }
}
