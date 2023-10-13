// Added by Matthew Hocking
package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class INABAKUMORI {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public INABAKUMORI() {
    }
    
    public ArrayList<Song> getINABAKUMORISongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Lag Train", "INABAKUMORI");                    // song 1 C:
         Song track2 = new Song("Lost Umbrella", "INABAKUMORI");         		// song 2
         this.albumTracks.add(track1);                                          //Add the first song to song list for the INABAKUMORI
         this.albumTracks.add(track2);                                          //Add the second song to song list for the INABAKUMORI 
         return albumTracks;                                                    //Return the songs for the INABAKUMORI in the form of an ArrayList
    }
}
