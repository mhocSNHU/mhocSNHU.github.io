/*****************************************************************
@author Matthew Hocking
@file ChessModel.java
@purpose A class to store the state of the chess game and progress or 
regress the game from that point. This servers as the main point of storage
for where the board state and the current player is stored. Any function to 
make changes to this state is also included. 
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for arrays */
import java.util.*;

public class ChessModel {	 

	/** The current state of the game */
    private ChessPiece[][] board;

	/** The current player who is making a turn */
	private Player player;

	/** Stack of all position changes */
	Stack<Move> MoveStack = new Stack<Move>();

	/** Stack of store what was at a point */
	Stack<ChessPiece> PastPiece = new Stack<ChessPiece>();

	/** Stack of all items deleted during a position change */
	Stack<ChessPiece> CaptureStack = new Stack<ChessPiece>();

	/** The current optimal move by points used by the AI to determine its next move */
	private Move currentMove;

	

/*****************************************************************
Constructor creates a chessgame with all pieces at default
*****************************************************************/
	public ChessModel() {
		board = new ChessPiece[8][8];
		player = Player.WHITE; // Initial player is always white

		// Piece placing
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[0][0] = new Rook(Player.BLACK);
        board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[7][0] = new Rook(Player.WHITE);

		// Loops for pawn placements
		for (int x = 0; x <= 7; x++) {
			board[6][x] = new Pawn(Player.WHITE);
		}
		for (int x = 0; x <= 7; x++) {
			board[1][x] = new Pawn(Player.BLACK);
		}
	}

/*****************************************************************
Checks to see if a player is in checkmate
@return true if the game is over (checkmate), false if otherwise
*****************************************************************/
	public boolean isComplete() {

		// Check to see if either player is in check
		Player checkedPlayer = player;

		// For every possible location on the board
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				
				// Check for a piece friendly to the checked player
				if (board[r][c] != null && board[r][c].player() == checkedPlayer) {

					// For every possible move that the piece can make
					for (Move possibleMove : board[r][c].getMoveSet(r, c, board)) {

						// Temporarly do the move
						move(possibleMove);

						// Check to see if the player is still in check
						if (!inCheck(checkedPlayer)) {

							// Undo said move and return false if the move gets the player out of check
							undo();
							return false;
						}
		                
		 				// Undo the move and continue the loop if the player is still in check
						undo();
					}
				}	
			}
		}

		// If no move gets the player out of check, return true due to it being checkmate
		return true;					
	}

/*****************************************************************
Checks to see if a move is valid
@param move The to and from that is being check
@return True if the move is completable, false otherwise
*****************************************************************/
public boolean isValidMove(Move move) {

	// General Check
	if (board[move.fromRow][move.fromColumn] != null && board[move.fromRow][move.fromColumn].isValidMove(move, board)) {

		// If moving into check logic
		MoveStack.push(move);
		CaptureStack.push(board[move.toRow][move.toColumn]);
		PastPiece.push(board[move.fromRow][move.fromColumn]);

		// Temporary move
		if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player() != board[move.fromRow][move.fromColumn].player()) {
			board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
			board[move.fromRow][move.fromColumn] = null;
		}

		// If the move puts the player in check return false
		if (inCheck(player)) {
			undo();
			setNextPlayer();
			return false;
		}

		// If the move doesnt put the player in checkmate return true
		else {
			undo();
			return true;
		}	
	}

	// If the move doesnt pass the default checks, its false
	return false;
}

/*****************************************************************
Moves a piece and computes game logic for a given move
@param move The move to compute
@return none
*****************************************************************/
	public void move(Move move) {
		// Push both the move and destination to the stacks
		MoveStack.push(move);
		CaptureStack.push(board[move.toRow][move.toColumn]);
		PastPiece.push(board[move.fromRow][move.fromColumn]);

		// Move logic
		if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player() != board[move.fromRow][move.fromColumn].player()) {
			board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
			board[move.fromRow][move.fromColumn] = null;

			// Promotions
			if (board[move.toRow][move.toColumn].type() == "Pawn") {
				if (move.toRow == 7 || move.toRow == 0) {
					board[move.toRow][move.toColumn] = new Queen(board[move.toRow][move.toColumn].player());
				}
			}
		}
	}

/*****************************************************************
Checks to see if a given player is in check
@param p the player to compute if they are in check or not
@return true is they are in check, false otherwise
*****************************************************************/
	public boolean inCheck(Player p) {

		// Position cordinates of the king
		int kingrow = 0;
		int kingcol = 0;

		// Find the king of p's position and save it
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				if (board[r][c] != null && board[r][c].type() == "King" && board[r][c].player() == p) {
					kingrow = r;
					kingcol = c;
				}
			}
		}

		// Check to see if any piece not owned by p can capture their king
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				if (board[r][c] != null && board[r][c].player() != p && board[r][c].isValidMove(new Move(r, c, kingrow, kingcol), board))
					return true;
			}
		}

		// If no piece can capture p's king, they are not in check
		return false;
	}

