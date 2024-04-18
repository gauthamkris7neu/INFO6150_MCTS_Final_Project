package edu.neu.coe.info6205.mcts.core;

import edu.neu.coe.info6205.mcts.core.Game;

import java.util.List;

public interface Node {
    void update(double result);
    Node addChild(Game state, int[] move);
    Node getBestChild();
    List<Node> getChildNodes();
    List<int[]> getUntriedMoves();
    void removeMoveFromUntriedMoves(int[] move);
    double getWins();
    double getVisits();
    int[] getMove();
    int getDepth();

    Node getParent();
}