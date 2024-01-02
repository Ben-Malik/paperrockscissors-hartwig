package com.hartwig.rockpaperscissors;

import com.hartwig.rockpaperscissors.exception.GameEngineException;
import com.hartwig.rockpaperscissors.logic.GameEngine;
import com.hartwig.rockpaperscissors.logic.GameSettings;
import com.hartwig.rockpaperscissors.model.Game;
import com.hartwig.rockpaperscissors.model.GameState;
import com.hartwig.rockpaperscissors.model.Interaction;
import com.hartwig.rockpaperscissors.model.Move;
import com.hartwig.rockpaperscissors.model.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_ID;
import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_NAME;
import static com.hartwig.rockpaperscissors.logic.GameSettings.GAME_NAME;
import static com.hartwig.rockpaperscissors.logic.GameSettings.PLAYER_ID;
import static com.hartwig.rockpaperscissors.model.Move.PAPER;
import static com.hartwig.rockpaperscissors.model.Move.ROCK;
import static com.hartwig.rockpaperscissors.model.Move.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@linkplain GameEngine} class.
 *
 * @author Ben-Malik
 */
public class GameEngineTest {

    private Game game;
    private GameEngine gameEngine;
    private final String PLAYER_NAME = "PlayerName";

    @BeforeEach
    public void init() {
        game = generateGame();
        gameEngine = new GameEngine(game);
    }

    @Test
    public void startInteractionsEndsGameWhenGivenInputIsZero() throws GameEngineException {
        Scanner scanner = mock(Scanner.class);
        when(scanner.next()).thenReturn("0");
        gameEngine.startGameInteractions(scanner);
        assertEquals(GameState.ENDED, game.getState());
    }

    @Test
    public void startGameGetsPlayerNameAndEnsureParticipants() throws GameEngineException {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(PLAYER_NAME);
        String playerName = gameEngine.getAndGreetPlayerName(scanner);
        assertEquals(PLAYER_NAME, playerName);

        when(scanner.next()).thenReturn("0");

        gameEngine.startGame(scanner);

        assertNotNull(game.getPlayer());
        assertNotNull(game.getComputer());
        assertTrue(game.getInteractions().isEmpty());
        assertEquals(GameState.ENDED, game.getState());
    }

