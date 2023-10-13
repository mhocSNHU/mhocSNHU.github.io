package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class Diiv {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public Diiv() {
    }
    
    public ArrayList<Song> getDiivSongs() {
    	
    	 albumTracks = new ArrayList<Song>(); //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Doused", "DIIV"); //Create a song
         Song track2 = new Song("Out of Mind", "DIIV"); //Create another song
         this.albumTracks.add(track1); //Add the first song to song list for Diiv
         this.albumTracks.add(track2); //Add the second song to song list for Diiv 
         return albumTracks; //Return the songs for Diiv in the form of an ArrayList                                                    
    }
}
