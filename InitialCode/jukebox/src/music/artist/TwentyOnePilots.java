package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class TwentyOnePilots {
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public TwentyOnePilots() {
    }
    
    public ArrayList<Song> getTwentyOnePilotsSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Stressed Out", "Twenty One Pilots");           //Create a song
         Song track2 = new Song("Smithereens", "Twenty One Pilots");         	//Create second song
         Song track3 = new Song("Car Radio", "Twenty One Pilots");         	//Create a third song
         this.albumTracks.add(track1);                                          //Add the first song to song list for Twenty One Pilots
         this.albumTracks.add(track2);                                          //Add the second song to song list for Twenty One Pilots
         this.albumTracks.add(track3);                                          //Add the third song to song list for Twenty One Pilots
         return albumTracks;                                                    //Return the songs for Twenty One Pilots in the form of an ArrayList
    }
}
