package edu.neu.coe.info6205.mcts.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void testPlayerSymbol() {
        Player player = new Player('X');
        assertEquals('X', player.getSymbol());

        player = new Player('O');
        assertEquals('O', player.getSymbol());
    }
}
