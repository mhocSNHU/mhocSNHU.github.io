package music.artist;

import java.util.ArrayList;

import snhu.jukebox.playlist.Song;

public class Akon {

	ArrayList<Song> albumTracks;
    String albumTitle;
    
    public Akon() {
    }
    
    public ArrayList<Song> getAkonSongs() {
    	
    	 albumTracks = new ArrayList<Song>();                           //Instantiate the album so we can populate it below
    	 Song track1 = new Song("Smack That", "Akon");         	    	//Create a song
         Song track2 = new Song("Locked Up", "Akon");                   //Create another song
         Song track3 = new Song("Enjoy That", "Akon");                  //Create a third song
         this.albumTracks.add(track1);                                  //Add the first song to song list
         this.albumTracks.add(track2);                                  //Add the second song to song list
         this.albumTracks.add(track3);                                  //Add the third song to song list
         return albumTracks;                                            //Return the songs for Akon in the form of an ArrayList
    }
}