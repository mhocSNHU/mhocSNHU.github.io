package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class Radiohead {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public Radiohead() {
    }
    
    public ArrayList<Song> getRadioheadSongs() {
    	
    	 albumTracks = new ArrayList<Song>(); //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Karma Police", "Radiohead"); //Create a song
         Song track2 = new Song("No Surprises", "Radiohead"); //Create another song
         Song track3 = new Song("Paranoid Android", "Radiohead"); //Create a third song
         this.albumTracks.add(track1); //Add the first song to song list for Radiohead
         this.albumTracks.add(track2); //Add the second song to song list for Radiohead 
         this.albumTracks.add(track3);//Add the third song to song list for Radiohead
         return albumTracks; //Return the songs for Radiohead in the form of an ArrayList                                                    
    }
}
