public abstract class ChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (boundCheck(move.fromRow, move.fromColumn) 
			&& boundCheck(move.toRow, move.toColumn)){
				if (dupeCheck(move)) {
					if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].owner != owner)
						return true;
				}
			}
		return false;
	}

	private boolean boundCheck(int x, int y) {
		if (x >= 0 && x <= 7)
			if (y >= 0 && y <= 7)
				return true;
		return false;
	}

	private boolean dupeCheck(Move move) {
		int ToCord[] = {move.toRow, move.toColumn};
		int FromCord[] = {move.fromRow, move.fromColumn};
		if (ToCord != FromCord)
			return true;
		return false;
	}

}
