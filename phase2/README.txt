The project does not have any additional plugins. It should work right out of the box. 
See the current directory for UML diagrams of our project classes.

Game Instructions:

Level 1 (Flying Game):
Tap to move the ball upwards. Your goal is to collect balls that give you points. 
The two types of balls are shown in the legend below. One gives you 1 point the other gives you 10. 
10 points are required to move on so collecting one 10 point ball automatically finishes the level. 
The Third type of ball makes you lose a life, lose all your lives and its game over. 
Time is also tracked during this stage which will affect your final score.

Level 2 (Maze Game):
Drag the player left, right, upwards, or downwards to navigate through the maze and make it to 
the square representing the exit point of the maze. The goal is to complete the maze in under 10 
or 15 seconds, and the player loses 1 life if it takes longer, and is forced to go back to the 
start of the maze. There’s a trick to automatically finish the maze by clicking in a certain 
area of the screen and immediately move to the next level. The player’s time along with number 
of lives they have left will determine their score.

Level 3 (Shooting Game): 
The player moves horizontally at the bottom of the screen. Tap the left or right buttons to 
change the direction of the player’s motion. There are dementors falling from the top of the 
screen; the user is supposed to tap the shoot button to shoot blasts toward the dementors. 
If a dementor is hit it disappears. If a dementor reaches the bottom of the screen, the player loses a life.
The objective of the level is to reach the goal of 5 dementors killed.
There is a special green object that falls from the top of the screen; if the player shoots 
that object they win immediately. Time is also tracked during this level which will, along with the 
number of remaining lives, determine the user’s final score.

Stats Information:
Lives:
A player has a set number of lives depending on which level difficulty selected - 10 initial lives 
for Easy level difficulty, and 5 initial lives for Hard level difficulty
Time:
Every level contains a timer tracking how long the player takes to finish the game
Points:
The final score is calculated using the number of lives a player has left and their total time 
is taken after winning the game. Every player starts with 10,000 points, and for every 10 seconds taken 
to finish the game, 100 points are deducted. In addition, a percentage of (lives left)/(initial lives) is 
calculated based on which level difficulty the player selected. The player keeps this percentage of the 
points left after deducting 100 for every 10 seconds played. For example, a player who chooses easy level 
difficulty takes 60 seconds to complete the game and has 5 lives left gets the following score:
(10000 - (6 * 100)) * (5/10) = 4700 points.
