public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}

	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			// Starting Pos
			int colDif = move.fromColumn - move.toColumn;
			int rowDif = move.fromRow - move.toRow;
			if (this.player() == Player.WHITE && move.fromRow == 6) {
				if (rowDif == 2)
					if (board[move.fromRow+1][move.fromColumn] == null)
						if (board[move.toRow][move.toColumn] == null)
						return true;
			}
			if (this.player() == Player.BLACK && move.fromRow == 1) {
				if (rowDif == -2)
					if (board[move.fromRow-1][move.fromColumn] == null)
						if (board[move.toRow][move.toColumn] == null)
						return true;
			}

			// Basic Pos
			if (this.player() == Player.WHITE && rowDif == 1) {
				if (board[move.toRow][move.toColumn] == null)
					return true;
			}
			if (this.player() == Player.BLACK && rowDif == -1) {
				if (board[move.toRow][move.toColumn] == null)
					return true;
			}

			// Attack Pos
			if (this.player() == Player.WHITE && rowDif == 1 ||
			this.player() == Player.WHITE && rowDif == 2 && move.fromRow == 1) {
				if (colDif == 1 || colDif == -1){
					if (board[move.toRow][move.toColumn].player() == Player.BLACK);
						return true;
					}
			}
			if (this.player() == Player.BLACK && rowDif == -1 ||
			this.player() == Player.BLACK && rowDif == -2 && move.fromRow == 7) {
				if (colDif == 1 || colDif == -1){
					if (board[move.toRow][move.toColumn].player() == Player.WHITE);
						return true;
					}
			}
		}
		return false;
	}
}
