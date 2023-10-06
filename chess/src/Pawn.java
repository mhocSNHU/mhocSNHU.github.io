/*****************************************************************
@author Matthew Hocking
@file Pawn.java
@purpose This class holds the extended functions related to the
Pawn chess piece. This includes moves and piece identification
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
public class Pawn extends ChessPiece {
 
	/** Rows that black and white can double move from */
	private int blackRow = 1, whiteRow = 6;

/*****************************************************************
Constructor creates a Pawn for the given player
@param player The player to create the new piece for
*****************************************************************/
	public Pawn(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "Pawn" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "Pawn";
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
		if (super.isValidMove(move, board) && board[move.fromRow][move.fromColumn].type() == type()) {

			// Calculate the col and row difference between to and from
			int colDif = move.fromColumn - move.toColumn;
			int rowDif = move.fromRow - move.toRow;

			// Unique move: Starting line
			if (this.player() == Player.WHITE && move.fromRow == 6) {
				if (rowDif == 2 && colDif == 0)
					if (board[move.fromRow-1][move.fromColumn] == null)
						if (board[move.toRow][move.toColumn] == null)
						return true;
			}
			if (this.player() == Player.BLACK && move.fromRow == 1) {
				if (rowDif == -2 && colDif == 0)
					if (board[move.fromRow+1][move.fromColumn] == null)
						if (board[move.toRow][move.toColumn] == null)
						return true;
			}

			// General move
			if (this.player() == Player.WHITE && rowDif == 1 && colDif == 0) {
				if (board[move.toRow][move.toColumn] == null)
					return true;
			}
			if (this.player() == Player.BLACK && rowDif == -1 && colDif == 0) {
				if (board[move.toRow][move.toColumn] == null)
					return true;
			}

			// Unique move: Attack
			if (this.player() == Player.WHITE && rowDif == 1) {
				if (colDif == 1 || colDif == -1) {
					if (board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.BLACK)
						return true;
					}
			}
			if (this.player() == Player.BLACK && rowDif == -1) {
				if (colDif == 1 || colDif == -1){
					if (board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.WHITE)
						return true;
					}
			}
		}

		// If none of the move conditions are true, the move is invalid
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

		// The pawns direction of movement
		short sign;
		// The doublespace row of a pawn
		int homeRow;

		// Player black or white check
		switch (this.player()) {
			case BLACK:
				// Black is on the top and moves down
				sign = 1;
				homeRow = blackRow;
				break;

			case WHITE:
				// White is on the bottom and moves up
				sign = -1;
				homeRow = whiteRow;
				break;

			// invalid player: return blank array
			default:
				return new Move[0];
		}

		// Move finding algorithm

		// Homerow movement
		if (srcX == homeRow)
			possibleMoves.add(new Move(srcX, srcY, srcX + (sign * 2), srcY));

		// Basic movement
		possibleMoves.add(new Move(srcX, srcY, srcX + (sign * 1), srcY));

		// Attack movement
		possibleMoves.add(new Move(srcX, srcY, srcX + (sign * 1), srcY + 1 ));
		possibleMoves.add(new Move(srcX, srcY, srcX + (sign * 1), srcY - 1 ));

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
