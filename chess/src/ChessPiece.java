/*****************************************************************
@author Matthew Hocking
@file ChessPiece.java
@purpose The base level chess piece class never used for individual
move checks. Every chess piece (Pawn/Rook) call upon the super varient
of boundCheck and isValidMove to ensure all moves are valid.
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

public abstract class ChessPiece {

	/** Owner of a given piece */
	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

/*****************************************************************
Gives the user the type of a given piece
@return a string correlating to the piece
*****************************************************************/
	public abstract String type();

/*****************************************************************
A getter for the owner variable
@return The value stored in owner
*****************************************************************/
	public Player player() {
		return owner;
	}

/*****************************************************************
Parent ValidMove check that looks at universal fail conditions
@param move The to and from cord to attempt to move to
@param board the current state of the game
@return True if the move COULD be true, false otherwise
*****************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {

		// Check the bounds of both cords
		if (boundCheck(move.fromRow, move.fromColumn) 
			&& boundCheck(move.toRow, move.toColumn)){

				// Check that the piece is not moving to iteself
				if (dupeCheck(move)) {

					// Check that the piece is not moving to a friendly space
					if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].owner != owner)
						if (board[move.fromRow][move.fromColumn] == this)
							return true;
				}
			}

		// Return false if any condition fails
		return false;
	}

/*****************************************************************
Checks to see if a given x and y are in bounds
@param x the x cord to check for
@param y the y cord to check for
@return True if the cords are in bounds, false otherwise
*****************************************************************/
	private boolean boundCheck(int x, int y) {
		if (x >= 0 && x <= 7)
			if (y >= 0 && y <= 7)
				return true;
		return false;
	}

/*****************************************************************
Checks see if a move is trying to go to itself
@param move The to and from to compare equality of
@return True if the to and from are equal, false otherwise
*****************************************************************/
	private boolean dupeCheck(Move move) {
		int ToCord[] = {move.toRow, move.toColumn};
		int FromCord[] = {move.fromRow, move.fromColumn};
		if (ToCord != FromCord)
			return true;
		return false;
	}

/*****************************************************************
Abstact function to find all possible moves of a piece
@param row the x value to find moves from
@param col the y value to find moves from
@board the current state of the game
@return An array of all possible valid moves for a piece
*****************************************************************/
	public abstract Move[] getMoveSet(int row, int col, ChessPiece[][] board);

}
