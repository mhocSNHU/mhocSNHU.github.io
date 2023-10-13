package music.artist;

import snhu.jukebox.playlist.Song;
import java.util.ArrayList;

public class AliceInChains {
	
	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public AliceInChains() {
    }
    
    //Populate the song list
    public ArrayList<Song> getAliceInChainsSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                                   //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Nutshell", "Alice In Chains");         		//Create a song
         Song track2 = new Song("Sludge Factory", "Alice In Chains");     		//Create another song
         Song track3 = new Song("Your Decision", "Alice In Chains");            //Create another song
         this.albumTracks.add(track1);                                          //Add the first song to song list for the AiC
         this.albumTracks.add(track2);                                          //Add the second song to song list for the AiC 
         this.albumTracks.add(track3);
         return albumTracks;                                                    //Return the songs for the AiC in the form of an ArrayList
    }
}
