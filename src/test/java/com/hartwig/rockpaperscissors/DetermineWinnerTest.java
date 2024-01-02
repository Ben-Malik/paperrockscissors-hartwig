package com.hartwig.rockpaperscissors;

import com.hartwig.rockpaperscissors.logic.GameEngineUtil;
import com.hartwig.rockpaperscissors.model.Move;
import org.junit.jupiter.api.Test;

import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_ID;
import static com.hartwig.rockpaperscissors.logic.GameSettings.TIE_ID;
import static com.hartwig.rockpaperscissors.logic.GameSettings.PLAYER_ID;
import static com.hartwig.rockpaperscissors.model.Move.PAPER;
import static com.hartwig.rockpaperscissors.model.Move.ROCK;
import static com.hartwig.rockpaperscissors.model.Move.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the scenarios of {@linkplain GameEngineUtil#determineWinner(Move, Move)}
 *
 * @author Ben-Malik
 */
public class DetermineWinnerTest {

    private final GameEngineUtil gameEngineUtil = new GameEngineUtil();

    @Test
    public void ensureComputerWinsWhenPlayerUsesRockAndComputerUsersPaper() {
        int winner = gameEngineUtil.determineWinner(ROCK, PAPER);
        assertEquals(COMPUTER_ID, winner);
    }

    @Test
    public void ensureItIsATieWhenPlayerUsesAndComputerMakeTheSameMove() {
        int winner = gameEngineUtil.determineWinner(PAPER, PAPER);
        assertEquals(TIE_ID, winner);
    }

    @Test
    public void ensurePlayerWinsWhenPlayerUsesRockAndComputerUsesScissors() {
        int winner = gameEngineUtil.determineWinner(ROCK, SCISSORS);
        assertEquals(PLAYER_ID, winner);
    }

    @Test
    public void ensurePlayerWinsWhenPlayerUsesScissorsAndComputerUsesPaper() {
        int winner = gameEngineUtil.determineWinner(SCISSORS, PAPER);
        assertEquals(PLAYER_ID, winner);
    }

    @Test
    public void ensureComputerWinsWhenPlayerUsesScissorsAndComputerUsesRock() {
        int winner = gameEngineUtil.determineWinner(SCISSORS, ROCK);
        assertEquals(COMPUTER_ID, winner);
    }

    @Test
    public void ensureComputerWinsWhenPlayerUsesPaperAndComputerUsesScissors() {
        int winner = gameEngineUtil.determineWinner(PAPER, SCISSORS);
        assertEquals(COMPUTER_ID, winner);
    }

    @Test
    public void ensurePlayerWinsWhenPlayerUsesPaperAndComputerUsesRock() {
        int winner = gameEngineUtil.determineWinner(PAPER, ROCK);
        assertEquals(PLAYER_ID, winner);
    }
}
