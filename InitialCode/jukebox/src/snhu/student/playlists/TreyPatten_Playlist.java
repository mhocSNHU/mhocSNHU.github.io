package snhu.student.playlists;

import snhu.jukebox.playlist.PlayableSong;
import snhu.jukebox.playlist.Song;
import music.artist.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TreyPatten_Playlist {
    
	public LinkedList<PlayableSong> StudentPlaylist(){
	
	LinkedList<PlayableSong> playlist = new LinkedList<PlayableSong>();
	ArrayList<Song> dominionTracks = new ArrayList<Song>();
    OldDominion olddominion = new OldDominion();
	
    dominionTracks = olddominion.getDominionSongs();
	
	playlist.add(dominionTracks.get(0));
	playlist.add(dominionTracks.get(1));
	
	
    Akon akonBand = new Akon();
	ArrayList<Song> AkonTracks = new ArrayList<Song>();
    AkonTracks = akonBand.getAkonSongs();
	
	playlist.add(AkonTracks.get(0));
	playlist.add(AkonTracks.get(1));
	playlist.add(AkonTracks.get(2));
	
    return playlist;
	}
}
