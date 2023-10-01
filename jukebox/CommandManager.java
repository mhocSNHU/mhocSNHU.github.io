/*************************************************************************
 * @author Matthew Hocking
 * @file CommandManager.java
 * @purpose Process all user requests and route them to different classes and SQL calls
 * This file is a form of sample UI that allows for users to easily interact with an interface
 * to make changes to the backend DB.
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

import java.io.BufferedReader; // Used for user inputs
import java.io.InputStreamReader; // Used for user inputs
import java.security.MessageDigest; // Used for user inputs
import java.sql.SQLException; // User for making SQL calls
import java.util.ArrayList; // Used for holding song data

public class CommandManager {
	
	private Database con; // The database connection to use
	private Student currentUser; // The current user of the command manager
		
	// Constructor
	public CommandManager(){
		con = new Database();
	}
	
    /****************************************************
    * Upkeep Functions
    ****************************************************/
	
	/****************************************************
	* Prints out the main menu 
	* 
	* @category Upkeep
	****************************************************/
	void printMainMenu(){
		System.out.println("\n\nWelcome to SNHU Jukebox Playlist Music!");
		System.out.println("Current User: " + currentUser.getName());
		System.out.println("Enter Command (Example: new playlist): ");
		System.out.println("Quit: quit");
		System.out.print("Enter a Command : ");
	}
	
	/****************************************************
	* Gets a given user input
	*
	* @category Upkeep
	* @returns The line entered by the user
	****************************************************/
	String getCommand(){
		String command="";
		try{
			
			// Try to get the user input provided by the buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			command = br.readLine();
		}
		
		// Due to the variety of possible problems (null input) - have a catch to create a loop
		catch(Exception e){
			System.out.println("Something went wrong with the system input!   Please try again.");
		}
		return command;
	}
	
    /****************************************************
    * Processing Functions
    ****************************************************/
	
	/****************************************************
	* Processes a given command
	* 
	* @author Matthew
	* @category Processing
	* @param Command: The string containing the command to process
	****************************************************/
    public void processCommand(String Command) {
    	
    	if (Command != null) {
    		
    		// Break up the string so that the individual parts can be processed
	    	String[] commandComponents = null;
	    	Command = Command.toLowerCase();
	    	commandComponents = Command.split(" ");
	    	
	    	// Check for the first word in the command
	    	switch (commandComponents[0]) {
	    	
	    		// Process creating a new entry
	    		case "new":  
	    			processNew(commandComponents);
	    			break;
	    		// Process looking at entries
	    		case "view":
	    			processView(commandComponents);
	    			break;
	    			
	    		// Process editing
	    		case "edit":
	    			processEdit(commandComponents);
	    			break;
	    		
	    		// Process playing a playlist
	    		case "play":
	    			processPlay();
	    			break;
	    		
	    	}
	    }
    }
    
	/****************************************************
	* Processes any adding based function commands
	* 
	* @author Matthew
	* @category Processing
	* @param newCommand: The already parsed string
	****************************************************/
    public void processNew(String[] newCommand) {
    	
    	if (newCommand != null && newCommand.length > 1) {   	
	    	switch (newCommand[1]) { // Switch statement based off of second word
	    	
	    		// Process adding an artist
		    	case "artist": 
		    		addArtist();
		    		break;
		    	
		    	// Process adding a playlist
		    	case "playlist":
		    		addPlaylist();
		    		break;
		    		
		    	// Process adding a song
		    	case "song":
		    		addSong();
		    		break;
		    		
		    		
	    	}
	    }
    }
    
	/****************************************************
	* Processes any editing based function commands
	* 
	* @author Matthew
	* @category Processing
	* @param newCommand: The already parsed string
	****************************************************/
    public void processEdit(String[] newCommand) {
    	
    	if (newCommand != null && newCommand.length > 1) {   	
	    	switch (newCommand[1]) { // Switch statement based off of second word
	    	
	    		// Process adding an artist
		    	case "artist": 
		    		editArtist();
		    		break;
		    	
		    	// Process adding a playlist
		    	case "playlist":
		    		editPlaylist();
		    		break;
		    		
		    	// Process adding a song
		    	case "song":
		    		editSong();
		    		break;
		    		
		    		
	    	}
	    }
    }
    
	/****************************************************
	* Processes any view based function commands
	* 
	* @author Matthew
	* @category Processing
	* @param newCommand: The already parsed string
	****************************************************/
    public void processView(String[] newCommand) {
    	if (newCommand != null && newCommand.length > 1) {
	    	
    		try {
		    	switch (newCommand[1]) { // Switch statement based off of second word	    
		    	
		    		// If you are viewing artists
			    	case "artist": 
			    		
			    		// If you are viewing artists - print a list based upon a list provided by the db
			    		ArrayList<Artist> artists = con.dbArtists();
			    		for (int i = 0; i < artists.size(); i++) {
			    			// Print out the index and name for each artist
			    			System.out.print(i + 1);
			    			System.out.println(": " + artists.get(i).getName());
			    		}
			    		break;
			    		
			    	// If you are viewing playlists - print a list based upon a list provided by the db
			    	case "playlist":
			    		ArrayList<Playlist> playlists = con.dbPlaylists(currentUser.getID());
			    		for (int i = 0; i < playlists.size(); i++) {
			    			// Print out the index and name for each playlist
			    			System.out.print(i + 1);
			    			System.out.println(": " + playlists.get(i).getName());
			    		}
			    		break;
			    		
			    	// If you are viewing songs - print a list based upon a list provided by the db
			    	case "song":
			    		ArrayList<Song> songs = con.dbSongs();
			    		// Prints out the index and the name of each song
			    		for (int i = 0; i < songs.size(); i++) {
			    			System.out.print(i + 1);
			    			System.out.println(": " + songs.get(i).getName());
			    		}
			    		break;
		    	}
		    	
		    // Catch any SQL problems that may occur due to DB access
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
    }
    
    
	/****************************************************
	* Processes any playing based function commands
	* 
	* @author Matthew
	* @category Processing
	* @param newCommand: The already parsed string
	****************************************************/
    public void processPlay() {
    	
    	try {
			Playlist playlist = con.dbPlaylistRetrieve(getPlaylistSelection(), currentUser.getID()); // Gets a playlist
			if (playlist != null) {
				playlist.playSongs(); // Plays the requested playlist
			}
			
		// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    /****************************************************
    * Selection Functions
    ****************************************************/
    
	/****************************************************
	* Prompts the user to select a playlist
	* 
	* @author Matthew
	* @category Selection
	* @returns The int ID of the playlist selected - -1 if invalid
	****************************************************/
    public int getPlaylistSelection() {
    	try {
    		
    		ArrayList<Playlist> playlists = con.dbPlaylists(currentUser.getID());
    		int ID = -1;
    		// Prints out each artist
    		for (int i = 0; i < playlists.size(); i++) {
    			System.out.print(i + 1);
    			System.out.println(": " + playlists.get(i).getName());
    		}
    		
    		// While loop to obtain a valid value
    		while (ID <= 0 || ID > playlists.size()) {
				System.out.print("\nSelect a Playlist (by ID): ");
				
				String selection = getCommand();
				
				// Parses the returned value
				try {
					ID = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					   ID = -1;
				}
    		}
			
			return playlists.get(ID - 1).getID();
			
		// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return -1;
    }
    
	/****************************************************
	* Prompts the user to select a artist
	* 
	* @author Matthew
	* @category Selection
	* @returns The int ID of the artist selected - -1 if invalid
	****************************************************/
    public int getArtistSelection() {
    	try {
    		
    		ArrayList<Artist> artists = con.dbArtists();
    		int ID = -1;
    		// Prints out each artist
    		for (int i = 0; i < artists.size(); i++) {
    			System.out.print(i + 1);
    			System.out.println(": " + artists.get(i).getName());
    		}
    		
    		// While loop to obtain a valid value
    		while (ID <= 0 || ID > artists.size()) {
				System.out.print("\nSelect an Artist (by ID): ");
				
				String selection = getCommand();
				
				// Parses the returned value
				try {
					ID = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					   ID = -1;
				}
    		}
			
			return artists.get(ID - 1).getID();
	    	
		// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return -1;
    }
    
	/****************************************************
	* Prompts the user to select a song given an artist ID
	* 
	* @author Matthew
	* @category Selection
	* @param artist: The artist ID that the user is searching songs for
	* @returns The int ID of the song selected - -1 if invalid
	****************************************************/
    public int getSongSelection(int artist) {
    	try {
    		
    		ArrayList<Song> songs = con.dbSongsByArtist(artist);
    		int ID = -1;
    		
    		// Prints out each song by artist
    		for (int i = 0; i < songs.size(); i++) {
    			System.out.print(i + 1);
    			System.out.println(": " + songs.get(i).getName());
    		}
    		
    		// Continue getting put until the value is within range
    		while (ID <= 0 || ID > songs.size()) {
    			
    			System.out.print("\nSelect a Song (by ID): ");
    			
    			String selection = getCommand();
    			
				// Parses the returned value
				try {
					ID = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					   ID = -1;
				}
    				
    		}
    		
    		return songs.get(ID - 1).getID();
			
    	// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return -1;
    }
    
    /****************************************************
    * Editing Functions
    ****************************************************/
    
	/****************************************************
	* Handles editing an artist in a variety of ways
	* 
	* @author Matthew
	* @category Editing
	****************************************************/
    public void editArtist() {
    	
    	try {
    		String name = null;
    		
    		int artistID = getArtistSelection();
    		
    		int editChoice = -1;
    		
    		System.out.print("\n1. Change Name");
    		System.out.print("\n2. Delete");
    		
    		// Continue getting put until the value is within range of options
    		while (editChoice <= 0 || editChoice > 2) {
    			
    			System.out.print("\nSelect an option (by ID): ");
    			
    			
    			String selection = getCommand();
    			
				// Parses the returned value
				try {
					editChoice = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					editChoice = -1;
				}
    				
    		}
    		
    		// Handles the edit choice
    		switch (editChoice)  {
    		
    			// Edit the artist name
    			case 1:
    		    	
    		    	while (name == null || name == "") {
    		    	System.out.print("Enter New Name (cannot be null) : ");
    		    	
    		    	name = getCommand();
    		    	}
    		    	
    		    	con.dbSetArtistName(artistID, name);
    		    	break;
    		    	
    		    // Delete the given artist
    			case 2:
    				con.dbDeleteArtist(artistID);
    				break;
    				
    		}
    		
    	// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
	/****************************************************
	* Handles editing a song in a variety of ways
	* 
	* @author Matthew
	* @category Editing
	****************************************************/
    public void editSong() {
    	
    	try {
    		String name = null;
    		
    		int artistID = getArtistSelection();
    		int songID = getSongSelection(artistID);
    		int editChoice = -1;
    		
    		System.out.print("\n1. Change Name");
    		System.out.print("\n2. Delete");
    		
    		// Continue getting put until the value is within range of options
    		while (editChoice <= 0 || editChoice > 2) {
    			
    			System.out.print("\nSelect an option (by ID): ");
    			
    			
    			String selection = getCommand();
    			
				// Parses the returned value
				try {
					editChoice = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					editChoice = -1;
				}
    				
    		}
    		
    		// Decide what type of edit will be done
    		switch (editChoice)  {
    		
    			// Rename the song
    			case 1:
    		    	
    		    	while (name == null || name == "") {
    		    	System.out.print("Enter New Name (cannot be null) : ");
    		    	
    		    	name = getCommand();
    		    	}
    		    	
    		    	con.dbSetSongName(songID, name);
    		    	break;
    		    
    		    // Delete the song
    			case 2:
    				con.dbDeleteSong(songID);
    				break;
    				
    		}
    		
    	// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	/****************************************************
	* Handles editing a playlist in a variety of ways
	* 
	* @author Matthew
	* @category Editing
	****************************************************/
    public void editPlaylist() {
    	
    	try {
    		String name = null;
    		
    		int playlistID = getPlaylistSelection();
    		int editChoice = -1;
    		
    		System.out.print("\n1. Change Name");
    		System.out.print("\n2. New Song");
    		System.out.print("\n3. Remove Song");
    		System.out.print("\n4. Delete");
    		
    		// Continue getting put until the value is within range of options
    		while (editChoice <= 0 || editChoice > 4) {
    			
    			System.out.print("\nSelect an option (by ID): ");
    			
    			
    			String selection = getCommand();
    			
				// Parses the returned value
				try {
					editChoice = Integer.parseInt(selection);
				}
				
				// Continue the loop if the given value is invalid
				catch (NumberFormatException e) {
					editChoice = -1;
				}
    				
    		}
    		
    		switch (editChoice)  {
    		
    			// Name change
    			case 1:
    		    	while (name == null || name == "") {
    		    	System.out.print("Enter New Name (cannot be null) : ");
    		    	
    		    	name = getCommand();
    		    	}
    		    	
    		    	con.dbSetPlaylistName(playlistID, name);
    		    	break;
    		    	
    		    // Addition of songs
    			case 2:
    				int songID = getSongSelection(getArtistSelection());
    				con.dbAddEntry(playlistID, songID);
    				break;
    		    	
    			// Removal of songs
    			case 3:
    		    	Playlist playlistBase = con.dbPlaylistRetrieve(playlistID, currentUser.getID());
    		    	ArrayList<Song> songs = playlistBase.getSongs();
    		    	int removalID = -1;
    		    	System.out.println("");
    		    	
    		    	// Prints out each song in the playlist
    	    		for (int i = 0; i < songs.size(); i++) {
    	    			System.out.print(i + 1);
    	    			System.out.println(": " + songs.get(i).getName());
    	    		}
    	    		
    	    		// Continue getting put until the value is within range
    	    		while (removalID <= 0 || removalID > songs.size()) {
    	    			
    	    			System.out.print("\nSelect a Song (by ID): ");
    	    			
    	    			String selection = getCommand();
    	    			
    					// Parses the returned value
    					try {
    						removalID = Integer.parseInt(selection);
    					}
    					
    					// Continue the loop if the given value is invalid
    					catch (NumberFormatException e) {
    						removalID = -1;
    					}
    	    				
    	    		}
    	    		
    	    		// Removes the given song from the playlist
    	    		con.dbRemoveEntry(playlistID, songs.get(removalID-1).getID());
    	    		break;

    		    // Removal of the playlist
    			case 4:
    				con.dbDeletePlaylist(playlistID);
    				break;
    				
    		}
    		
    	// Catch any SQL problems that may occur due to DB access
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /****************************************************
    * Addition Functions
    ****************************************************/
    
	/****************************************************
	* Has the user add an artist to the DB - Self Contained
	* 
	* @author Matthew
	* @category Addition
	****************************************************/
    public void addArtist() {
    	String name = null;
    	
    	try {
    		
    		// Collects info
	    	System.out.println("\n\nPlease Enter The Required Information");
	    	
	    	while (name == null) {
	    	System.out.print("Enter Artist Name (cannot be null) : ");
	    	
	    	name = getCommand();
	    	}
	    	
	    	Artist newArtist = new Artist(name);
	    	
	    	// Sends the collected info to DB
	    	con.dbAddArtist(newArtist);
    	
	    // Catch any SQL problems that may occur due to DB access
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
	/****************************************************
	* Has the user add a song to the DB - Self Contained
	* 
	* @author Matthew
	* @category Addition
	****************************************************/
    public void addSong() {
    	String name = null;
    	int artist = -1;
    	try {
    		// Collects info
	    	System.out.println("\n\nPlease Enter The Required Information");
	    	
	    	while (name == null) {
	    	System.out.print("Enter Song Name (cannot be null) : ");
	    	
	    	name = getCommand();
	    	}
	    	
	    	artist = getArtistSelection();
	    	
	    	Song newSong = new Song(name, artist); 
	    	
	    	// Sends the collected info to DB
	    	con.dbAddSong(newSong);
    	
	    // Catch any SQL problems that may occur due to DB access
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
	/****************************************************
	* Has the user add a playlist to the DB - Self Contained
	* 
	* @author Matthew
	* @category Addition
	****************************************************/
    public void addPlaylist() {
    	String name = null;
    	int user = currentUser.getID();
    	Boolean addingSongs = true;
    	int currentArtist = -1;
    	int currentSong = -1;
    	String response = "y";
    	try {
    		
    		// Collects info
	    	while (name == null) {
	    	System.out.print("Enter Playlist Name (cannot be null) : ");
	    	
	    	name = getCommand();
	    	}
	    	
	    	// Generates a new playlist with the basic info
	    	Playlist newPlaylist = new Playlist(name, user);
    		
	    	// While the user still wants to add songs
	    	while (addingSongs == true) {
	    		
	    		// Gets a song
	    		currentArtist = getArtistSelection();
	    		currentSong = getSongSelection(currentArtist);
	    		
	    		// Adds the song to the new playlist
				newPlaylist.addSong(con.dbSongsByID(currentSong));
	    		
				// Prompts the user if they want to add more
		    	System.out.print("Would you like to add more (Y/n) ? : ");
		    	response = getCommand();
		    	if (response.toLowerCase().equals("y") != true) {
		    		addingSongs = false;
		    	}
	    	}
	    	
	    	con.dbAddPlaylist(newPlaylist);
	    	
	    // Catch any SQL problems that may occur due to DB access
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /****************************************************
    * Login Functions 
    ****************************************************/
    
	/****************************************************
	* Prints out the main menu for logging in 
	* 
	* @author Matthew
	* @category Login
	* @return true if the CommandManager is given a user - otherwise false
	****************************************************/
    public Boolean loginProcess() {
		System.out.println("\n\nWelcome to SNHU Jukebox Playlist Music!");
		System.out.println("This is the primary login screen for new or returning users: ");
		System.out.println("Quit: quit - New: create new user account - Existing: Login");
		System.out.print("Enter a Command : ");
		
		// Gets a command to process for login
		String command = getCommand();  
		command = command.toLowerCase();
			switch(command) {
			
				// In the case of quit - break out of the loop and end the program
				case "quit":
					return false;
					
				// In the case of newUser - generate a completely new user for the DB
				case "new":
					return newUser();
					
				// In the case of an existing user - request a username and password
				case "existing":
					return existingUser();
			}
		
		return false;
    }
    
	/****************************************************
	* Handles the process of generating a new user
	* 
	* @author Matthew
	* @category Login
	* @return true if the creation is successufl
	****************************************************/
    public Boolean newUser() {
    	String name = null;
    	String username = null;
    	String password = null;
    	
    	try {
    		
    		// Collects new user info
	    	System.out.println("\n\nPlease Enter The Required Information");
	    	
	    	while (name == null) {
	    		
		    	System.out.print("Enter Student Name (cannot be null) : ");
		    	
		    	name = getCommand();
	    	}
	    	
	    	while (username == null) {
	    		
		    	System.out.print("\nEnter Student user (cannot be null) : ");
		    	
		    	username = getCommand();
					if (con.dbUserDupCheck(username)== false) {
						username = null;
					}
	    	}
	    	
	    	while (password == null) {
		    	System.out.print("\nEnter password (cannot be null) : ");
		    	
		    	password = getCommand();
	    	}
	    	
	    	// Process the info to create a new student
	    	Student newStudent = con.dbNewUser(username, hashPass(password), name); // Ensure the password is hashed for security purposes
	    	if (newStudent != null) {
	    		currentUser = newStudent;
	    		return true;
	    	}
	    	return false;
    	
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false; 
    }
    
	/****************************************************
	* Handles logging in
	* 
	* @author Matthew
	* @category Login
	* @return true search is successful
	****************************************************/
    public Boolean existingUser() {
    	try {
    		
        	String username = null;
        	String password = null;
        	Student userValues = null;
    		
    		// Collects info
	    	System.out.println("\n\nPlease Enter The Required Information");
	    	
	    	// Get a username from the user
	    	while (username == null) {
	    	
	    		System.out.print("Enter Username : ");
	    		username = getCommand();  
	    	
	    	}
	    	
	    	// Get a password from the user
	    	while (password == null) {
	    		
		    	System.out.print("\nEnter Password : ");
		    	password = getCommand();  
		    	
	    	}
			
	    	// Checks to see if the user matches a user in the database
	    	userValues = con.dbUserCheck(username, hashPass(password));
				
	    	// If a user is returned by the database layer - we consider this the current user
			if (userValues != null) {
				currentUser = userValues;
				return true;
			}
			
			// Prompt the user to try again if the input is incorrect
			else {
				System.out.println("\n\nIncorrect Login");
				System.out.print("Try Again (Y/n)? : ");
				
				String tryAgain = getCommand();
				
				if (tryAgain.toLowerCase().equals("y")) {
					return existingUser(); // Calls existing user again - making this recursive
				}
				
				else {
					return false;
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return false;

    }
    
	/****************************************************
	* Hashes passwords to make them more difficult to crack/
	* Provide more DB security
	* 
	* @implNote Implementation Taken From packtpub - 
	* https://subscription.packtpub.com/book/security/9781849697767/1/ch01lvl1sec09/creating-a-strong-hash-simple
	* 
	* @author Matthew
	* @category Login
	* @return true search is successful
	****************************************************/
    public String hashPass(String password) {
    	try {
    		
    		// Create a SHA-1 processor - SHA-1 used for the low length
    		MessageDigest processor = MessageDigest.getInstance("SHA-1");
    		
    		// Sets the digest to the passwords bytes
    		processor.update(password.getBytes());
    		
    		// Gets an array of hashed bytes
    		byte bytes[] = processor.digest();
    		
    		// String Builder for the hash
    		StringBuilder sb = new StringBuilder();
    		
    		// Generate a new string using the hash
    		for (int i = 0; i < bytes.length; i++) {
    			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    		}
    		
    		return sb.toString();
    	}
    	catch (Exception e) {
    		System.out.println("Hashing Error");
    		return null;
    	}
    }
}


