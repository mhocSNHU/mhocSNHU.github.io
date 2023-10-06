/*****************************************************************
@author Matthew Hocking
@file ChessPanel.java
@purpose Used for all user interface logic
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used for a variety of GUI functionality */
import java.awt.*; 

/** Used for GUI events */
import java.awt.event.*;

/** Used to compliment the GUI and add swing options */
import javax.swing.*;

public class ChessPanel extends JPanel {
    public enum GUIcodes {
        NoMessage, NotYourTurn, inCheck, Checkmate // The current state of the GUI used for pop-ups
    
    }

	// Variable Creation

	// Board representation made of buttons
	private JButton[][] board;

	// Turn information variables
	private JLabel infoMenu;
	private int turnCount = 0;
	private TurnHolder infoStrings;

	// Generation of a fresh chess model
	private ChessModel model;

	// Piece image icons
	private ImageIcon bRook;
	private ImageIcon bBishop;
	private ImageIcon bQueen;
	private ImageIcon bKing;
	private ImageIcon bPawn;
	private ImageIcon bKnight;
	private ImageIcon wRook;
	private ImageIcon wBishop;
	private ImageIcon wQueen;
	private ImageIcon wKing;
	private ImageIcon wPawn;
	private ImageIcon wKnight;

	// GUI upkeep variables
	private GUIcodes messageCode;
	private listener listener;

/*****************************************************************
Constructor for a new chess panel. Should be used for any new UI
*****************************************************************/
	public ChessPanel() {

		// Initialize all variables required for GUI upkeep - this includes a new chessmodel
		infoStrings = new TurnHolder(10);
		messageCode = GUIcodes.NoMessage;
		model = new ChessModel();
		board = new JButton[model.numRows()][model.numColumns()];
		listener = new listener();
		infoMenu = new JLabel();
		updateMenu();
		createIcons();
		
		// Generate the panel used for holding move info
		JPanel textPanel = new JPanel();
		textPanel.add(infoMenu, BorderLayout.NORTH);

		// Generate the panel used for interacting directly with the chess model
		JPanel boardpanel = new JPanel();
		boardpanel.setLayout(new GridLayout(model.numRows(), model.numRows(), 1, 1));

		// Fill in this chess board with images that match the initial state of the chess model
		// Also add listeners so that it may be interacted with
		for (int r = 0; r < model.numRows(); r++) {
			for (int c = 0; c < model.numColumns(); c++) {
				if (model.pieceAt(r, c) == null) {
					board[r][c] = new JButton("", null);
					board[r][c].addActionListener(listener);
				} else if (model.pieceAt(r, c).player() == Player.WHITE)
					placeWhitePieces(r, c);
				else if (model.pieceAt(r, c).player() == Player.BLACK) {
					placeBlackPieces(r, c);
				}
				setBackGroundColor(r, c);
				boardpanel.add(board[r][c]);
			}
		}

		// Combine the two board components into a single window
		boardpanel.setPreferredSize(new Dimension(800, 600));
		textPanel.setPreferredSize(new Dimension(125, 600));
		add(boardpanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.CENTER);
	}

/*****************************************************************
Used for setting the color of the bottons to create the checkboard
pattern
@param r: The row to set the color of
@param c: The column to set the color of
*****************************************************************/
	private void setBackGroundColor(int r, int c) {
		// If the cords lead to a spot transposed to black spaces in either row format - its black
		if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
			board[r][c].setBackground(Color.LIGHT_GRAY);
		// Otherwise - check to see if the location is aligned to white spaces
		} else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
			board[r][c].setBackground(Color.WHITE);
		}
	}

/*****************************************************************
Places each black piece and adds its dedicated listener
As the type should be set by now, only the r and c are required
@param r: The row to set the piece at
@param c: The column to set the piece at
*****************************************************************/
	private void placeBlackPieces(int r, int c) {

		// Pawn Placements
		if (model.pieceAt(r, c).type().equals("Pawn")) {
			board[r][c] = new JButton(null, bPawn);
			board[r][c].addActionListener(listener);
		}

		// Rook Placements
		if (model.pieceAt(r, c).type().equals("Rook")) {
			board[r][c] = new JButton(null, bRook);
			board[r][c].addActionListener(listener);
		}

		// Knight Placements
		if (model.pieceAt(r, c).type().equals("Knight")) {
			board[r][c] = new JButton(null, bKnight);
			board[r][c].addActionListener(listener);
		}

		// Bishop Placements
		if (model.pieceAt(r, c).type().equals("Bishop")) {
			board[r][c] = new JButton(null, bBishop);
			board[r][c].addActionListener(listener);
		}

		// Queen Placements
		if (model.pieceAt(r, c).type().equals("Queen")) {
			board[r][c] = new JButton(null, bQueen);
			board[r][c].addActionListener(listener);
		}

		// King Placements
		if (model.pieceAt(r, c).type().equals("King")) {
			board[r][c] = new JButton(null, bKing);
			board[r][c].addActionListener(listener);
		}
	}

