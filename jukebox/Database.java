/*************************************************************************
 * @author Matthew Hocking
 * @file Database.java
 * @purpose Connects the user interface and back end database
 * Can be used to acccess every table of the jukebox database
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

import java.sql.*; // Used to create SQL commands
import java.util.ArrayList; // Used to return large amounts of data at once

public class Database {
	private Connection db; // The connections to a SQL
	
	Database() {
		
		// Default connection parameters (can be changed)
		 String url = "jdbc:mysql://localhost:3306/playlists";
		 String username = "root";
		 String password = "SNHUf";
		 
		 System.out.println("Connecting database...");

		 // Attempt to connect to a database with the given parameters
		 try (Connection connection = DriverManager.getConnection(url, username, password)) {
			 db = DriverManager.getConnection(url, username, password);
		     System.out.println("Database Connection Successful");
		     
		  // Catch any SQL exceptions that may occur during the query call
		 } catch (SQLException e) {
		     throw new IllegalStateException("Cannot connect the database - Aborting", e);
		 }
    }
	
    /****************************************************
    * Artist Functions 
    ****************************************************/
	
	/****************************************************
	* Gives out a list of every artist in the database
	* 
	* @author Matthew
	* @category Artist
	* @returns An arraylist of artists in the system
	****************************************************/
	ArrayList<Artist> dbArtists() throws SQLException {
		
		// Arraylist to return
		ArrayList<Artist> artistsReturn = new ArrayList<Artist>(); // Arraylist of the artists
		
		// Generate query & execute query
		
		// This query is built to find all artists ordered by artist name alphabetically
		String artistQuery = "SELECT * FROM artists ORDER BY artistNAME";
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(artistQuery);
	        
	        // Print out each artist
	        while (rs.next()) {
	        	
	        	// Get the individual 
	          int artistID = rs.getInt("artistsID");
	          String artist = rs.getString("artistNAME");
	          artistsReturn.add(new Artist(artist, artistID));
	        }
	        
	        // Return the filled list
	        return artistsReturn;
	        
	     // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see artists - Aborting", e);
	      }
	}
	
	/****************************************************
	* Adds a new artist to the database using a given name 
	* 
	* @author Matthew
	* @category Artist
	* @param newArtist: The artist object to base the new entry on
	****************************************************/
	void dbAddArtist(Artist newArtist) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to add a new artist to the DB
		String artistQuery = "INSERT INTO `artists` (`artistNAME`) " + "VALUES (?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(artistQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, newArtist.getName()); // Set the ? to the artist name
			preparedStmt.execute();
			
			// As long as no SQL exception is hit - print out a confirmation
			System.out.println("Artist Addition Successful");
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Adding error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Edits a currently existing artists name
	* 
	* @author Matthew
	* @category Artist
	* @param artist: The ID of the artist to be edited
	* @param Name: The name to rename to
	****************************************************/
	void dbSetArtistName(int artist, String Name) throws SQLException {
		
		// Generate query & execute query
		
		// This query is set the name of a pre-selected artist by inputting its ID
		String studentQuery = "UPDATE `artists` SET `artistNAME` = ? WHERE (`artistsID` = ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, Name); // Set the first ? to the artist name
			preparedStmt.setInt(2, artist); // Set the second ? to the artist ID
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Update error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Removes an existing artist
	* 
	* @author Matthew
	* @category Artist
	* @param artist: The ID of the artist to be removed
	****************************************************/
	void dbDeleteArtist(int artist) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to remove an artist where the artist ID given ID
		String studentQuery = "DELETE FROM `artists` WHERE (`artistsID` = ?);";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, artist); // Set the first ? to the song artist ID
			
			preparedStmt.executeUpdate();
			
			// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Removal error - Aborting", e);
    	}
	}
	
    /****************************************************
    * Song Functions 
    ****************************************************/
	
	/****************************************************
	* Gives a list of every song
	* 
	* @author Matthew
	* @category Songs
	* @return An arraylist of the related songs
	****************************************************/
	ArrayList<Song> dbSongs() throws SQLException {
		
		ArrayList<Song> songReturn = new ArrayList<Song>(); // Arraylist of the songs
		
		// Generate query & execute query
		
		// This query is built to find all artists ordered by artist name alphabetically
		String artistQuery = "SELECT * FROM songs ORDER BY songNAME";
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(artistQuery);
	        
	        // Print out the returned songs
	        while (rs.next()) {
	          int songID = rs.getInt("songsID");
	          String song = rs.getString("songNAME");
	          songReturn.add(new Song(song, songID));
	        }
	        
	        return songReturn;
	        
	     // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see songs - Aborting", e);
	      }
	}
	
	/****************************************************
	* Gives a list of songs based upon a given artist ID
	* 
	* @author Matthew
	* @category Songs
	* @param artist: the ID of the artist to be searched
	* @return An arraylist of the related songs
	****************************************************/
	ArrayList<Song> dbSongsByArtist(int artist) throws SQLException {
		
		ArrayList<Song> songReturn = new ArrayList<Song>(); // Arraylist of the songs
		
		// Generate query & execute query 
		
		// This query is built to find all songs by artist 
		String artistQuery = "SELECT * FROM songs WHERE songARTIST = " + Integer.toString(artist) + " ORDER BY songNAME"; 
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(artistQuery);
	        
	        // Print out the related songs
	        while (rs.next()) {
	          int songID = rs.getInt("songsID");
	          int artistID = rs.getInt("songARTIST");
	          String song = rs.getString("songNAME");
	          songReturn.add(new Song(song, artistID, songID));
	        }
	        
	        return songReturn;
	        
	     // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see songs - Aborting", e);
	      }
	}
	
	/****************************************************
	* Returns a song based upon the given ID
	* 
	* @author Matthew
	* @category Songs
	* @param ID: the ID of the song to be searched
	* @return a song object containing the related ID info
	****************************************************/
	Song dbSongsByID(int ID) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to find the song by a given ID if it exists
		String artistQuery = "SELECT * FROM songs WHERE songsID = " + Integer.toString(ID); 
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(artistQuery);
	        
	        // Generate and return the new song object
	        while (rs.next()) {
	        	
	          // Builds a new object with the returned info 
	          return new Song(rs.getString("songNAME"), rs.getInt("songARTIST"), ID);
	        }
	        
	     // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see songs - Aborting", e);
	      }
	    
	    return null;
	}
	
	/****************************************************
	* Adds a song based upon the given song name and 
	* artist ID the song is related to
	* 
	* @author Matthew
	* @category Songs
	* @param newSong: The song object to be added
	****************************************************/
	void dbAddSong(Song newSong) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to insert a new song with the given artist name and related artist
		String studentQuery = "INSERT INTO `songs` (songNAME, songARTIST) " + "VALUES (?, ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, newSong.getName()); // Set the first ? to the song name
			preparedStmt.setInt(2, newSong.getArtist()); // Set the second ? to the artist ID
			
			preparedStmt.execute();

		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Adding error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Edits a currently existing songs name
	* 
	* @author Matthew
	* @category Songs
	* @param song: The ID of the song to be edited
	* @param Name: The name to rename to
	****************************************************/
	void dbSetSongName(int song, String Name) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to change the song name of a pre-existing song entry
		String studentQuery = "UPDATE `songs` SET `songNAME` = ? WHERE (`songsID` = ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, Name); // Set the first ? to the song name
			preparedStmt.setInt(2, song); // Set the first ? to the song ID
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Update error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Removes an existing song
	* 
	* @author Matthew
	* @category Songs
	* @param song: The ID of the artist to be removed
	****************************************************/
	void dbDeleteSong(int song) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to remove a song from the DB at a given index
		String studentQuery = "DELETE FROM `songs` WHERE (`songsID` = ?);";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, song); // Set the first ? to the song ID
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Removal error - Aborting", e);
    	}
	}
	
    /****************************************************
    * Playlist Functions
    ****************************************************/
	
	/****************************************************
	* Gives a list of all playlists for the given user
	* 
	* @author Matthew
	* @category Playlist
	* @param ID: The user to show playlists for
	* @return An arraylist of matching playlists
	****************************************************/
	ArrayList<Playlist> dbPlaylists(int ID) throws SQLException {
		
		ArrayList<Playlist> playlistReturn = new ArrayList<Playlist>(); // Arraylist of the songs
		
		// Generate query & execute query
		
		// This query is built to remove a song from the DB at a given index
		String playlistQuery = "SELECT * FROM playlists WHERE playlistsSTUDENT=" + ID + " ORDER BY playlistsNAME";
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(playlistQuery);
	        
	        // Print out each returned playlist
	        while (rs.next()) {
	          int playlistID = rs.getInt("playlistsID");
	          String playlistName = rs.getString("playlistsNAME");
	          playlistReturn.add(new Playlist(playlistName, ID, playlistID));
	        }
	        
	        return playlistReturn;
	        
	      // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see playlists - Aborting", e);
	      }
	}
	
	/****************************************************
	* Returns the requested playlist if the given user
	* owns it
	* 
	* @author Matthew
	* @category Playlist
	* @param ID: The ID of the playlist to retrieve
	* @param user: The user who owns the requested playlist
	* @return The playlist object filled in with the data
	****************************************************/
	Playlist dbPlaylistRetrieve(int ID, int user) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to get a specified playlist (only if its owned by the current user)
		String playlistQuery = "SELECT * FROM playlists WHERE playlistsSTUDENT=" + user + " AND playlistsID=" + ID + " ORDER BY playlistsID";
		Playlist playlist = null;
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(playlistQuery);
	        
	        // Generate a new playlist object based upon the returned value
	        while (rs.next()) {
	          playlist = new Playlist(rs.getString("playlistsNAME"), user);
	        }
	        
	        // Generate a second query to get the song entries
	        
	     // This query is built get every song that is present within the playlist
	        String songQuery = "SELECT * FROM entries WHERE entryPLAYLIST=" + ID + " ORDER BY entryID";
	        Statement stmt2 = db.createStatement();
		    ResultSet ms = stmt2.executeQuery(songQuery);
		        
		        // For each song returned for the given playlist, add it to the new playlist object
		        while (ms.next()) {
		        	playlist.addSong(dbSongsByID(ms.getInt("entrySONG")));
		        }
		        
		        // Return the filled playlist object
		        return playlist;
	        
		  // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("Cannot see playlists - Aborting", e);
	      }
	    
	}
	
	/****************************************************
	* Adds a new playlist based upon the given object
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The playlist to enter into the DB
	****************************************************/
	void dbAddPlaylist(Playlist playlist) throws SQLException {
		
		// Generate query & execute query to add the playlist
		
		// This query is built to insert a new playlist object based upon the given student and name
		String studentQuery = "INSERT INTO `playlists` (playlistsSTUDENT, playlistsNAME) " + "VALUES (?, ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, playlist.getOwner()); // Set the first ? to the playlist owner ID
			preparedStmt.setString(2, playlist.getName()); // Set the second ? to the playlist name
			
			preparedStmt.execute();
			
			// Find the newly assigned ID for the playlist 
			// This second expression is used to get the playlist ID that the DB assigns to it
			String playlistFound = "SELECT * FROM playlists WHERE playlistsSTUDENT='" + playlist.getOwner() + "' AND playlistsNAME='" + playlist.getName() + "'" ;
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(playlistFound);
			
			// Fill in song entries for the new playlist
	        while (rs.next()) {
	        	dbFillEntries(playlist, rs.getInt("playlistsID"));
		    }
			
	     // Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Adding error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Fills in the song entries for a given playlist
	* Note: This is only used during creation
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The playlist to enter
	* @param ID: The ID of the newly created playlist
	****************************************************/
	void dbFillEntries(Playlist playlist, int ID) throws SQLException {
		
		ArrayList<Song> songs = playlist.getSongs();
		
		// For each song in the playlist object
		for (int i = 0; i < songs.size(); i++) {
			
			// Generate query & execute query to add each song
			
			// This query is built to insert playlist entries into the DB during playlist creation
			String studentQuery = "INSERT INTO `entries` (entryPLAYLIST, entrySONG) " + "VALUES (?, ?)";
			try {
				PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
				
				// Set the ? values in the string
				preparedStmt.setInt(1, ID); // Set the first ? to the ID of the playlist
				preparedStmt.setInt(2, songs.get(i).getID()); // Set the second ? ID of the song
				
				preparedStmt.execute();
				
			// Catch any SQL exceptions that may occur during the query call
	    	} catch (SQLException e) {
	    		throw new IllegalStateException("Adding error - Aborting", e);
	    	}
		}
	}
	
	/****************************************************
	* Edits a currently existing playlists name
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The ID of the playlists to be edited
	* @param Name: The name to rename to
	****************************************************/
	void dbSetPlaylistName(int playlist, String Name) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to change the playlist name of a pre-existing playlist
		String studentQuery = "UPDATE `playlists` SET `playlistsNAME` = ? WHERE (`playlistsID` = ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, Name); // Set the first ? to the name of the playlist
			preparedStmt.setInt(2, playlist); // Set the second ? to the ID of the playlist
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Update error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Removes an existing playlist
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The ID of the playlist to be removed
	****************************************************/
	void dbDeletePlaylist(int playlist) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to remove a a playlist from the DB
		String studentQuery = "DELETE FROM `playlists` WHERE (`playlistsID` = ?);";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, playlist); // Set the first ? to the ID of the playlist
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Removal error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Adds an entry for a playlist
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The playlist to add to
	* @param song: The song to add into that playlists entries
	****************************************************/
	void dbAddEntry(int playlist, int song) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to insert a new entry (add songs from a playlist)
		String studentQuery = "INSERT INTO `entries` (entryPLAYLIST, entrySONG) " + "VALUES (?, ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, playlist); // Set the first ? to the ID of the playlist
			preparedStmt.setInt(2, song); // Set the second ? ID of the song to add
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Adding error - Aborting", e);
    	}
	}
	
	/****************************************************
	* Removes an entry for a playlist
	* 
	* @author Matthew
	* @category Playlist
	* @param playlist: The playlist to remove from
	* @param song: The ID of the song to be removed
	****************************************************/
	void dbRemoveEntry(int playlist, int song) throws SQLException {
		
		// Generate query & execute query
		
		// This query is built to remove a song from a pre-existing playlist
		String studentQuery = "DELETE FROM `entries` WHERE (`entrySONG` = ? and `entryPLAYLIST` = ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setInt(1, song); // Set the first ? to the ID of the song
			preparedStmt.setInt(2, playlist); // Set the second ? to the playlist ID
			
			preparedStmt.executeUpdate();
			
		// Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("Removal error - Aborting", e);
    	}
	}
	
    /****************************************************
    * User Functions 
    ****************************************************/
	
	/****************************************************
	* Checks to see if the user matches the DB
	* If so returns a student object
	* 
	* @author Matthew
	* @category User
	* @param username: The username of the requested user 
	* @param password: The password of the requested user
	* @return The student object for the requested user
	****************************************************/
	Student dbUserCheck(String username, String password) throws SQLException {
		
		// Generate query & execute query
		String studentQuery = "SELECT * FROM students WHERE studentUSER ='" + username + "'";
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(studentQuery);
	        while (rs.next()) {
	        	
	          // Check to see if the given password is correct
	          String correctPass = rs.getString("studentsPASS");
	          
	          // If the password is correct - Return the requested student
	          if (password.equals(correctPass)) {
	        	  return new Student(rs.getString("studentsName"), rs.getInt("studentsID"), rs.getString("studentUSER"));
	          }
	        }
	        
	      // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("User error - Aborting", e);
	      }
	    
	    return null;
	}
	
	/****************************************************
	* Generates a new user based upon the given input
	* 
	* @author Matthew
	* @category User
	* @param username: The username of the new user 
	* @param password: The password of the new user
	* @param name: The name of the new user
	* @return Returns a user object based upon the new user
	****************************************************/
	Student dbNewUser(String username, String password, String name) throws SQLException {
		
		// Generate query & execute query
		String studentQuery = "INSERT INTO `students` (`studentsName`, `studentsPASS`, `studentUSER`) " + "VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStmt = db.prepareStatement(studentQuery);
			
			// Set the ? values in the string
			preparedStmt.setString(1, name); // Set the first ? to the name if the user
			preparedStmt.setString(2, password); // Set the second ? to the hashed password
			preparedStmt.setString(3, username); // Set the third ? to the username
			
			preparedStmt.execute();
			
			// Request the newly generated student to get their ID
			String studentFound = "SELECT * FROM students WHERE studentUSER='" + username + "'";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(studentFound);
			
			// Return a new student object based upon the newly generated info
	        while (rs.next()) {
	        	return new Student(rs.getString("studentsName"), rs.getInt("studentsID"), rs.getString("studentUSER"));
		    }
			
	     // Catch any SQL exceptions that may occur during the query call
    	} catch (SQLException e) {
    		throw new IllegalStateException("User error - Aborting", e);
    	}
		return null;
	}
	
	/****************************************************
	* Checks if a given username is unique
	* 
	* @author Matthew
	* @category User
	* @param username: The potential duplicate username
	* @return True if the username is unique
	****************************************************/
	Boolean dbUserDupCheck(String username) throws SQLException {
		
		// Generate query & execute query
		String studentQuery = "SELECT COUNT(*) FROM students WHERE studentUSER ='" + username + "'";
	    try (Statement stmt = db.createStatement()) {
	        ResultSet rs = stmt.executeQuery(studentQuery);
	        
	        // If it finds anything aside from 0 entries for the given username - returns false
	        while (rs.next()) {
	        	if (rs.getInt(1) != 0) {
	        		return false;
	        	}
	        	else {
	        		return true;
	        	}
	        }
	        
	     // Catch any SQL exceptions that may occur during the query call
	      } catch (SQLException e) {
	    	  throw new IllegalStateException("User error - Aborting", e);
	      }
	    
	    return false;
	}
	
	
	
}
