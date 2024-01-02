package com.hartwig.rockpaperscissors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An encapsulation of the participants in the Rock Paper & Scissors game.
 * In our very case, the {@linkplain Game} will have an array of two players: The computer and the player.
 *
 * @author Ben-Malik
 */
@Getter
@Setter
@NoArgsConstructor
public class Participant {
    private int id;
    private String name;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfTies;

    public void incrementWin() {
        numberOfWins += 1;
    }

    public void incrementLoss() {
        numberOfLosses += 1;
    }

    public void incrementTie() {
        numberOfTies += 1;
    }

}
