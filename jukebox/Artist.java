/*************************************************************************
 * @author Matthew Hocking
 * @file Artist.java
 * @purpose Holds song info - creating a structured location for songs
 * Songs are the primary component of playlists. They also use artists as a connection
 * to hold a bit more information
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

public class Artist {

	private String name; // Song name
	private int ID; // artist ID - primary key in database
	
	/****************************************************
	* Base level constructor for creating a song
	* 
	* @author Matthew
	* @category Artist Object
	* @param name: The string name of the artist to be created
	* @param ID: The int ID of the artist - this is the primary key
	****************************************************/
	public Artist(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	/****************************************************
	* Base level constructor for creating a song - no primary ID
	* 
	* @author Matthew
	* @category Artist Object
	* @param name: The string name of the artist to be created
	****************************************************/
	public Artist(String name) {
		this.name = name;
		this.ID = -1;
	}
	
	/****************************************************
	* Getter for name
	* 
	* @author Matthew
	* @category Artist Object
	* @return the String name of the given artist
	****************************************************/
	public String getName() {
		return name;
	}
	
	/****************************************************
	* Getter for ID
	* 
	* @author Matthew
	* @category Artist Object
	* @return the ID of the given object
	****************************************************/
	public int getID() {
		return ID;
	}

	/****************************************************
	* Setter for ID
	* 
	* @author Matthew
	* @category Artist Object
	* param ID: the ID to change to
	****************************************************/
	public void setID(int ID) {
		this.ID = ID;
	}
}
