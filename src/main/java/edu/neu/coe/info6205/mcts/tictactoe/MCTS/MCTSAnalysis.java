package edu.neu.coe.info6205.mcts.tictactoe.MCTS;


import edu.neu.coe.info6205.mcts.tictactoe.Board;
import edu.neu.coe.info6205.mcts.tictactoe.Tree.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MCTSAnalysis extends MonteCarloTreeSearch {
    private List<String[]> logData;
    private long phaseStartTime;

    public MCTSAnalysis() {
        super();
        this.logData = new ArrayList<>();
        this.logData.add(new String[]{"Phase", "Duration (ms)", "NodeCount", "Tree Depth", "Tree Breadth", "Total Simulations", "Average Time Per Node (ms)"});
    }

    private void startPhase() {
        phaseStartTime = System.currentTimeMillis();
    }

    private void logPhase(String phase, Node rootNode) {
        long duration = System.currentTimeMillis() - phaseStartTime;
        int treeDepth = calculateTreeDepth(rootNode);
        int treeBreadth = calculateTreeBreadth(rootNode);
        int totalSimulations = calculateTotalSimulations(rootNode);
        double avgTimePerNode = rootNode.getChildArray().isEmpty() ? 0 : (double) duration / rootNode.getChildArray().size();
        logData.add(new String[]{
                phase,
                String.valueOf(duration),
                String.valueOf(rootNode.getChildArray().size()),
                String.valueOf(treeDepth),
                String.valueOf(treeBreadth),
                String.valueOf(totalSimulations),
                String.format("%.2f", avgTimePerNode)
        });
    }

    @Override
    public Board findNextMove(Board board, int playerNo) {
        startPhase();
        Board result = super.findNextMove(board, playerNo);
        logPhase("findNextMove", getRootNode());
        return result;
    }

    @Override
    protected Node selectPromisingNode(Node rootNode) {
        startPhase();
        Node result = super.selectPromisingNode(rootNode);
        logPhase("selectPromisingNode", rootNode);
        return result;
    }

    @Override
    protected void expandNode(Node node) {
        startPhase();
        super.expandNode(node);
        logPhase("expandNode", node);
    }

    @Override
    protected void backPropogation(Node nodeToExplore, int playoutResult) {
        startPhase();
        super.backPropogation(nodeToExplore, playoutResult);
        logPhase("backPropogation", nodeToExplore);
    }

    private int calculateTreeDepth(Node node) {
        if (node == null) return 0;
        int maxDepth = 0;
        for (Node child : node.getChildArray()) {
            maxDepth = Math.max(maxDepth, calculateTreeDepth(child));
        }
        return 1 + maxDepth;
    }

    private int calculateTreeBreadth(Node node) {
        return node == null ? 0 : node.getChildArray().size();
    }

    private int calculateTotalSimulations(Node node) {
        if (node == null) return 0;
        int total = node.getState().getVisitCount();
        for (Node child : node.getChildArray()) {
            total += calculateTotalSimulations(child);
        }
        return total;
    }

    public void saveDataToCSV(String filename) {
        try (FileWriter csvWriter = new FileWriter(filename)) {
            for (String[] data : logData) {
                csvWriter.append(String.join(",", data));
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
