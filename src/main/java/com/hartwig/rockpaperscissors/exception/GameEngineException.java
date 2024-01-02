package com.hartwig.rockpaperscissors.exception;

/**
 * An exception for the {@linkplain com.hartwig.rockpaperscissors.logic.GameEngine}
 *
 * @author Ben-Malik
 */
public class GameEngineException extends Exception {

    public GameEngineException(String message) {
        super(message);
    }
}
