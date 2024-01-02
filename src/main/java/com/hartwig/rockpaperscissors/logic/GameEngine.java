package com.hartwig.rockpaperscissors.logic;

import com.hartwig.rockpaperscissors.exception.GameEngineException;
import com.hartwig.rockpaperscissors.model.Game;
import com.hartwig.rockpaperscissors.model.GameState;
import com.hartwig.rockpaperscissors.model.Interaction;
import com.hartwig.rockpaperscissors.model.Move;
import com.hartwig.rockpaperscissors.model.Participant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.hartwig.rockpaperscissors.logic.GameSettings.ALL_INTERACTION_HEADER;
import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_OVERVIEW_MESSAGE;
import static com.hartwig.rockpaperscissors.logic.GameSettings.DISPLAY_COMPUTER_MOVE;
import static com.hartwig.rockpaperscissors.logic.GameSettings.TIE;
import static com.hartwig.rockpaperscissors.logic.GameSettings.GAME_OVERVIEW;
import static com.hartwig.rockpaperscissors.logic.GameSettings.INPUT_IS_NOT_NUMBER;
import static com.hartwig.rockpaperscissors.logic.GameSettings.PLAYER_OVERVIEW_MESSAGE;
import static com.hartwig.rockpaperscissors.logic.GameSettings.TOTAL_NUMBER_OF_INTERACTIONS;

/**
 * The engine of the game.
 *
 * @author Ben-Malik
 */
@Getter
@Setter
public class GameEngine {

    private final Game game;
    private final GameEngineUtil gameEngineUtil;
    private final List<Interaction> allInteractions = new ArrayList<>();

    private Participant player;
    private Participant computer;

    public GameEngine() {
        this.gameEngineUtil = new GameEngineUtil();
        this.game = gameEngineUtil.createGame();
    }

    public GameEngine(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.computer = game.getComputer();
        this.gameEngineUtil = new GameEngineUtil();
    }

    /** Gets the player's name and create both the player and computer as participants.*/
    public void ensureParticipants(String playerName) {

        Participant computer = gameEngineUtil.createComputer();
        Participant player = gameEngineUtil.createPlayer(playerName);
        this.computer = computer;
        this.player = player;
        game.setPlayer(player);
        game.setComputer(computer);
    }

    /**
     * A helper method that randomly generates a value between 1 and 3 as a move.
     * @return The randomly created computer move as a {@linkplain Move}.
     */
    public Move makeComputerMove() {
        Random random = new Random();
        int randomMove = random.nextInt(GameSettings.MAXIMUM_NUMBER_OF_MOVES);
        return Move.values()[randomMove];
    }

    public void startGame(Scanner scanner) throws GameEngineException {

        game.setState(GameState.STARTED);
        String playerName = getAndGreetPlayerName(scanner);
        ensureParticipants(playerName);
        gameEngineUtil.displayGameOptions();
        startGameInteractions(scanner);
    }

    /**
     * Starts the game interactions and get the player and computer moves and determine the winner
     * until the player decides to quit the game.
     */
    public void startGameInteractions(Scanner scanner) throws GameEngineException {

        while (true) {
            System.out.print(GameSettings.ENTER_YOUR_MOVE_MESSAGE);
            String input = scanner.next();

            int playerMoveAsInt;
            try {
                playerMoveAsInt = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // In case the player enters a value that is not a number
                System.out.println(INPUT_IS_NOT_NUMBER);
                continue;
            }

            if (playerMoveAsInt == GameSettings.QUIT) {
                quitGame();
                break;
            }

            if (Arrays.stream(Move.values()).noneMatch(x -> x.getValue() == playerMoveAsInt)) {
                System.out.println(GameSettings.INVALID_MOVE_MESSAGE + playerMoveAsInt);
                continue;
            }

            Move playerMove = gameEngineUtil.fromInt(playerMoveAsInt);
            System.out.printf(GameSettings.DISPLAY_PLAYER_MOVE, playerMoveAsInt, playerMove);

            Move computerMove = makeComputerMove();
            System.out.printf(DISPLAY_COMPUTER_MOVE, computerMove.getValue(), computerMove);

            triggerInteraction(computerMove, playerMove);
        }
    }

    /**
     * Plays the given computer and player move.
     * @param computerMove the move of the computer.
     * @param playerMove the move of the player
     */
    public Interaction triggerInteraction(Move computerMove, Move playerMove) {

        Interaction currentInteraction = new Interaction();
        currentInteraction.setComputerMove(computerMove);
        currentInteraction.setPlayerMove(playerMove);
        currentInteraction.setMadeAt(new Date());

        if (playerMove.equals(computerMove)) {
            System.out.println(GameSettings.TIE_MESSAGE);
            computer.incrementTie();
            player.incrementTie();
            currentInteraction.setWinner(TIE);
        } else {
            int winner = gameEngineUtil.determineWinner(playerMove, computerMove);
            if (winner == GameSettings.PLAYER_ID) {
                System.out.println(GameSettings.WIN_MESSAGE);
                player.incrementWin();
                computer.incrementLoss();
                currentInteraction.setWinner(player.getName());
            } else {
                System.out.println(GameSettings.LOSS_MESSAGE);
                player.incrementLoss();
                computer.incrementWin();
                currentInteraction.setWinner(computer.getName());
            }
        }
        allInteractions.add(currentInteraction);
        return currentInteraction;
    }

    /**
     * A helper method invoked when the player decides to quit the game.
     * <br>
     * It displays the overview of the game and does the final settings required on the game.
     *
     */
    public void quitGame() {
        displayGameOverview();
        updateGameSettings();
    }

    private void displayGameOverview() {
        String winnerMessage = GameSettings.LOSS_ROUND_MESSAGE;
        if (player.getNumberOfWins() == computer.getNumberOfWins())
            winnerMessage = GameSettings.TIE_ROUND_MESSAGE;
        else if (player.getNumberOfWins() > computer.getNumberOfWins())
            winnerMessage = GameSettings.WIN_ROUND_MESSAGE;

        System.out.println(GAME_OVERVIEW);
        System.out.println(winnerMessage);

        System.out.printf(PLAYER_OVERVIEW_MESSAGE, player.getNumberOfWins(), player.getNumberOfLosses(), player.getNumberOfTies());
        System.out.printf(COMPUTER_OVERVIEW_MESSAGE, computer.getNumberOfWins(), computer.getNumberOfLosses(), computer.getNumberOfTies());
    }

    /**
     * Grabs the name of the player and greets him/her.
     * @return The name of the player.
     */
    public String getAndGreetPlayerName(Scanner scanner) {
        System.out.printf(GameSettings.WELCOME_MESSAGE, game.getName());
        System.out.print(GameSettings.GET_PLAYER_NAME_MESSAGE);
        String playerName = scanner.nextLine();
        System.out.printf(GameSettings.GREETING_MESSAGE, playerName);
        return playerName;
    }

    private void updateGameSettings() {
        game.setState(GameState.ENDED);
        game.setComputer(computer);
        game.setPlayer(player);
        game.setInteractions(allInteractions);

        System.out.printf(TOTAL_NUMBER_OF_INTERACTIONS, game.getInteractions().size());
        System.out.println(ALL_INTERACTION_HEADER);
        game.getInteractions().forEach(System.out::println);
    }

}
