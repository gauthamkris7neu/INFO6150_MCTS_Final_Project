package edu.neu.coe.info6205.mcts.core;

import java.util.List;

public interface State<T> {
    List<int[]> getAvailableMoves();
    boolean isGameOver();
    void makeMove(int[] move);
    int getWinner();
}
