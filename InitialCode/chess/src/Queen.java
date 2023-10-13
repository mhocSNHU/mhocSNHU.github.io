public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);
	}

	public String type() {
		return "Queen";
	}
	
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		Rook RookHolder = new Rook(this.player());
		Bishop BishopHolder = new Bishop(this.player());
		if (super.isValidMove(move, board)) {
			if (RookHolder.isValidMove(move,board) || BishopHolder.isValidMove(move,board))
				return true;
		}
		return false;
	}
}
