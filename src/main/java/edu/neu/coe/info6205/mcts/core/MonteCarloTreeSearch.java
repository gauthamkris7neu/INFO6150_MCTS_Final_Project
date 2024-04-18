package edu.neu.coe.info6205.mcts.core;

import edu.neu.coe.info6205.mcts.tictactoe.TicTacToe;

public interface MonteCarloTreeSearch {
    int[] findNextMove(Game game);

}
