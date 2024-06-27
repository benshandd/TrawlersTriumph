
<a name="readme-top"></a>
[![Contributors][contributors-shield]][contributors-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
<h3 align="center">Trawlers Triumph </h3>

  <p align="center">
    A top-down grid-based arcade-style game where you control a fishing boat.
    <br />
    <a href="https://github.com/benshandd/TrawlersTriumph"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://google.com">View Demo</a>
    ·
    <a href="https://github.com/benshandd/TrawlersTriumph/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/benshandd/TrawlersTriumph/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>


<!-- ABOUT THE PROJECT -->
## About The Project

Reel It In is a top-down grid-based arcade-style game where you control a fishing boat. Your goal is to catch all the fish and return them to the dock to allow you through to the whirlpool portal that teleports you to the next level. This repo contains the Java source code.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With
* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=coffeescript&logoColor=white) 
* [![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)


<!-- DEMO -->
## Demo

![Reel it in GIF](demo.gif)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- INSTALLATION AND USAGE -->
## Installation and Usage

To get a local copy up and running follow these simple steps.

### Prerequisites

* Java 8
* JUnit5 or above

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Tokeley/Reel-It-In.git
   ```
2. Make sure JUnit5 or above is installed and assertions are enabled
3. Run the main method located in: `nz/ac/wgtn/swen225/lc/app/main/Main.java`

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- TO PLAY GAME -->
## To play game

To move the boat, use arrow keys or W, A, S, and D. Collect keys of door colors to unlock doors of that color and avoid enemy boats. The game also has record and replay capabilities. Press “Record” to start recording the game, press “Load recorded game” to load in a recorded game, and use “Step by step” or “Auto replay” to go through a recorded game.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTROLS -->
## Controls

- CTRL-X  - exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level
- CTRL-S  - exit the game, saves the game state, game will resume next time the application will be started
- CTRL-R  - resume a saved game -- this will pop up a file selector to select a saved game to be loaded
- CTRL-1 - start a new game at level 1
- CTRL-2 - start a new game at level 2
- SPACE - pause the game and display a “game is paused” dialog
- ESC - close the “game is paused” dialog and resume the game
- UP, DOWN, LEFT, RIGHT ARROWS (W, A , S, D)  -- move Boat within the maze

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- WHAT I LEARNED -->
## What I learned

- **Model-View-Controller Design Pattern:** This game adheres to the Model-View-Controller (MVC) design pattern, emphasizing a clear separation of concerns and streamlined maintainability. The Model encapsulates the game board's underlying data and logic, notably the 2D array of Tiles within the Domain module. The view is the Renderer module is dedicated to presenting this game board state through the graphical user interface (GUI). The Controller's role is embodied by the App module, managing user input and orchestrating Model updates.
- **Unit Testing:** This project contains comprehensive unit testing using JUnit5. The Domain, Persistency, and Recorder Modules all have unit tests that use assert statements to check the correctness of methods, field values after an operation, and the logic of these modules. Using unit tests meant we could be confident in our program's functionality after making changes.
- **Java Swing:** Researching Swing's components and functionalities was followed by hands-on practice in the development of the game's user interface. The end product was a clean, intuitive, and engaging design for the final game. 

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTORS -->
## Contributors

- Ben Shand ([@benshandd](https://github.com/benshandd))
- Tokeley ([@tokeley](https://github.com/tokeley))
- SlazengerV100 ([@slazengerv100](https://github.com/slazengerv100))
- MathiasSCode ([@mathiasscode](https://github.com/mathiasscode))


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Tokeley/Reel-It-In.svg?style=for-the-badge
[contributors-url]: https://github.com/Tokeley/Reel-It-In/graphs/contributors
[stars-shield]: https://img.shields.io/github/stars/Tokeley/Reel-It-In.svg?style=for-the-badge
[stars-url]: https://github.com/Tokeley/Reel-It-In/stargazers
[issues-shield]: https://img.shields.io/github/issues/Tokeley/Reel-It-In.svg?style=for-the-badge
[issues-url]: https://github.com/Tokeley/Reel-It-In/issues
[license-shield]: https://img.shields.io/github/license/Tokeley/Reel-It-In.svg?style=for-the-badge
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/tokeley/
