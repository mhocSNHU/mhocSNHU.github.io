/*****************************************************************
@author Matthew Hocking
@file Player.java
@purpose This class defines a enum used to cycle between the black
and white player. This could be simplified to 1 and 2, but black
and white creates a easier enviorment to identify the player.
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

public enum Player {
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 *
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		if (this == BLACK)
			return WHITE;
		else
		 	return BLACK;
	}
}