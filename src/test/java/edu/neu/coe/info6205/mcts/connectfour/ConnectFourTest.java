package edu.neu.coe.info6205.mcts.connectfour;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConnectFourTest{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));  // Redirect System.out to capture outputs
        ConnectFour.intializeGame();  // Initialize game to reset the board and players
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);  // Restore the original System.out
    }

    @Test
    public void testGameInitializationOutputs() {
        ConnectFour.intializeGame();
        String output = outContent.toString();
        assertTrue(output.contains("Player X starts the game") || output.contains("Player O starts the game"));
    }


    @Test
    public void testRandomStartingPlayerOutput() {
        boolean startsWithX = false;
        boolean startsWithO = false;
        for (int i = 0; i < 10; i++) {
            outContent.reset();  // Clear the output stream before each initialization
            ConnectFour.intializeGame();
            if (outContent.toString().contains("Player X starts the game")) {
                startsWithX = true;
            }
            if (outContent.toString().contains("Player O starts the game")) {
                startsWithO = true;
            }
            if (startsWithX && startsWithO) break;
        }
        assertTrue(startsWithX && startsWithO); // Check that both players can start
    }
}
