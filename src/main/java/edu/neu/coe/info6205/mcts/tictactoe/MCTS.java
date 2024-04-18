package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.MonteCarloTreeSearch;
import edu.neu.coe.info6205.mcts.core.Node;

import java.util.*;

public class MCTS implements MonteCarloTreeSearch {
    private static final int SIMULATIONS = 10000;
    MetricsCollector collector = new MetricsCollector();
    private static final double C = Math.sqrt(2);
    private final Random random = new Random();


    @Override
    public int[] findNextMove(Game game) {
        long startTime = System.currentTimeMillis();
        Node root = new TicTacToeNode(game);


        for (int i = 0; i < SIMULATIONS; i++) {
            Node node = root;
            Game state = game.clone();

            // Selection phase
            while (!node.getUntriedMoves().isEmpty() && !node.getChildNodes().isEmpty()) {
                node = node.getBestChild();
                state.makeMove(node.getMove());
            }

            // Expansion phase
            if (!node.getUntriedMoves().isEmpty()) {
                int randomMove = random.nextInt(node.getUntriedMoves().size());
                int[] move = node.getUntriedMoves().get(randomMove);
                node.getUntriedMoves().remove(move);
                state.makeMove(move);
                node = node.addChild(state, move);
            }

            // Simulation phase
            while (!state.isGameOver()) {
                List<int[]> availableMoves = state.getAvailableMoves();
                int randomMove = random.nextInt(availableMoves.size());
                state.makeMove(availableMoves.get(randomMove));
            }

            // Backpropagation phase
            while (node != null) {
                node.update(state.getWinner());
                node = node.getParent();  // Ensure getParent is implemented in the Node interface
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Execution time: " + executionTime + " ms");
        System.out.println("Memory used: " + memory + " bytes");
        System.out.println("Total nodes created: " + TicTacToeNode.getTotalNodes());
        int maxDepth = TicTacToeNode.getMaxDepth(root);
        System.out.println("Maximum depth of the tree: " + maxDepth);
        Node bestChild = root.getBestChild();
        int [] bestMove = bestChild != null ? bestChild.getMove() : getRandomMove(game);
        collector.collect(new Metrics(executionTime, memory, TicTacToeNode.getTotalNodes(), maxDepth, bestMove));
        return bestMove;
    }

    private int[] getRandomMove(Game game) {
        List<int[]> availableMoves = game.getAvailableMoves();
        Random random = new Random();
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

}