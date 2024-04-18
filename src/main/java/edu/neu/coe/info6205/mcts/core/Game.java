package edu.neu.coe.info6205.mcts.core;

import java.util.List;

public interface Game {
    boolean isGameOver();
    List<int[]> getAvailableMoves();
    void makeMove(int[] move);
    Game clone();
    int getWinner();
}