/*****************************************************************
Getter for the current player
@return Returns the player of the current turn
*****************************************************************/
	public Player currentPlayer() {
		return player;
	}

/*****************************************************************
Returns the number of rows in the game
@return Returns 8
*****************************************************************/
	public int numRows() {
		return 8;
	}

/*****************************************************************
Returns the number of cols in the game
@return Returns 8
*****************************************************************/
	public int numColumns() {
		return 8;
	}

/*****************************************************************
A function to give the user the piece at a given location
@param row the x cord of the desired piece
@param cord the y cord of the desired piece
@return The piece at the given row and col
*****************************************************************/
	public ChessPiece pieceAt(int row, int column) {		
		return board[row][column];
	}

/*****************************************************************
Changes the current player to the opposite side
*****************************************************************/
	public void setNextPlayer() {
		player = player.next();
	}

/*****************************************************************
Changes the piece at a given location to another piece
@param row the x cord to change pieces at
@param column the y cord to change pieces at
@param piece the piece to put at the given location
@return none
*****************************************************************/
	public void setPiece(int row, int column, ChessPiece piece) {
		board[row][column] = piece;
	}

/*****************************************************************
Changes the boardstate to the previous one held in the stack
*****************************************************************/
	public void undo() {

		// If the stack is not completely empty
		if (!MoveStack.empty()) {

			// Take the two top values
			Move undoVal = MoveStack.peek();
			ChessPiece Captured = CaptureStack.peek();
			ChessPiece Past = PastPiece.peek();

			// Change the boardstate to how it was pre-move
			board[undoVal.fromRow][undoVal.fromColumn] = Past;
			board[undoVal.toRow][undoVal.toColumn] = Captured;

			// Remove the values from the stack
			MoveStack.pop();
			CaptureStack.pop();
			PastPiece.pop();

			// Game logic
			setNextPlayer();
		}
	}

/*****************************************************************
The base AI controller that calculates the AI turn
*****************************************************************/
	public void AI() {

		// Holds the current player for future safeguards
		Player playerSave = player;

		// Gets the optimal move with both players in mind
		getMAX(pieceCounts(player), pieceCounts(player.next()), playerSave);

		// Execute the optimal move and continue to the next player
		move(currentMove);
		currentMove = null;
		player = playerSave;
	}

/*****************************************************************
Calculates the MAX (best move AI can make)
@param CurrentPlayerCounts The base player piece counts (want these high)
@param EnemyPlayerCounts The base player piece counts (want these low)
@param pSave The player at the start (used to hold player during undo)
@return none
*****************************************************************/
	public void getMAX(Dictionary<String, Integer> CurrentPlayerCounts, Dictionary<String, Integer> EnemyPlayerCounts, Player pSave) {

		// Set the default best possible move to be unobtainably low
		int currentEval = -1000;

		// For each row
		for (int r = 0; r < numRows(); r++) {

			// For each column
			for (int c = 0; c < numColumns(); c++) {

				// If the piece at that point on the board is owned by the player being evaluated
                if (board[r][c] != null && board[r][c].player() == currentPlayer()) {

					// Check to see what valid moves that piece has
                    Move[] possibleMoves = board[r][c].getMoveSet(r, c, board);

						// If it does have valid moves - check each move
						for (int m = 0; m < possibleMoves.length; m++) {

							// Execute the move in a theoritical enviorment
							move(possibleMoves[m]);

							// Get the minimum value move from the opponent
							int currentPoint = getMIN(CurrentPlayerCounts, EnemyPlayerCounts, pSave);

							// If this new score is better then the previous best - replace the move to execute
							if (currentPoint > currentEval) {
								currentEval = currentPoint;
								currentMove = possibleMoves[m];
							}

							// If the score is equal to the current best - pick randomly based on a random bool
							else if (currentPoint == currentEval) {
								Random randomBool = new Random();
								if (randomBool.nextBoolean()) {
									currentMove = possibleMoves[m];
								}
							}

							// undo the theoritical move
							undo();

							// safeguard the current player
							player = pSave;
						}
                }

            }
        }
	}

/*****************************************************************
Calculates the MIN (best move opponent can make against the AI)
@param CurrentPlayerCounts The base player piece counts (want these high)
@param EnemyPlayerCounts The base player piece counts (want these low)
@param pSave The player at the start (used to hold player during undo)
@return none
*****************************************************************/
	public int getMIN(Dictionary<String, Integer> CurrentPlayerCounts, Dictionary<String, Integer> EnemyPlayerCounts, Player pSave) {

		// Set the default best possible move to be unobtainably low
		int currentBest = 1000;

		// For each row
		for (int r = 0; r < numRows(); r++) {

			// For each column
			for (int c = 0; c < numColumns(); c++) {

				// If the piece at that point on the board is owned by the player being evaluated
                if (board[r][c] != null && board[r][c].player() != currentPlayer()) {

					// If it does have valid moves - check each move
                    Move[] possibleMoves = board[r][c].getMoveSet(r, c, board);

						// Execute the move in a theoritical enviorment
						for (int m = 0; m < possibleMoves.length; m++) {
							move(possibleMoves[m]);

							// Evaluate this new board state after both players have taken a move
							int currentEval = evaluateBoard(CurrentPlayerCounts, EnemyPlayerCounts);

							// The the new move presents a LOWER net gain - we use this
							// We do this because we assume the enemy always takes the optimal route
							if (currentEval < currentBest) {
								currentBest = currentEval;
							}

							// Undo the move and safeguard the player
							undo();
							player = pSave;
						}
                }

            }
        }

		// return the worst outcome
		return currentBest;
	}

