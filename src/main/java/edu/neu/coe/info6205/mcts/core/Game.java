package edu.neu.coe.info6205.mcts.core;

public interface Game {
    State<? extends Game> getInitialState();
}
