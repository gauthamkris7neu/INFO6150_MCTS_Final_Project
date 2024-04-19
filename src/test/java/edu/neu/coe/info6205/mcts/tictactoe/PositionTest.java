package edu.neu.coe.info6205.mcts.tictactoe;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @Test
    public void testGetX() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getX());
    }

    @Test
    public void testGetY() {
        Position position = new Position(1, 2);
        assertEquals(2, position.getY());
    }

    @Test
    public void testSetX() {
        Position position = new Position();
        position.setX(3);
        assertEquals(3, position.getX());
    }

    @Test
    public void testSetY() {
        Position position = new Position();
        position.setY(4);
        assertEquals(4, position.getY());
    }

}

