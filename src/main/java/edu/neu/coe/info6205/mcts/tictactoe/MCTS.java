package edu.neu.coe.info6205.mcts.tictactoe;

import edu.neu.coe.info6205.mcts.core.Game;
import edu.neu.coe.info6205.mcts.core.MonteCarloTreeSearch;
import edu.neu.coe.info6205.mcts.core.Node;

import java.util.*;

class MCTS implements MonteCarloTreeSearch {
    private static final int SIMULATIONS = 1000000;
    private static final double C = Math.sqrt(2);


    @Override
    public int[] findNextMove(Game game) {
        Node root = new TicTacToeNode(game);
        Random random = new Random();

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

        Node bestChild = root.getBestChild();
        return bestChild != null ? bestChild.getMove() : getRandomMove(game);
    }

    private int[] getRandomMove(Game game) {
        List<int[]> availableMoves = game.getAvailableMoves();
        Random random = new Random();
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

}