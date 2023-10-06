/*****************************************************************
@author Matthew Hocking
@file Knight.java
@purpose This class holds the extended functions related to the
knight chess piece. This includes moves and piece identification
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for getMoveSet variably sized array */
import java.util.ArrayList;

/*****************************************************************
A class to calculate possible moves of a chess knight from a given
location

@author Matthew Hocking
@version Winter 2022
*****************************************************************/
public class Knight extends ChessPiece {

/*****************************************************************
Constructor creates a Knight for the given player
@param player The player to create the new piece for
*****************************************************************/
	public Knight(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "Knight" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "Knight";
	}

/*****************************************************************
Function to check if the given move is valid under the current 
board conditions.
@param move The to and from cords for the move
@param board The current state of the chessgame
@return true if the move is possible and false if not
*****************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board){

		// General Truth Check
		if (super.isValidMove(move, board)) {

			// Calculate the col and row difference between to and from
			int colDif = move.fromColumn - move.toColumn;
			int rowDif = move.fromRow - move.toRow;

			// Checks for the colDif 1 possibilities
			if (colDif == 1 || colDif == -1) {
				if (rowDif == 2 || rowDif == -2)
					return true;
			}
			
			// Checks for the colDif 2 possibilities
			else if (colDif == 2 || colDif == -2) {
				if (rowDif == 1 || rowDif == -1)
					return true;
			}
		}

		// If none of the 8 possibilities are input, return false
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
	public Move[] getMoveSet(int srcX, int srxY, ChessPiece[][] board) {

		// Create empty arraylist of moves
		ArrayList<Move> moves = new ArrayList<Move>();

		// Set initial col difference to -2
		int col = -2;

		// Cycle through every possible col difference
		while (col != 2) {

			// Change col past 0 due to col dif never being 0
			if (col == 0)
				col++;

			// (2 col = 1 row)
			if (col == 2 || col == -2) {
				if (board[srcX][srxY].isValidMove(new Move(srcX, srxY, srcX + col, srxY + 1), board)) {
					moves.add(new Move(srcX, srxY, srcX + col, srxY + 1));
				}
				if (board[srcX][srxY].isValidMove(new Move(srcX, srxY, srcX + col, srxY - 1), board)) {
					moves.add(new Move(srcX, srxY, srcX + col, srxY - 1));
				}
			}

			// (1 row = 2 col)
			if (col == 1 || col == -1) {
				if (board[srcX][srxY].isValidMove(new Move(srcX, srxY, srcX + col, srxY + 2), board)) {
					moves.add(new Move(srcX, srxY, srcX + col, srxY + 2));
				}
				if (board[srcX][srxY].isValidMove(new Move(srcX, srxY, srcX + col, srxY - 2), board)) {
					moves.add(new Move(srcX, srxY, srcX + col, srxY - 2));
				}
			}

			// Increment col
			col++;
		}

		// Return the arraylist converted to an array
		return moves.toArray(new Move[0]);
	}

}
