package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class OldDominion {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public OldDominion() {
    }
    
    public ArrayList<Song> getDominionSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("One Man Band", "Old Dominion");                //Create a song
         Song track2 = new Song("Some People Do", "Old Dominion");              //Create another song
         this.albumTracks.add(track1);                                          //Add the first song to song list for the Old Dominion
         this.albumTracks.add(track2);                                          //Add the second song to song list for the Old Dominion
         return albumTracks;                                                    //Return the songs for Old Dominion in the form of an ArrayList
    }
}
