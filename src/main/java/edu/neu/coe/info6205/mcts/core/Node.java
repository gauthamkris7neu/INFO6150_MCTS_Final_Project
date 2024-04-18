package edu.neu.coe.info6205.mcts.core;

import java.util.ArrayList;
import java.util.List;

public interface Node {
    Node getParent();
    ArrayList<Node> getChildNodes();
    List<int[]> getUntriedMoves();
    void update(double result);
    int[] getMove();
    Node getBestChild();
}
