package snhu.student.playlists;

import snhu.jukebox.playlist.PlayableSong;
import snhu.jukebox.playlist.Song;
import music.artist.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Hocking_Playlist {
    
	public LinkedList<PlayableSong> StudentPlaylist(){
	
	LinkedList<PlayableSong> playlist = new LinkedList<PlayableSong>();
	
	// INABAKUMORI Songs
	ArrayList<Song> INAtracks = new ArrayList<Song>();
	INABAKUMORI INABAKUMORIclass = new INABAKUMORI();
    INAtracks = INABAKUMORIclass.getINABAKUMORISongs();
	
	playlist.add(INAtracks.get(0));
	playlist.add(INAtracks.get(1));
	
	// Gallant Songs
	Gallant GallantBand = new Gallant();
	ArrayList<Song> GallantTracks = new ArrayList<Song>();
	GallantTracks = GallantBand.getGallantSongs();
	
	playlist.add(GallantTracks.get(0));
	playlist.add(GallantTracks.get(1));
	
	// Panic Songs
	PanicAtTheDisco PanicBand = new PanicAtTheDisco();
	ArrayList<Song> PanicTracks = new ArrayList<Song>();
	PanicTracks = PanicBand.getPanicAtTheDiscoSongs();
	
	playlist.add(PanicTracks.get(0));
	playlist.add(PanicTracks.get(1));
	
	// Radiohead songs
	Radiohead RadioheadBand = new Radiohead();
	ArrayList<Song> RadioHeadTracks = new ArrayList<Song>();
	RadioHeadTracks = RadioheadBand.getRadioheadSongs();
	
	playlist.add(RadioHeadTracks.get(0));
	
	// Badflower songs
	Badflower BadflowerBand = new Badflower();
	ArrayList<Song> BadflowerTracks = new ArrayList<Song>();
	BadflowerTracks = BadflowerBand.getBadflowerSongs();
	
	playlist.add(BadflowerTracks.get(0));
	playlist.add(BadflowerTracks.get(1));
	
    return playlist;
	}
}
