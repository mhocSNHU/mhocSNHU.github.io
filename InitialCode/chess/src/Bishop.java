public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			int colDif = move.toColumn - move.toColumn;
			int rowDif = move.toRow - move.toRow;
			if (colDif == rowDif && colDif != 0 || colDif == -rowDif && colDif != 0) {
				if (colDif > 0) {
					if (colDif == rowDif) {
						for (int x = 0; x < colDif - 1; x++) {
							if (board[move.fromRow+x][move.fromColumn+x] != null)
								return false;
						}
					}
					if (colDif != rowDif) {
						for (int x = 0; x < colDif - 1; x++) {
							if (board[move.fromRow+x][move.fromColumn-x] != null)
								return false;
						}
					}
				}
				if (colDif < 0) {
					if (colDif == rowDif) {
						for (int x = 0; x > colDif + 1; x--) {
							if (board[move.fromRow+x][move.fromColumn+x] != null)
								return false;
						}
					}
					if (colDif != rowDif) {
						for (int x = 0; x > colDif + 1; x--) {
							if (board[move.fromRow+x][move.fromColumn-x] != null)
								return false;
						}
					}
				}
					return true;
			}
		}
		return false;
	}
}
