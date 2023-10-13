// Added by Matthew Hocking
package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class Gallant {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public Gallant() {
    }
    
    public ArrayList<Song> getGallantSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Celine", "Gallant");             				// song 1
         Song track2 = new Song("KNOW ME", "Gallant");         					// song 2
         this.albumTracks.add(track1);                                          //Add the first song to song list for the Gallant
         this.albumTracks.add(track2);                                          //Add the second song to song list for the Gallant 
         return albumTracks;                                                    //Return the songs for the Gallant in the form of an ArrayList
    }
}
