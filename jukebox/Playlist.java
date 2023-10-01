/*************************************************************************
 * @author Matthew Hocking
 * @file Playlist.java
 * @purpose Holds playlist info - This serves as the object to pass back
 * and forth between the database layer
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

import java.util.ArrayList; // Used for the songs arraylist

public class Playlist {

	private String name; // Name of the playlist
	private int owner; // User ID of the playlist owner
	private int ID; // The playlist ID value
	private ArrayList<Song> songs; // Arraylist of the playlist songs
	
	/****************************************************
	* Base level constructor for creating a playlist
	* 
	* @author Matthew
	* @category Playlist Object
	* @param name: The name of the playlist
	* @param owner: The ID of the owner of the playlist (User)
	* @param ID: The ID of the playlist
	****************************************************/
	public Playlist(String name, int owner, int ID) {
		this.name = name;
		this.owner = owner;
		this.ID = ID;
		songs = new ArrayList<Song>();
	}
	
	/****************************************************
	* Base level constructor for creating a playlist with no
	* playlist ID
	* 
	* @author Matthew
	* @category Playlist Object
	* @param name: The name of the playlist
	* @param owner: The ID of the owner of the playlist (User)
	****************************************************/
	public Playlist(String name, int owner) {
		this.name = name;
		this.owner = owner;
		this.ID = -1;
		songs = new ArrayList<Song>();
	}
	
	/****************************************************
	* Getter for name
	* 
	* @author Matthew
	* @category Playlist Object
	* @return the String name of the given playlist
	****************************************************/
	public String getName() {
		return name;
	}

	/****************************************************
	* Setter for name
	* 
	* @author Matthew
	* @category Playlist Object
	* @param name: The name to change to
	****************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/****************************************************
	* Getter for ID
	* 
	* @author Matthew
	* @category Playlist Object
	* @return the int ID of the given playlist
	****************************************************/
	public int getID() {
		return ID;
	}

	/****************************************************
	* Setter for ID
	* 
	* @author Matthew
	* @category Playlist Object
	* @param ID: The ID to change to 
	****************************************************/
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/****************************************************
	* Getter for Owner
	* 
	* @author Matthew
	* @category Playlist Object
	* @return the int ID of the given playlists owner
	****************************************************/
	public int getOwner() {
		return owner;
	}

	/****************************************************
	* Setter for owner
	* 
	* @author Matthew
	* @category Playlist Object
	* @param name: The owner ID to change the playlist owner to
	****************************************************/
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	/****************************************************
	* Adds a song onto the playlist song arraylist
	* 
	* @author Matthew
	* @category Playlist Object
	* @param song: The song to append onto the arraylist
	****************************************************/
	public void addSong(Song song) {
		if (song != null) {
			songs.add(song);
		}
	}
	
	/****************************************************
	* Getter for the playlist songs
	* 
	* @author Matthew
	* @category Playlist Object
	* @return An arraylist of all songs in the playlist
	****************************************************/
	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	/****************************************************
	* Prints out the songs in a given playlist
	* 
	* @author Matthew
	* @category Playlist Object
	* @return A printout of every song by index
	****************************************************/
	public void playSongs() {
		for (int i = 0; i < songs.size(); i++) {
			System.out.print(i + 1);
			System.out.println(": " + songs.get(i).getName());
		}
	}
}
