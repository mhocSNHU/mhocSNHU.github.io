# Chess

**Preface:**

This document will break down the primary additions made to the chess application during the enhancement process. As the majority of the application functionality takes place in a UI, there will be no list of commands associated with this enhancement. 

**The Application:**

The initial application made use of a base Chess Model class to interact with a UI to simulate a chess game between two players. The initial version of the game lacked some of the features that a user may want in a simple chess app, such as AI and basic statistics about the current state of the game (turn/move history).
This project moved to amend that by adding these features. 

![Database Tablevs](/assets/images/ChessMainPanel.png)


**Information Additions:**

The user display was altered to give the user the ability to see the current turn count and the move history. These changes required adapting a relatively static display to a more dynamic layout to allow for information aside from the raw chessboard to be displayed. This addition also led to the creation of the new TurnHolder class.

![Database Tablevs](/assets/images/MoveHistory.png)

**AI:**

The major addition for this artifact was an AI capable of being a proper opponent. This meant that the AI had to be more developed then simply following a few rudementary set of rules. I opted to pursue a MinMax algorithm to allow the AI to search through every possible outcome to determine the optimal move to make each turn based off of a points system.
I make the current implementation only look through a single set of turns, but making the AI recursively look through more would be trivial. I avoided making the AI look through too many future turns due to the exponential nature of looking into so many branching paths. 

![Database Tablevs](/assets/images/MinMax.png)

<a href="https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/#"> MinMax Image Source / Resource Used For MinMax Algorithm</a>

<a href="https://www.cs.cornell.edu/boom/2004sp/ProjectArch/Chess/algorithms.html"> Additional Resource Used For MinMax Algorithm</a>

Chess Initial Codebase:

<a href="https://github.com/mhocSNHU/mhocSNHU.github.io/tree/main/InitialCode/chess/src/">Initial Codebase</a>

Chess Initial Code Download:

<a href="https://mhocsnhu.github.io/InitialCode/InitialChess.zip">Initial Codebase Download</a>

Chess Enhanced Codebase:

<a href="https://github.com/mhocSNHU/mhocSNHU.github.io/tree/main/chess">Enhanced Codebase</a>

Chess Enhanced Code Download:

<a href="https://mhocsnhu.github.io/chess/EnchancedChess.zip">Enhanced Codebase Download</a>

Narrative:

<a href="https://mhocsnhu.github.io/narratives/HockingW4Nar"> Week 4 Narrative</a>
