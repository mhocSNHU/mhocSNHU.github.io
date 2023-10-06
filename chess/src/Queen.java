/*****************************************************************
@author Matthew Hocking
@file Queen.java
@purpose This class holds the extended functions related to the
Queen chess piece. This includes moves and piece identification
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for getMoveSet variably sized array */
import java.util.ArrayList;

/** Used for arrays */
import java.util.*;

/*****************************************************************
A class to calculate possible moves of a chess Queen from a given
location

@author Matthew Hocking
@version Winter 2022
*****************************************************************/
public class Queen extends ChessPiece {

/*****************************************************************
Constructor creates a Queen for the given player
@param player The player to create the new piece for
*****************************************************************/
	public Queen(Player player) {
		super(player);
	}

/*****************************************************************
A getter that returns the type of piece being called
@return "Queen" being the chess piece of the class
*****************************************************************/
	public String type() {
		return "Queen";
	}
	
/*****************************************************************
Function to check if the given move is valid under the current 
board conditions.
@param move The to and from cords for the move
@param board The current state of the chessgame
@return true if the move is possible and false if not
*****************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {

		// Queen can be broken into Rook and Bishop
			// Create temp pieces of those types
		Rook RookHolder = new Rook(this.player());
		Bishop BishopHolder = new Bishop(this.player());
		ChessPiece QueenHold = board[move.fromRow][move.fromColumn];

		// If the move is valid for the Rook or Bishop
		if (super.isValidMove(move, board)) {
			board[move.fromRow][move.fromColumn] = RookHolder;
			if (RookHolder.isValidMove(move,board)) {
				board[move.fromRow][move.fromColumn] = QueenHold;
				return true;
			}
			board[move.fromRow][move.fromColumn] = BishopHolder;
			if (BishopHolder.isValidMove(move,board)) {
				board[move.fromRow][move.fromColumn] = QueenHold;
				return true;
			}
			board[move.fromRow][move.fromColumn] = QueenHold;
		}

		// If the move cannot be completed by a Rook or Bishop it fails
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

		// Create temporary pieces for both possible movetypes in queen
			// (Rook/Bishop)
		Bishop TempBish = new Bishop(this.player());
		Rook TempRook = new Rook(this.player());
		ChessPiece TempQueen = this;

		// Adds all possible moves from the rook and bishop arrays
		board[srcX][srcY] = TempBish;
		possibleMoves.addAll(Arrays.asList(TempBish.getMoveSet(srcX, srcY, board)));
		board[srcX][srcY] = TempRook;
		possibleMoves.addAll(Arrays.asList(TempRook.getMoveSet(srcX, srcY, board)));
		board[srcX][srcY] = TempQueen;
		
		
		// Return the arraylist converted to an array
		return possibleMoves.toArray(new Move[0]);
	}
}
