/*****************************************************************
@author Matthew Hocking
@file Move.java
@purpose This class holds the to and from cords for any given move. 
This is used for undoing and executing moves. 
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
	}

	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
	
	
}
