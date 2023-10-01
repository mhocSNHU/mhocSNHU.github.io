/*************************************************************************
 * @author Matthew Hocking
 * @file Song.java
 * @purpose Holds song info - This serves as an object to pass back and forth
 * between the database layer
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

public class Song {

	private String name; // Song name
	private int artist; // Artist ID
	private int ID; // Song ID
	
	/****************************************************
	* Base level constructor for creating a song
	* 
	* @author Matthew
	* @category Artist Object
	* @param name: The string name of the song to be created
	* @param artist: The int ID of the artist that created the song
	* @param ID: The int ID of the song - this is the primary key
	****************************************************/
	public Song(String name, int artist, int ID) {
		this.name = name;
		this.artist = artist;
		this.ID = ID;
	}
	
	/****************************************************
	* Base level constructor for creating a song - excludes
	* primary key
	* 
	* @author Matthew
	* @category Artist Object
	* @param name: The string name of the song to be created
	* @param artist: The int ID of the artist that created the song
	****************************************************/
	public Song(String name, int artist) {
		this.name = name;
		this.artist = artist;
		this.ID = -1; // ID set to -1 incase its used to make a DB call - this will always return nothing
	}
	
	/****************************************************
	* Getter for name
	* 
	* @author Matthew
	* @category Song Object
	* @return the String name of the given song
	****************************************************/
	public String getName() {
		return name;
	}

	/****************************************************
	* Setter for name
	* 
	* @author Matthew
	* @category Song Object
	* @param name: The name to change to
	****************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/****************************************************
	* Getter for artist
	* 
	* @author Matthew
	* @category Song Object
	* @return the int ID of the given songs artist owner
	****************************************************/
	public int getArtist() {
		return artist;
	}

	/****************************************************
	* Setter for artist
	* 
	* @author Matthew
	* @category Song Object
	* @param name: The artist ID to change to
	****************************************************/
	public void setArtist(int artist) {
		this.artist = artist;
	}
	
	/****************************************************
	* Getter for ID
	* 
	* @author Matthew
	* @category Song Object
	* @return the int ID of the given song
	****************************************************/
	public int getID() {
		return ID;
	}

	/****************************************************
	* Setter for ID
	* 
	* @author Matthew
	* @category Song Object
	* @param name: The song ID to change to
	****************************************************/
	public void setID(int ID) {
		this.ID = ID;
	}
}
