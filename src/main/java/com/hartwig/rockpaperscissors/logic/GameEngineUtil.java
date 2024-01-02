package com.hartwig.rockpaperscissors.logic;

import com.hartwig.rockpaperscissors.exception.GameEngineException;
import com.hartwig.rockpaperscissors.model.Game;
import com.hartwig.rockpaperscissors.model.Move;
import com.hartwig.rockpaperscissors.model.Participant;

import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_ID;
import static com.hartwig.rockpaperscissors.logic.GameSettings.COMPUTER_NAME;
import static com.hartwig.rockpaperscissors.logic.GameSettings.PLAYER_ID;
import static com.hartwig.rockpaperscissors.model.Move.PAPER;
import static com.hartwig.rockpaperscissors.model.Move.ROCK;
import static com.hartwig.rockpaperscissors.model.Move.SCISSORS;

/**
 * A utility class for the GameEngine.
 *
 * @author Ben-Malik
 */
public class GameEngineUtil {

    /**
     * Creates the {@linkplain Game} with name as the one specified in {@linkplain GameSettings}
     * @return a newly created Game.
     */
    public Game createGame() {
        Game game = new Game();
        game.setName(GameSettings.GAME_NAME);
        return game;
    }

    /**
     * Creates a player with the name and the {@linkplain GameSettings#PLAYER_ID}
     * @param name The name of the player.
     * @return a newly created {@linkplain Participant}
     */
    public Participant createPlayer(String name) {
        return createParticipant(PLAYER_ID, name);
    }

    /**
     * Creates the computer participant.
     * @return a newly created participant.
     */
    public Participant createComputer() {
        return createParticipant(COMPUTER_ID, COMPUTER_NAME);
    }

    /**
     * Determines the winner given the move of both players.
     * @param playerMove The move of the player.
     * @param computerMove The move of the computer.
     * @return The id of the winning player and {@linkplain GameSettings#TIE_ID} in case of a tie scenario.
     */
    public int determineWinner(Move playerMove, Move computerMove) {

        if (playerMove.equals(computerMove))
            return GameSettings.TIE_ID;

        boolean playerWon =
                playerMove == ROCK && computerMove == SCISSORS
                        || (playerMove == SCISSORS && computerMove == PAPER)
                        || (playerMove == PAPER && computerMove == ROCK);

        return playerWon ? GameSettings.PLAYER_ID : COMPUTER_ID;
    }

    /**
     * Returns the Move corresponding to the given integer value.
     * @throws GameEngineException in case the given integer value is not in the range of [1,3]
     * */
    public Move fromInt(int move) throws GameEngineException {
        return switch (move) {
            case 1 -> ROCK;
            case 2 -> PAPER;
            case 3 -> SCISSORS;
            default -> throw new GameEngineException(GameSettings.INVALID_MOVE_MESSAGE + move);
            // in the scope of this game,
            // this scenario is unlikely as in the GameEngine, a proper message is displayed to the use when the given
            // integer is not in correct range.
        };
    }

    public void displayGameOptions() {
        System.out.println(GameSettings.GAME_OPTIONS_DISPLAY);
    }

    /**
     * A helper method to create a participant with the given id and name.
     * @param id the id of the participant.
     * @param name the name of the participant
     * @return The created participant with given id and name.
     */
    private Participant createParticipant(int id, String name) {
        Participant participant = new Participant();
        participant.setName(name);
        participant.setId(id);
        return participant;
    }

}
