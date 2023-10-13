public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	public String type() {
		return "King";
	}
	
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			int colDif = move.toColumn - move.toColumn;
			int rowDif = move.toRow - move.toRow;
			if (colDif == 1 || colDif == -1) {
				if (rowDif == 1 || rowDif == -1) {
					return true;
				}
			}
		}
		return false;
	}
}
