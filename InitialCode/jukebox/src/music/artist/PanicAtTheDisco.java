// Added by Matthew Hocking
package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class PanicAtTheDisco {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public PanicAtTheDisco() {
    }
    
    public ArrayList<Song> getPanicAtTheDiscoSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("House of Memories", "Panic At The Disco");     // Song 1
         Song track2 = new Song("Impossible Year", "Panic At The Disco");       // Song 2
         this.albumTracks.add(track1);                                          //Add the first song to song list for the Panic
         this.albumTracks.add(track2);                                          //Add the second song to song list for the Panic 
         return albumTracks;                                                    //Return the songs for the Panic in the form of an ArrayList
    }
}
