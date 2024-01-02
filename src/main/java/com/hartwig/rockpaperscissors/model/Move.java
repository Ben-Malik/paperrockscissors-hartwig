package com.hartwig.rockpaperscissors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * An enumeration of the three moves possible in the Rock Paper & Scissors game.
 *
 * @author Ben-Malik
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Move {

    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private int value;

}
