/*****************************************************************
@author Matthew Hocking
@file Bishop.java
@purpose This class holds the extended functions related to the
bishop chess piece. This includes moves and piece identification
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for getMoveSet variably sized array */
import java.util.ArrayList;

/*****************************************************************
A class to calculate possible moves of a chess bishop from a given
location

@author Matthew Hocking
@version Winter 2022
*****************************************************************/
public class Bishop extends ChessPiece {

/*****************************************************************
Constructor creates a Bishop for the given player
@param player The player to create the new piece for
*****************************************************************/
	public Bishop(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "Bishop" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "Bishop";
	}
	
/*****************************************************************
Function to check if the given move is valid under the current 
board conditions.
@param move The to and from cords for the move
@param board The current state of the chessgame
@return true if the move is possible and false if not
*****************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {

		// General Truth Check
		if (super.isValidMove(move, board)) {

			// Calculate the col and row difference between to and from
			int colDif = move.fromColumn - move.toColumn;
			int rowDif = move.fromRow - move.toRow;

			// Check to make sure Col and row are the same or inversed
			if (colDif == rowDif && colDif != 0 || colDif == -rowDif && colDif != 0) {

				// (+, ())
				if (colDif > 0) {

					// (+, +)
					if (colDif == rowDif) {
						for (int x = 1; x < colDif; x++) {
							if (board[move.fromRow-x][move.fromColumn-x] != null)
								return false;
						}
					}

					// (+, -)
					if (colDif != rowDif) {
						for (int x = 1; x < colDif; x++) {
							if (board[move.fromRow+x][move.fromColumn-x] != null)
								return false;
						}
					}
				}

				// (-, ())
				if (colDif < 0) {
					// (-, -)
					if (colDif == rowDif) {
						for (int x = -1; x > colDif; x--) {
							if (board[move.fromRow-x][move.fromColumn-x] != null)
								return false;
						}
					}

					// (-, +)
					if (colDif != rowDif) {
						for (int x = -1; x > colDif; x--) {
							if (board[move.fromRow+x][move.fromColumn-x] != null)
								return false;
						}
					}
				}
					return true;
			}
		}
		return false;
	}

/*****************************************************************
Checks every possible move of a bishop from srcX, srcY under the
given board conditions
@param srcX The x cord of the piece to check from
@param srcY The y cord of the piece to check from
@param board The current state of the gameboard
@return An array of all possible moves that a piece can do validly
*****************************************************************/
	public Move[] getMoveSet(int srcX, int srcY, ChessPiece[][] board) {
		
		// Create empty arraylist of moves
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		/** Cords to calculate to when find all possible positions */
		int destX, destY;

		// Generate top-left diagonal
		// (0,0) is top left
		destX = srcX;
		destY = srcY;
		while (destX > 0 && destY > 0) {
			possibleMoves.add(new Move(srcX, srcY, --destX, --destY));
		}

		// Generate bottom-right diagonal
		// (7,7) is bottom-right
		destX = srcX;
		destY = srcY;
		while (destX < 7 && destY < 7) {
			possibleMoves.add(new Move(srcX, srcY, ++destX, ++destY));
		}

		// Generate top-right diagonal
		// (0,7) is top-right
		destX = srcX;
		destY = srcY;
		while (destX > 0 && destY < 7) {
			possibleMoves.add(new Move(srcX, srcY, --destX, ++destY));
		}

		// Generate bottom-left diagonal
		// (7,0) is bottom-left
		destX = srcX;
		destY = srcY;
		while (destX < 7 && destY > 0) {
			possibleMoves.add(new Move(srcX, srcY, ++destX, --destY));
		}

		// Narrow down the movesets by checking each move in the arraylist
		for (int m = possibleMoves.size() - 1; m >= 0; m--) {
			if (!isValidMove(possibleMoves.get(m), board)) {
				possibleMoves.remove(m);
			}
		}

		// Return the arraylist converted to an array
		return possibleMoves.toArray(new Move[0]);
	}
}
