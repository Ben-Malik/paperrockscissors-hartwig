package com.hartwig.rockpaperscissors.logic;

/**
 * A final class holding all the static data used all across the game engine.
 *
 * @author Ben-Malik
 */
public final class GameSettings {

    // Constant values.
    public static final String GAME_NAME = "Rock Paper Scissors";
    public static final String COMPUTER_NAME = "computer";
    public static final int QUIT = 0;
    public static final int PLAYER_ID = 1;
    public static final int COMPUTER_ID = 0;
    public static final int TIE_ID = -1;
    public static final String TIE = "Tie";
    public static final int MAXIMUM_NUMBER_OF_MOVES = 3;

    // Messages
    public static final String WELCOME_MESSAGE = "\nWelcome to the %s game!%n";
    public static final String GET_PLAYER_NAME_MESSAGE = "How should we call you? ";
    public static final String GREETING_MESSAGE = "%nIt's a pleasure to meet you %s!%n%n";
    public static final String TIE_MESSAGE = "It's a tie!";
    public static final String LOSS_MESSAGE = "You lost!";
    public static final String WIN_MESSAGE = "You won!";

    public static final String TIE_ROUND_MESSAGE = "There was a tie this time! Well done!";
    public static final String WIN_ROUND_MESSAGE = "Congratulations, you've won this round!";
    public static final String LOSS_ROUND_MESSAGE = "Oh no! You have lost this round!";

    public static final String INVALID_MOVE_MESSAGE = "Invalid input for move: ";
    public static final String GAME_OPTIONS_DISPLAY =  """
                        Enter the corresponding number of the move you want to make:\s
                        1. Rock\s
                        2. Paper\s
                        3. Scissors\s
                        0. Quit\s
                        """;
    public static final String DISPLAY_PLAYER_MOVE = "Your move is %d. %s%n";
    public static final String DISPLAY_COMPUTER_MOVE = "Computer move: %s. %s%n";
    public static final String ENTER_YOUR_MOVE_MESSAGE = "Enter your move: ";
    public static final String TOTAL_NUMBER_OF_INTERACTIONS = "%nTotal Number of Interactions: %s.%n";
    public static final String COMPUTER_OVERVIEW_MESSAGE = "Computer had %s win(s), %s lost(s) and %s tie(s)%n";
    public static final String PLAYER_OVERVIEW_MESSAGE = "You had %s win(s), %s lost(s) and %s tie(s)%n";
    public static final String INPUT_IS_NOT_NUMBER = "Enter a number value of the move you'd like to make.";
    public static final String GAME_OVERVIEW = "\n ..............Game Overview..............";
    public static final String ALL_INTERACTION_HEADER = "\n..............All Interactions.............. ";
}
