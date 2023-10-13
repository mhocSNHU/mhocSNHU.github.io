public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, ChessPiece[][] board){
		if (super.isValidMove(move, board)) {
			int colDif = move.toColumn - move.toColumn;
			int rowDif = move.toRow - move.toRow;
			if (colDif == 1 || colDif == -1) {
				if (rowDif == 2 || rowDif == -2)
					return true;
			}
			else if (colDif == 2 || colDif == -2) {
				if (rowDif == 1 || rowDif == -1)
					return true;
			}
		}
		return false;	
	}

}
