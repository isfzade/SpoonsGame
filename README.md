# Spoons Game - Android Application
## Introduction
Spoons Game is a non-commercial and not-Play Store-published Android application which developed
by using Jetpack Compose. This game was developed by Isfandiyar Rashidzade in the scope TestDevLab 
seniority level assignment. The assignment was given by Edijs Gorbunovs and completion duration
was two weeks.

The application was delivered in 4 February 2024

## About the Application
The game is mainly composed of menu, game play, high scores screens.

### Menu
The application starts with menu screen which contains 5 buttons;

- Three-player game: a user will start to play game with two bots.
- Four-player game: there will be three bots and a user.
- Five-player game: there will be totally five players and four of them are bots.
- High scores: in this scree user can find won and lost game records.
- Load the latest game: a user can continue to play the latest unfinished game.

<img src="./screenshots/menu.png" alt="drawing" width="100" height="212"/>

### Play Game
Spoons Game is a card game in which players receive a letter from SPOON word
when loosing each round. The game can be played with 3,4 and 5 players.

<img src="./screenshots/play_1.png" alt="drawing" width="100" height="212"/>

When the game is started first time a real user starts first, but later at each round will
start with the next player.

The first player of each round draws a card from available deck cards 
and the the last player discards any card from hand to deck. When available cards are finished
the discarded cards are shuffled and added to available cards.
The other players always gives card to next player. 
After discarding a card the player will have four cards.

A round is finished when a player will have four same suits (i.e. four Kings). 
The player with four same suit is the winner of round. Following logic will happen
depending on round winner.

- The round winner is the real user a random bot receives a letter
- If round winner is a bot and there are more than 2 players in round then.
the real user will see "Take spoon" button for 1 second on screen.
  - If the real user will able to click the button then a random bot (except the winner)
  will receive a letter.
  - If the will not click the button then the user will receive a letter.
- If round winner is a bot and only 2 players left in round then the real user receives a letter.

## Reference
Card images was taken from:

- @hanhaechi https://github.com/hanhaechi/playing-cards/tree/master