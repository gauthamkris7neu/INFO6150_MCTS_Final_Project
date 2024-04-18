package edu.neu.coe.info6205.mcts.tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MetricsCollector {
    List<Metrics> metricsList = new ArrayList<>();

    public void collect(Metrics metrics) {
        metricsList.add(metrics);
    }

    public void writeToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.println("ExecutionTime,MemoryUsed,TotalNodes,MaxDepth");
            for (Metrics m : metricsList) {
                writer.println(m.getExecutionTime() + "," + m.getMemoryUsed() + "," + m.getTotalNodes() + "," + m.getMaxDepth());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to CSV");
        }
    }
}