/*****************************************************************
Places each white piece and adds its dedicated listener
As the type should be set by now, only the r and c are required
@param r: The row to set the piece at
@param c: The column to set the piece at
*****************************************************************/
	private void placeWhitePieces(int r, int c) {

		// Pawn Placements
		if (model.pieceAt(r, c).type().equals("Pawn")) {
			board[r][c] = new JButton(null, wPawn);
			board[r][c].addActionListener(listener);
		}

		// Rook Placements
		if (model.pieceAt(r, c).type().equals("Rook")) {
			board[r][c] = new JButton(null, wRook);
			board[r][c].addActionListener(listener);
		}

		// Knight Placements
		if (model.pieceAt(r, c).type().equals("Knight")) {
			board[r][c] = new JButton(null, wKnight);
			board[r][c].addActionListener(listener);
		}

		// Bishop Placements
		if (model.pieceAt(r, c).type().equals("Bishop")) {
			board[r][c] = new JButton(null, wBishop);
			board[r][c].addActionListener(listener);
		}

		// Queen Placements
		if (model.pieceAt(r, c).type().equals("Queen")) {
			board[r][c] = new JButton(null, wQueen);
			board[r][c].addActionListener(listener);
		}

		// King Placements
		if (model.pieceAt(r, c).type().equals("King")) {
			board[r][c] = new JButton(null, wKing);
			board[r][c].addActionListener(listener);
		}
	}

/*****************************************************************
Creates icons based off of a pre-set group of images
*****************************************************************/
	private void createIcons() {
		// Sets the ImageIcon for the Black Player Pieces
		bRook = new ImageIcon("./images/bRook.png");  
		bBishop = new ImageIcon("./images/bBishop.png"); 
		bQueen = new ImageIcon("./images/bQueen.png");  
		bKing = new ImageIcon("./images/bKing.png");
		bPawn = new ImageIcon("./images/bPawn.png");
		bKnight = new ImageIcon("./images/bKnight.png");

		// Sets the Image for white player pieces
		wRook = new ImageIcon("./images/wRook.png");
		wBishop = new ImageIcon("./images/wBishop.png");
		wQueen = new ImageIcon("./images/wQueen.png");
		wKing = new ImageIcon("./images/wKing.png");
		wPawn = new ImageIcon("./images/wPawn.png");
		wKnight = new ImageIcon("./images/wKnight.png");
	}

/*****************************************************************
Displays the board based upon the given state
*****************************************************************/
	private void displayBoard() {

		// For each row
		for (int r = 0; r < 8; r++) {

			// For each column
			for (int c = 0; c < 8; c++) 

				// Set the displayed icon based upon the piece currently there

				// Null pieces are set to no icon
				if (model.pieceAt(r, c) == null) 
					board[r][c].setIcon(null);

				// If the piece is not null, get the individual pieces icon
				else 

					// White players icons
					if (model.pieceAt(r, c).player() == Player.WHITE) {
						if (model.pieceAt(r, c).type().equals("Pawn")) 
							board[r][c].setIcon(wPawn);

						if (model.pieceAt(r, c).type().equals("Rook")) 
							board[r][c].setIcon(wRook);

						if (model.pieceAt(r, c).type().equals("Knight")) 
							board[r][c].setIcon(wKnight);

						if (model.pieceAt(r, c).type().equals("Bishop")) 
							board[r][c].setIcon(wBishop);

						if (model.pieceAt(r, c).type().equals("Queen")) 
							board[r][c].setIcon(wQueen);

						if (model.pieceAt(r, c).type().equals("King")) 
							board[r][c].setIcon(wKing);

					// Black players icons
					} else 
						if (model.pieceAt(r, c).player() == Player.BLACK) {
							if (model.pieceAt(r, c).type().equals("Pawn")) 
								board[r][c].setIcon(bPawn);

							if (model.pieceAt(r, c).type().equals("Rook")) 
								board[r][c].setIcon(bRook);

							if (model.pieceAt(r, c).type().equals("Knight")) 
								board[r][c].setIcon(bKnight);

							if (model.pieceAt(r, c).type().equals("Bishop")) 
								board[r][c].setIcon(bBishop);

							if (model.pieceAt(r, c).type().equals("Queen")) 
								board[r][c].setIcon(bQueen);

							if (model.pieceAt(r, c).type().equals("King")) 
								board[r][c].setIcon(bKing);
						}
		}

		// Update the information menu
		updateMenu();

		// Repaint the view for the user
		repaint();
	}

