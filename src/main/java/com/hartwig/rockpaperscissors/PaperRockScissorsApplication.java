package com.hartwig.rockpaperscissors;

import com.hartwig.rockpaperscissors.exception.GameEngineException;
import com.hartwig.rockpaperscissors.logic.GameEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PaperRockScissorsApplication {

    public static void main(String[] args) throws GameEngineException {
        SpringApplication.run(PaperRockScissorsApplication.class, args);

        GameEngine engine = new GameEngine();
        Scanner scanner = new Scanner(System.in);
        engine.startGame(scanner);
    }
}