/*****************************************************************
Generates a weight value based upon piece count changes
@param CurrentPlayerCounts The base player piece counts (want these high)
@param EnemyPlayerCounts The base player piece counts (want these low)
@return An integer value representing how good the turn outcome is
*****************************************************************/
	public int evaluateBoard(Dictionary<String, Integer> CurrentPlayerCounts, Dictionary<String, Integer> EnemyPlayerCounts) {

		// Create two new dicts based off the new board state
		Dictionary<String, Integer> newPieceCounts = pieceCounts(player);
		Dictionary<String, Integer> newEnemyCounts = pieceCounts(player.next());

		// Results always start at 0
		int evaluationResult = 0;

		// Evaluation Table - The first set removes points based on lost pieces and the second adds points based on enemy lost pieces
		// Due to how this works - a pawn promotion adds points in the first set (since its 0-1 or current - (-1))

		evaluationResult -= ((CurrentPlayerCounts.get("Pawn") - newPieceCounts.get("Pawn")) * 1); // Calculate any Pawn loses at a weight of 1
		evaluationResult -= ((CurrentPlayerCounts.get("Knight") - newPieceCounts.get("Knight")) * 3); // Calculate any Knight loses at a weight of 3
		evaluationResult -= ((CurrentPlayerCounts.get("Rook") - newPieceCounts.get("Rook")) * 4); // Calculate any pawn Rook at a weight of 4
		evaluationResult -= ((CurrentPlayerCounts.get("Bishop") - newPieceCounts.get("Bishop")) * 6); // Calculate any Bishop loses at a weight of 6
		evaluationResult -= ((CurrentPlayerCounts.get("Queen") - newPieceCounts.get("Queen")) * 10); // Calculate any Queen loses at a weight of 10
		evaluationResult -= ((CurrentPlayerCounts.get("King") - newPieceCounts.get("King")) * 100); // Calculate any King loses at a weight of 100

		
		evaluationResult += ((EnemyPlayerCounts.get("Pawn") - newEnemyCounts.get("Pawn")) * 1); // Calculate any Pawn gains at a weight of 1
		evaluationResult += ((EnemyPlayerCounts.get("Knight") - newEnemyCounts.get("Knight")) * 3); // Calculate any Knight gains at a weight of 3
		evaluationResult += ((EnemyPlayerCounts.get("Rook") - newEnemyCounts.get("Rook")) * 4); // Calculate any Rook gains at a weight of 4
		evaluationResult += ((EnemyPlayerCounts.get("Bishop") - newEnemyCounts.get("Bishop")) * 6); // Calculate any Bishop gains at a weight of 6
		evaluationResult += ((EnemyPlayerCounts.get("Queen") - newEnemyCounts.get("Queen")) * 10); // Calculate any Queen gains at a weight of 10
		evaluationResult += ((EnemyPlayerCounts.get("King") - newEnemyCounts.get("King")) * 100); // Calculate any King gains at a weight of 100

		// return the generated results
		return evaluationResult;

	}

/*****************************************************************
Generates a weight value based upon piece count changes
@param player the player to generate the piece count dict for
@return A dict with each piece type and the number present
*****************************************************************/
	public Dictionary<String, Integer> pieceCounts(Player player) {

		// Generate a new hashtable to hold the counts
		Dictionary<String, Integer> pieceCounts = new Hashtable<>() {
		};

		// Put values in the hashtable starting at 0 incase some are not found
		pieceCounts.put("Pawn", 0);
		pieceCounts.put("Rook", 0);
		pieceCounts.put("Bishop", 0);
		pieceCounts.put("Knight", 0);
		pieceCounts.put("King", 0);
		pieceCounts.put("Queen", 0);

		// For each row
		for (int r = 0; r < numRows(); r++) {

			// For each column
			for (int c = 0; c < numColumns(); c++) {

				// If a piece is owned by the sought out player
				if (board[r][c] != null && board[r][c].player().equals(player)) {

					// Add that piece to that specific count
					Integer currentValue = pieceCounts.get(board[r][c].type());
					pieceCounts.put(board[r][c].type(), currentValue + 1);
				}
			}
		}

		// Return the generated count list
		return pieceCounts;
	}
}
