package com.hartwig.rockpaperscissors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * An encapsulation of the Rock Paper & Scissors game.
 *
 * @author Ben-Malik
 */
@Getter
@Setter
@NoArgsConstructor
public class Game {
    private String name;
    private GameState state;
    private Participant player;
    private Participant computer;
    List<Interaction> interactions = new ArrayList<>();
}
