/*****************************************************************
@author Matthew Hocking
@file King.java
@purpose This class holds the extended functions related to the
king chess piece. This includes moves and piece identification
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for absolute value */
import java.lang.Math;

/** Used for arraylists in getMoveSet */
import java.util.ArrayList;

/** Used for arrays */
import java.util.Arrays;

/*****************************************************************
A class to calculate possible moves of a chess king from a given
location

@author Matthew Hocking
@version Winter 2022
*****************************************************************/
public class King extends ChessPiece {

/*****************************************************************
Constructor creates a King for the given player
@param player The player to create the new piece for
*****************************************************************/
	public King(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "King" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "King";
	}
	
/*****************************************************************
Function to check if the given move is valid under the current 
board conditions.
@param move The to and from cords for the move
@param board The current state of the chessgame
@return true if the move is possible and false if not
*****************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {

		// General truth check
		if (super.isValidMove(move, board)) {

			// Calculate the col and row difference between to and from
			int colDif = move.fromColumn - move.toColumn;
			int rowDif = move.fromRow - move.toRow;
			ChessPiece kingHold = board[move.fromRow][move.fromColumn];
			// Create a queen due to a king being a queen with a range of 1
			Queen QueenHolder = new Queen(this.player());

			// Make sure the king is moving
			if (Math.abs(rowDif) + Math.abs(colDif) != 0)

				// Make sure the king is moving within a range of 1
				if (Math.abs(rowDif) >= 0 && Math.abs(colDif) >= 0 && Math.abs(rowDif) < 2 && Math.abs(colDif) < 2) {

					board[move.fromRow][move.fromColumn] = QueenHolder;

					// Truth check the value from the queen truthcheck
					if (QueenHolder.isValidMove(move, board)) {
						board[move.fromRow][move.fromColumn] = kingHold;

						return true;
					}
				}
			}
		
		// If a queen could not make an identical move, return false
		return false;
	}

/*****************************************************************
Checks every possible move of a King from srcX, srcY under the
given board conditions
@param srcX The x cord of the piece to check from
@param srcY The y cord of the piece to check from
@param board The current state of the gameboard
@return An array of all possible moves that a piece can do validly
*****************************************************************/
	public Move[] getMoveSet(int srcX, int srxY, ChessPiece[][] board) {

		// Create empty arraylist of moves
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		// Create temporary pieces to generate moves from
		Bishop TempBish = new Bishop(this.player());
		Rook TempRook = new Rook(this.player());
		ChessPiece kingHolder = this;

		// Create a list combining all moves possible by those pieces
		board[srcX][srxY] = TempBish;
		possibleMoves.addAll(Arrays.asList(TempBish.getMoveSet(srcX, srxY, board)));
		board[srcX][srxY] = TempRook;
		possibleMoves.addAll(Arrays.asList(TempRook.getMoveSet(srcX, srxY, board)));
		board[srcX][srxY] = kingHolder;

		// Narrow down the movesets by checking each move in the combined moveset
		for (int m = possibleMoves.size() - 1; m >= 0; m--) {
			if (!isValidMove(possibleMoves.get(m), board)) {
				possibleMoves.remove(m);
			}
		}

		// Return the arraylist converted to an array
		return possibleMoves.toArray(new Move[0]);
	}
}
