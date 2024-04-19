# Monte Carlo Tree Search Games: Tic-Tac-Toe & Connect Four

Welcome to the repository for the implementations of Tic-Tac-Toe and Connect Four using the Monte Carlo Tree Search (MCTS) algorithm. This project aims to demonstrate the application of MCTS to simple and moderately complex games, providing robust AI opponents in both.

## Overview

This repository contains Java implementations for the following games:
- **Tic-Tac-Toe**: A classic 3x3 grid game where two players take turns marking their symbols with the goal of aligning three marks horizontally, vertically, or diagonally.
- **Connect Four**: A strategy game where two players alternately drop colored discs into a seven-column, six-row vertically suspended grid, aiming to connect four of their discs in a row.

The AI for both games uses the Monte Carlo Tree Search, a decision-making algorithm known for its effectiveness in board game scenarios like chess and Go.

## Features

- Interactive Tic-Tac-Toe and Connect Four games against an AI.
- AI driven by the Monte Carlo Tree Search algorithm.
- Easy to use command-line interface for playing the games.

## Getting Started

### Prerequisites

Ensure you have Java JDK 11 or later installed on your computer to run the Java applications.

### Installation

Clone the repository and navigate into it:

```bash
git clone https://github.com/gauthamkris7neu/INFO6150_MCTS_Final_Project.git
git checkout final_changes
cd src
cd main
cd java/edu/neu/coe/info6205
cd mcts
For ConnectFour: cd connectfour & For TicTacToe: cd tictactoe
```

Compile the Java files (ensure you are in the project directory):

```bash
javac -d bin src/*.java
```

# Running the Games
Execute the following commands to run the games:
 * Tic-Tac-Toe:
    ```bash
    java -cp bin TicTacToeGame
    ```
 * Connect Four:
   ```bash
   java -cp bin ConnectFour
   ```
# How to Play
After starting the game of your choice via the command line:
> For TicTacToe
* Follow the on-screen prompts and decide which mode to play.
* If you decide to play Human VS AI follow the on-screen prompts to make your moves against the AI.
* The AI will automatically calculate its moves using MCTS and display the game board after each move.
> For Connect Four
* It is AI VS AI
* So the AI will automatically calculate its moves using MCTS and display the game board after each move.

# License

This project is licensed under the MIT License - see the LICENSE.md file for details.
