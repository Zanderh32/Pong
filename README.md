# Harrison_Pong

This project is a **Java-based Pong game** implemented using the Swing library for the graphical user interface (GUI). It features both single-player (vs CPU) and two-player modes, sound effects, and a score-based game system.

## Features

- **Single and Two-Player Modes**: 
  - Play against the CPU (automatic paddle movement) in single-player mode or against another player in two-player mode.
- **Graphical Interface**: 
  - The game uses Java's `JPanel` and `JFrame` to display paddles, the ball, and scores. The ball and paddles move smoothly across the screen in response to player inputs and game mechanics.
- **Score Tracking**: 
  - Both player scores are displayed on the screen, and the game ends when a player reaches 11 points, displaying the winner.
- **Sound Effects**: 
  - Plays sound effects when the ball hits a paddle or when a player scores. Uses the `javax.sound.sampled` library to handle audio.

## How to Play

1. **Startup**: 
   - When the game launches, you'll be presented with an introductory screen.
   - Press '1' to play against the CPU.
   - Press '2' to play against another player.

2. **Controls**:
   - **Player 1 (Left Paddle)**: W (up), S (down)
   - **Player 2 (Right Paddle)**: Up Arrow (up), Down Arrow (down)

3. **Winning the Game**:
   - The first player to reach 11 points wins.
   - The game will display a message announcing the winner and will stop.

## How to Run

### Prerequisites

- Java Development Kit (JDK) installed (preferably JDK 8 or higher).

### Running the Game

1. **Compile the Game**:
   - Open your terminal or command prompt.
   - Navigate to the directory where `Harrison_Pong.java` is located.
   - Compile the program using:
     ```bash
     javac Harrison_Pong.java
     ```

2. **Run the Game**:
   - After compilation, run the program with:
     ```bash
     java Harrison_Pong
     ```

3. **Play**:
   - Enjoy playing Pong either against the CPU or with a friend!

## Code Overview

- **`PongPanel`**: 
  - Handles all rendering, input processing, and game logic, including player movement, ball physics, and scorekeeping.
- **Sound Effects**: 
  - The game plays sound files (`score.wav` and `collision.wav`) for scoring and paddle collisions. Ensure these audio files are in the same directory as the program or adjust the file paths accordingly.
