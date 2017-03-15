Game Project: For the King

@author Xingfan Xia, Russel Smith, Yitong Cheng
@artist Jufei Luo
@Project for CS257 Software Desgin
@Professor Eric Alexander

- What this program does

    Welcome to our game ForTheKing! This is a PVP game called “For the King” with elements of turn-based games, RPG and tower defense games.

    The game allows players to work as a team and fight for the same goal--protect their king and kill the enemy king. The game consists of two main phases, the construct phase, which allows players to construct buildings on their own bases and the fight phase, where minions spawned from the buildings that players built will automatically fight each other.

    This is basically a strategy game, where players need to cooperate with each other and make tactful decisions about where on the base and when to build a building.


- How to Play

    To start the game, run the file WelcomeScreen.java found in source/View. This will take the player to the welcome screen.

    On the welcome screen, players can read the rules of the game by clicking the help button. The rules are also presented here for convenience.
        - Rules: This is a PVP game. Players are divided into two teams, the light and the dark. Each two players from the two teams will be placed at two ends of the same lane on the map and they will fight against each other.
                 To fight, construct buildings in the building phase. Different buildings will automatically spawn different minions with distinctive properties in the fighting phase to fight against the enemy minions. Different buildings, of course, cost different amounts of gold. Gold can be earned by killing enemy minions. Choose the buildings wisely by considering the buildings of your opponent.
                 Each team also has a king that they need to protect. Minions who survive to reach the opponent base on their own lane will be transported to the king lane to protect and fight for the king. The ultimate mission for players on the same team is to cooperate with each other and kill the enemy king.
                 Now go and fight for the king!

    After the player finishes reading the rules, click on the BackToHome button and the player will be taken back to the welcome screen.

    Click on Play to initialize players. Choose the number of players and then enter the names of the players one by one. Remember to choose a color for your player. When you are done, click start to enter the construct building phase for player1.

    Each player has one minute at most each round to construct buildings. Click on the base in the current base panel and the available buildings will show up. Hover on the building to see what kind of minions the building will spawn. Click on the building and the system will ask whether you confirm constructing the selected building at the selected position. Each player starts with 500 gold at the beginning. Choose your buildings wisely.

    The game will loop through all of the players so that everyone can construct their own buildings. Clicking finish before time is up jumps right next to the next player. After the last player finishes constructing building, the game enters the fight phase where minions will spawn from the buildings and fight each other automatically.

    Minions from each side will march toward the enemy base on the same lane and fight anything on their way. If a minion reaches the base, it will be transported to the king lane to fight/protect the kings. Each team can click the buff button to add health to the king.

    After all of the minions are dead for the currect round, the game enter another building phase for all of the players. Players earn gold by killing minions. After this construct phase, there is another fight phase with new minions spawned.

    The game ends when one of the king dies. A window will popup once that happens and click on the see score will allow players to see their individual score. On this page, players can choose to play again or to quit game.


- Known Bugs and Things we wanted to implement but didn't get to

    - When the King dies, the message that informs the players about the death pops up for multiple times. We believe that's because that popUpWindow is constructed for each minion in the game.
    - We hoped to have a variety of minions but were only able to implement two types, the knight and the cupcake warrior. Since we didn't have image for the knight, we didn't include options to build the knight in the current version of the game.
    - Right now, there is a problem if we want to enter the second construct building phase. In the fight phase after the second construct building phase, the images of the buildings are not rendered to the correct place and will wander in the center of the lane. We were not sure what the problem was so we couldn't really fix that.
    - If you initialze 8 players, you might see that the fourth lane has some image-rendering issues. The minions leave a trace where they have gone and the traces don't go away. This is weird because all the other three lanes worked fine.