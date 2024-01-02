package com.hartwig.rockpaperscissors;

import com.hartwig.rockpaperscissors.exception.GameEngineException;
import com.hartwig.rockpaperscissors.logic.GameEngineUtil;
import com.hartwig.rockpaperscissors.logic.GameSettings;
import com.hartwig.rockpaperscissors.model.Game;
import com.hartwig.rockpaperscissors.model.Move;
import org.junit.jupiter.api.Test;

import static com.hartwig.rockpaperscissors.logic.GameSettings.GAME_NAME;
import static com.hartwig.rockpaperscissors.model.Move.PAPER;
import static com.hartwig.rockpaperscissors.model.Move.ROCK;
import static com.hartwig.rockpaperscissors.model.Move.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameEngineUtilTest {

    private final static GameEngineUtil gameEngineUtil = new GameEngineUtil();
    @Test
    public void createGameCreatesGame() {
        Game game = gameEngineUtil.createGame();
        assertNotNull(game);

        assertEquals(GAME_NAME, game.getName());
        assertTrue(game.getInteractions().isEmpty());
        assertNull(game.getComputer());
        assertNull(game.getPlayer());
        assertNull(game.getState());
    }

    @Test
    public void fromIntReturnsTheEnumerationVersionOfTheGivenIntValue() throws GameEngineException {
        Move move = gameEngineUtil.fromInt(1);
        assertEquals(ROCK, move);

        move = gameEngineUtil.fromInt(2);
        assertEquals(PAPER, move);

        move = gameEngineUtil.fromInt(3);
        assertEquals(SCISSORS.getValue(), move.getValue());
    }

    @Test
    public void fromIntThrowsGameEngineExceptionWhenGivenIntValueIsNotTheRangeOneToThree() {
        int invalidMove = 4;
        Exception exception = assertThrows(GameEngineException.class, () -> gameEngineUtil.fromInt(invalidMove));
        assertEquals(GameSettings.INVALID_MOVE_MESSAGE + invalidMove, exception.getMessage());

    }
}
