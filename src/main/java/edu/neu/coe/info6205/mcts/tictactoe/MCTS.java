package edu.neu.coe.info6205.mcts.tictactoe;

import java.util.*;

class MCTS {
    private static final int SIMULATIONS = 1000000;
    private static final double C = Math.sqrt(2);

    public int[] findNextMove(TicTacToe game) {
        Node root = new Node(game);
        Random random = new Random();

        for (int i = 0; i < SIMULATIONS; i++) {
            Node node = root;
            TicTacToe state = new TicTacToe(game);

            // Selection phase
            while (!node.untriedMoves.isEmpty() && !node.childNodes.isEmpty()) {
                node = node.getBestChild();
                state.makeMove(node.move);
            }

            // Expansion phase
            if (!node.untriedMoves.isEmpty()) {
                int randomMove = random.nextInt(node.untriedMoves.size());
                state.makeMove(node.untriedMoves.remove(randomMove));
                node = node.addChild(state);
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
                node = node.parent;
            }
        }

        Node bestChild = root.getBestChild();
        if (bestChild != null) {
            return bestChild.move;
        } else {
            // If no best child found, select a random move from available moves
            List<int[]> availableMoves = game.getAvailableMoves();
            int randomMove = random.nextInt(availableMoves.size());
            return availableMoves.get(randomMove);
        }
    }



    ;



    static class Node {
        Node parent;
        List<Node> childNodes;
        List<int[]> untriedMoves;
        double wins;
        double visits;
        int[] move;

        public Node(TicTacToe state) {
            this.parent = null;
            this.childNodes = new ArrayList<>();
            this.untriedMoves = new ArrayList<>(state.getAvailableMoves()); // Initialize with available moves from the game state
            this.wins = 0;
            this.visits = 0;
            this.move = null; // No move associated with the root
        }

        public Node(Node parent, TicTacToe state, int[] move) {
            this.parent = parent;
            this.childNodes = new ArrayList<>();
            this.wins = 0;
            this.visits = 0;
            this.move = move;

            if (parent != null) {
                this.untriedMoves = new ArrayList<>(parent.untriedMoves); // Copy from parent
                // Correctly remove the move
                removeMoveFromUntriedMoves(move);
            } else {
                this.untriedMoves = new ArrayList<>(state.getAvailableMoves()); // Initialize for the root node
            }
        }

        private void removeMoveFromUntriedMoves(int[] move) {
            if (move != null) {
                untriedMoves.removeIf(m -> Arrays.equals(m, move));
            }
        }


        public Node addChild(TicTacToe state) {
            if (!untriedMoves.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(untriedMoves.size());
                int[] move = untriedMoves.remove(randomIndex);
                Node child = new Node(this, state, move);
                childNodes.add(child);
                return child;
            }
            return null; // No untried moves left
        }


        public Node getBestChild() {
            return childNodes.stream()
                    .max(Comparator.comparingDouble(c -> c.wins / c.visits + C * Math.sqrt(Math.log(visits) / c.visits)))
                    .orElse(null);
        }

        public void update(double result) {
            visits++;
            wins += result;
        }
    }

}