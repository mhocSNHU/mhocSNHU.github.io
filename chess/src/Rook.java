/*****************************************************************
@author Matthew Hocking
@file Rook.java
@purpose This class holds the extended functions related to the
Rook chess piece. This includes moves and piece identification
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for getMoveSet variably sized array */
import java.util.ArrayList;

/*****************************************************************
A class to calculate possible moves of a chess Rook from a given
location

@author Matthew Hocking
@version Winter 2022
*****************************************************************/
public class Rook extends ChessPiece {


/*****************************************************************
Constructor creates a Bishop for the given player
@param player The player to create the new piece for
*****************************************************************/
	public Rook(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "Rook" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "Rook";
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

			// If the Rook is moving along the cols
			if (colDif != 0 && rowDif == 0) {

				// Positive col
				if (colDif > 0) {
					for (int x = 1; x <= colDif - 1; x++) {
						if (board[move.fromRow][move.fromColumn - x] != null)
							return false;
					}
				}

				// Negative col
				if (colDif < 0) {
					for (int x = -1; x >= colDif + 1; x--) {
						if (board[move.fromRow][move.fromColumn - x] != null)
							return false;
					}
				}
				return true;
			}
	
			// If the Rook is moving along the rows
			if (colDif == 0 && rowDif != 0) {

				// Positive row
				if (rowDif > 0) {
					for (int x = 1; x <= rowDif - 1; x++) {
						if (board[move.fromRow - x][move.fromColumn] != null)
							return false;
					}
				}

				// Negative row
				if (rowDif < 0) {
					for (int x = -1; x >= rowDif + 1; x--) {
						if (board[move.fromRow - x][move.fromColumn] != null)
							return false;
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

		// Add all moves along the srcX row
		for (int z = 0; z <= 7; z++) {
			possibleMoves.add(new Move(srcX, srcY, z, srcY));
		}

		// Add all moves along the srcY row
		for (int z = 0; z <= 7; z++) {
			possibleMoves.add(new Move(srcX, srcY, srcX, z));
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
