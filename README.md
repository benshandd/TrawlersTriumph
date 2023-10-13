# Reel It In

SWEN225 Group Project

Reel It In is a top-down grid-based arcade style game in which you control a fishing boat. Your goal is to catch all the fish and return them to the dock to allow you through to the whirlpool portal that teleports you to the next level. 

## To run game:
Run main method located in: nz/ac/wgtn/swen225/lc/app/main/Main.java\
**Important:** Please make sure to enable Java assertions before running the program! To do this, add `-ea` to the VM options.

## To play game:
To move the boat use arrow keys or W, A, S and D. Collect keys of door colours to unlock doors of that colour and avoid enemy boats. The game also has record and replay capabilities. Press “Record” to start recording the game, press “Load recorded game” to load in a recorded game and use “Step by step” or “Auto replay” to go through a recorded game. 

## Controls:
CTRL-X  - exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level
CTRL-S  - exit the game, saves the game state, game will resume next time the application will be started
CTRL-R  - resume a saved game -- this will pop up a file selector to select a saved game to be loaded
CTRL-1 - start a new game at level 1
CTRL-2 - start a new game at level 2
SPACE - pause the game and display a “game is paused” dialog
ESC - close the “game is paused” dialog and resume the game
UP, DOWN, LEFT, RIGHT ARROWS (W, A , S, D)  -- move Boat within the maze



