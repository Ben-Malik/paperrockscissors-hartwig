package com.hartwig.rockpaperscissors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * All the interactions made in a game session.
 * In other words, the pair of moves made by both the player and the computer
 * alongside the winner of the interaction in question and the time at which that was mde.
 *
 * @author Ben-Malik
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Interaction {
    private Move computerMove;
    private Move playerMove;
    private String winner;
    private Date madeAt;
}
