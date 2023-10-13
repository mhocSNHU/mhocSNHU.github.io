package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class JonBellion {
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public JonBellion() {
    }
    
    public ArrayList<Song> getJonBellionSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("All Time Low", "Jon Bellion");           		//Create a song
         Song track2 = new Song("Human", "Jon Bellion");         				//Create second song
         this.albumTracks.add(track1);                                          //Add the first song to song list for Jon Bellion
         this.albumTracks.add(track2);                                          //Add the second song to song list for Jon Bellion
         return albumTracks;                                                    //Return the songs for Jon Bellion in the form of an ArrayList
    }
}