    @Test
    public void triggerInteractionWorksProperly() {
        List<Interaction> allInteractions = new ArrayList<>();

        Participant computer = gameEngine.getComputer();
        Participant player = gameEngine.getPlayer();

        // Interaction 1.
        Interaction currentInteraction = gameEngine.triggerInteraction(PAPER, ROCK);
        allInteractions.add(currentInteraction);

        assertEquals(1, computer.getNumberOfWins());
        assertEquals(1, player.getNumberOfLosses());

        // Interaction 2 & 3
        currentInteraction = gameEngine.triggerInteraction(SCISSORS, SCISSORS);
        allInteractions.add(currentInteraction);

        currentInteraction = gameEngine.triggerInteraction(ROCK, ROCK);
        allInteractions.add(currentInteraction);

        computer = gameEngine.getComputer();
        player = gameEngine.getPlayer();
        assertTrue(computer.getNumberOfTies() == 2 && computer.getNumberOfWins() == 1 && computer.getNumberOfLosses() == 0);
        assertTrue(player.getNumberOfTies() == 2 && player.getNumberOfWins() == 0 && player.getNumberOfLosses() == 1);

        // Interaction 4.
        currentInteraction = gameEngine.triggerInteraction(SCISSORS, ROCK);
        allInteractions.add(currentInteraction);

        computer = gameEngine.getComputer();
        player = gameEngine.getPlayer();
        assertTrue(computer.getNumberOfTies() == 2 && computer.getNumberOfWins() == 1 && computer.getNumberOfLosses() == 1);
        assertTrue(player.getNumberOfTies() == 2 && player.getNumberOfWins() == 1 && player.getNumberOfLosses() == 1);

        assertEquals(4, allInteractions.size());

        gameEngine.quitGame();

        // Compare the interactions from the Game object and the ones saved in the tests.
        List<Interaction> gameInteractions = game.getInteractions();
        assertTrue(gameInteractions.stream()
                .anyMatch(i -> i.getComputerMove().equals(allInteractions.get(0).getComputerMove())
                        && i.getPlayerMove().equals(allInteractions.get(0).getPlayerMove())
                        && i.getWinner().equals(allInteractions.get(0).getWinner())
                        && i.getMadeAt().equals(allInteractions.get(0).getMadeAt())));

        assertTrue(gameInteractions.stream()
                .anyMatch(i -> i.getComputerMove().equals(allInteractions.get(1).getComputerMove())
                        && i.getPlayerMove().equals(allInteractions.get(1).getPlayerMove())
                        && i.getWinner().equals(allInteractions.get(1).getWinner())
                        && i.getMadeAt().equals(allInteractions.get(1).getMadeAt())));

        assertTrue(gameInteractions.stream().anyMatch(i -> i.getComputerMove().equals(allInteractions.get(2).getComputerMove())
                && i.getPlayerMove().equals(allInteractions.get(2).getPlayerMove())
                && i.getWinner().equals(allInteractions.get(2).getWinner())
                && i.getMadeAt().equals(allInteractions.get(2).getMadeAt())));

        assertTrue(gameInteractions.stream().anyMatch(i -> i.getComputerMove().equals(allInteractions.get(3).getComputerMove())
                && i.getPlayerMove().equals(allInteractions.get(3).getPlayerMove())
                && i.getWinner().equals(allInteractions.get(3).getWinner())
                && i.getMadeAt().equals(allInteractions.get(3).getMadeAt())));
    }

    @Test
    public void ensureParticipantsCreatesThePlayerAndComputerWithAccurateData() {
        gameEngine.ensureParticipants(PLAYER_NAME);

        assertTrue(PLAYER_NAME.equals(game.getPlayer().getName())
                && game.getPlayer().getId() == PLAYER_ID
                && game.getPlayer().getNumberOfLosses() == 0
                && game.getPlayer().getNumberOfWins() == 0
                && game.getPlayer().getNumberOfTies() == 0);

        assertTrue(COMPUTER_NAME.equals(game.getComputer().getName())
                && game.getComputer().getId() == COMPUTER_ID
                && game.getComputer().getNumberOfLosses() == 0
                && game.getComputer().getNumberOfWins() == 0
                && game.getComputer().getNumberOfTies() == 0);

        assertTrue(game.getInteractions().isEmpty());
        assertEquals(GAME_NAME, game.getName());
        assertEquals(GameState.STARTED, game.getState());
    }

    @Test
    public void testQuitGameUpdatesTheStateOfGameAppropriately() {
        gameEngine.ensureParticipants(PLAYER_NAME);

        assertTrue(game.getInteractions().isEmpty());
        assertEquals(GAME_NAME, game.getName());
        assertEquals(GameState.STARTED, game.getState());

        gameEngine.quitGame();

        assertEquals(GameState.ENDED, game.getState());
        assertTrue(game.getInteractions().isEmpty());
    }

    @Test
    public void makeComputerMoveReturnsAMoveSuccessfully() {
        Move computerMove = gameEngine.makeComputerMove();
        assertNotNull(computerMove);
    }

    private Game generateGame() {
        Game game = new Game();
        game.setName(GameSettings.GAME_NAME);

        Participant player = new Participant();
        player.setName("playerName");
        player.setId(PLAYER_ID);
        game.setPlayer(player);

        Participant computer = new Participant();
        computer.setName(COMPUTER_NAME);
        computer.setId(COMPUTER_ID);
        game.setComputer(computer);

        game.setState(GameState.STARTED);
        return game;
    }
}
