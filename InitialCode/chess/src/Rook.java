public class Rook extends ChessPiece {

	public Rook(Player player) {
		super(player);
	}

	public String type() {
		return "Rook";
	}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			int colDif = move.toColumn - move.toColumn;
			int rowDif = move.toRow - move.toRow;
			if (colDif != 0 || rowDif == 0) {
				if (colDif > 0) {
					for (int x = 0; x < colDif - 1; x++) {
						if (board[move.fromRow][move.fromColumn + x] != null)
							return false;
					}
				}
				if (rowDif < 0) {
					for (int x = 0; x > colDif + 1; x--) {
						if (board[move.fromRow][move.fromColumn + x] != null)
							return false;
					}
				}
				return true;
			}
			if (colDif == 0 || rowDif != 0) {
				if (rowDif > 0) {
					for (int x = 0; x < rowDif - 1; x++) {
						if (board[move.fromRow + x][move.fromColumn] != null)
							return false;
					}
				}
				if (rowDif < 0) {
					for (int x = 0; x > rowDif + 1; x--) {
						if (board[move.fromRow + x][move.fromColumn] != null)
							return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
}
