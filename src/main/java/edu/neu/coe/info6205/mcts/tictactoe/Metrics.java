package edu.neu.coe.info6205.mcts.tictactoe;

public class Metrics {
    private long executionTime;
    private long memoryUsed;
    private int totalNodes;
    private int maxDepth;
    private int[] bestMove;

    public Metrics(long executionTime, long memoryUsed, int totalNodes, int maxDepth, int[] bestMove) {
        this.executionTime = executionTime;
        this.memoryUsed = memoryUsed;
        this.totalNodes = totalNodes;
        this.maxDepth = maxDepth;
        this.bestMove = bestMove;
    }

    // Getters
    public long getExecutionTime() { return executionTime; }
    public long getMemoryUsed() { return memoryUsed; }
    public int getTotalNodes() { return totalNodes; }
    public int getMaxDepth() { return maxDepth; }
    public int[] getBestMove() { return bestMove; }
}
