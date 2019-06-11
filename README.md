# Lottery-Game

Point Loma Nazarene University
CSC154 - Objects and Elementary Data Structures
Java GUI project

In this project, I was assigned to create a game of my choosing that used a GUI, a separate class, animation and color using painting techniques, action listeners, my own accessor and mutator methods, and the ability to read and write to a file.

When the game initializes, a file containing the player's current winnings and current jackpot is read into the program. To play, the player guesses 4 numbers that will be matched by the program at random. Initiation costs $5, which is deducted from the winnings. However many numbers match determines how much the player wins. If the player guesses 3 or fewer numbers, $5 is added to the jackpot. When the players matches all 4 numbers, the player collects the entire pot and the jackpot value resets to $500. If the player runs out of money, the game is over and the winnings and jackpot are reinitialized to $100 and $1000, respectively.

0 matches = $0
1 match = $1
2 matches = $5
3 matches = $10
4 matches = JACKPOT

The project met all the requirements of the project, but lost a few points for the outcome of the game being almost completely random (I gained those points back with extra credit).