/*****************************************************************
Updates the menu with info about the current gamestate
*****************************************************************/
	private void updateMenu() {

		// If the player is WHITE - increment the turn
		if (model.currentPlayer().name().equals("WHITE")) {

			turnCount++;
		}

		// Begin the concat string
		String concatString = "";

		// Add the current turn count
		concatString+="<html>Current Turn: " + turnCount + "<br>";

		// Add the value from the infoStrings
		concatString+= infoStrings.returnTurns();

		// Update the menu
		infoMenu.setText(concatString);
	}

/*****************************************************************
Inner class used for action listeners
*****************************************************************/
	private class listener implements ActionListener {

		// Variable declaration
        boolean pieceChosen = false;

		// Move variables - set to -1 to show that it isnt valid to start
		int fromRow = -1;
		int fromColumn = -1;
		int toRow = -1;
		int toColumn = -1;

		// Holding variables
		Move tempMove;
		Color tempColor;

/*****************************************************************
Attemps to process a given event based upon the given board state
@param event: The event to process
*****************************************************************/
		public void actionPerformed(ActionEvent event) {

			// For each row
			for (int r = 0; r < model.numRows(); r++) {

				// For each column
				for (int c = 0; c < model.numColumns(); c++) {

					// If this location is the source of the event
					if (board[r][c] == event.getSource()) {

						// Check to see if the requested event is valid
						if (!pieceChosen) {
							if (model.pieceAt(r, c) != null) 

								// If it is and its the correct player - execute the event
								if (model.pieceAt(r, c).player() == model.currentPlayer()) {
									selectPiece(r, c);

								// Inform the player if its not their turn
								} else {
									messageCode = GUIcodes.NotYourTurn;
									displayMessage();
								}
						}

						else 
							if (pieceChosen) {
								tryMoveTo(r, c);
							}
					}
                }
            }
		}

/*****************************************************************
attempts to move a given piece based upon the given R and C cords
@param r: The row to set the piece at
@param c: The column to set the piece at
*****************************************************************/
		private void tryMoveTo(int r, int c) {

			board[fromRow][fromColumn].setBackground(tempColor);
			toRow = r;
			toColumn = c;

			// Generates a move based upon the given information
			tempMove = new Move(fromRow, fromColumn, toRow, toColumn);

			// If the move is valid based upon the current model
			if (model.isValidMove(tempMove)) {
				model.move(tempMove);

				// If the user is in check - inform the user
				if (model.inCheck(model.currentPlayer())) {
					messageCode = GUIcodes.inCheck;

					// If the user is in checkmate - end the game
					if (model.isComplete()) 
						messageCode = GUIcodes.Checkmate;
				}
					// Add the messagestring to the info panel
					infoStrings.insertNode(model.pieceAt(r,c).type() + " at " + fromRow + ", " + fromColumn + " to " + toRow + ", " + toColumn);

				// Display the board with the updated move
				displayBoard();
			}

			// Make sure the unselect the current piece
			pieceChosen = false;

			// Display any popups
			displayMessage();

			// Allow the AI to take a turn
			// To disable the AI - comment this out
            AITurn();
		}
		
/*****************************************************************
Processes the AI turn if its enabled
*****************************************************************/
        public void AITurn() {

			// Only take the AI turn if its processing for black
			if (model.currentPlayer() == Player.BLACK) {

				// Call upon the models dedicated AI
				model.AI();
    		    messageCode = GUIcodes.NoMessage;

				// Set the player to the next player
				model.setNextPlayer();

				// Update the board based upon the changes made by the dedicated AI
				displayBoard();

				// Complete standard in check and in checkmate calls
				if (model.inCheck(model.currentPlayer())) {
					messageCode = GUIcodes.inCheck;
					if (model.isComplete())
						messageCode = GUIcodes.Checkmate;
				}

				// Reduce the turn-count as the AI does not count
				turnCount--;

				// Standard book-keeping
				displayBoard();
				pieceChosen = false;
				displayMessage();
			}
        }

/*****************************************************************
Function to identify what piece is selected
@param r: The row to set the piece at
@param c: The column to set the piece at
*****************************************************************/
		private void selectPiece(int r, int c) {

			// Set the background color to cyan to identify it as active
			tempColor = board[r][c].getBackground();
			board[r][c].setBackground(Color.CYAN);
			fromRow = r;
			fromColumn = c;
			messageCode = GUIcodes.NoMessage;

			// Set this piece as chosen status true
			pieceChosen = true;
		}

/*****************************************************************
Print out any GUI messages including wrong turn and checkmate
*****************************************************************/
		private void displayMessage() {

			// Wrong turn message
			if (messageCode == GUIcodes.NotYourTurn) {
				JOptionPane.showConfirmDialog(null, "It is not your turn.", 
						"Warning", JOptionPane.CLOSED_OPTION);
			}

			// Checkmate message
			if (messageCode == GUIcodes.Checkmate) {
				JOptionPane.showConfirmDialog(null, "Checkmate.", 
						"Warning", JOptionPane.CLOSED_OPTION);
			}
		}

	}
}
