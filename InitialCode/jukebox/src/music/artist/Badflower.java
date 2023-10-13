package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class Badflower {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public Badflower() {
    }
    
    //Populate the song list
    public ArrayList<Song> getBadflowerSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Family", "Badflower");         				//Create a song
         Song track2 = new Song("The Jester", "Badflower");     				//Create another song
         this.albumTracks.add(track1);                                          //Add the first song to song list for the Badflower
         this.albumTracks.add(track2);                                          //Add the second song to song list for the Badflower 
         return albumTracks;                                                    //Return the songs for the Badflower in the form of an ArrayList
    }
}
