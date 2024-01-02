# Rock Paper Scissors Game

## Tech stack
1. Java 17
2. Maven
3. Spring Boot
4. JUnit
5. Mockito
6. Lombok

## How to Play?
```
1. Clone the project to your local device.
2. Go to main folder of the project on your terminal.
3. Run this command 'mvn clean install spring-boot:run'
4. To skip steps 2 and 3, Open the project on Intellij, Eclips or STS and run PaperRockScissorsApplication.
5. You will be prompted with an interaction console where you can specify your name and start playing. 
```

## Test Coverage
1. The class coverage is 90%
2. The method coverage is 92%
3. The total line coverage is 84%

## Package Definitions

1. `com.hartwig.rockpaperscissors.exceptioin`:
This package is meant to have the exceptions pre-defined and thrown throughout the app. 
2. `com.hartwig.rockpaperscissors.logic`: All the business logic is implemented here. 
3. `com.hartwig.rockpaperscissors.model`: All the model classes are defined here.

## Class Definitions

```
public class GameEngineException {}: Thrown throughtout the game engine business logic implementation.

public class GameEngine {}: The implementation of the game engine lies here.

public class GameEngineUtil {}: This is a utility class defining and implementing helper method used in the GameEngine class.

public final GameSettings {}: All default values including messages displayed are defined here.

public class Game {}: It is a model class composed of the name, details about the player and the computer participants, the state of the game and all interactions having been made.

public enum GameState {}: An enumeration class to indicate the current state of the game: STARTED or ENDED.

public class Interaction {}: An interaction is composed of the moves of both the player and the computer, the winner of the very interaction and the date and time at which it was made.

public enum Move {}: The moves doable during the game: rock, paper or scissors.

public class Participant {}: It encapsulates the information of a given participant. Here we have two: a player and a computer.
The computer is automatically created at game start and the player is created after getting the name of the player from the console.
```


## Unit Tests

```
DetermineWinnerTest: Unit tests taking into account all the possible scenarios in the determination of the winner given two moves.
```

```
GameEngineTest: Unit tests for the methods in GameEngine.
```

```
GameEngineUtilTest: Unit tests for the methods in GameEngineUtil.
```

