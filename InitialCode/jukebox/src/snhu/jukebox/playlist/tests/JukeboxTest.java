package snhu.jukebox.playlist.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import music.artist.*;
import snhu.jukebox.playlist.Song;

public class JukeboxTest {

	@Test
	public void testGetBeatlesAlbumSize() throws NoSuchFieldException, SecurityException {
		 TheBeatles theBeatlesBand = new TheBeatles();
		 ArrayList<Song> beatlesTracks = new ArrayList<Song>();
		 beatlesTracks = theBeatlesBand.getBeatlesSongs();
		 assertEquals(2, beatlesTracks.size());
	}
	
	@Test
	public void testGetImagineDragonsAlbumSize() throws NoSuchFieldException, SecurityException {
		 ImagineDragons imagineDragons = new ImagineDragons();
		 ArrayList<Song> imagineDragonsTracks = new ArrayList<Song>();
		 imagineDragonsTracks = imagineDragons.getImagineDragonsSongs();
		 assertEquals(3, imagineDragonsTracks.size());
	}
	
	@Test
	public void testGetAdelesAlbumSize() throws NoSuchFieldException, SecurityException {
		 Adele adele = new Adele();
		 ArrayList<Song> adelesTracks = new ArrayList<Song>();
		 adelesTracks = adele.getAdelesSongs();
		 assertEquals(3, adelesTracks.size());
	}
	
	@Test
	public void testGetOldDominionAlbumSize() throws NoSuchFieldException, SecurityException {
		 OldDominion olddominion = new OldDominion();
		 ArrayList<Song> olddominionTracks = new ArrayList<Song>();
		 olddominionTracks = olddominion.getDominionSongs();
		 assertEquals(2, olddominionTracks.size());
	}
	
	@Test
	public void testAkonAlbumSize() throws NoSuchFieldException, SecurityException {
		 Akon akon = new Akon();
		 ArrayList<Song> akonTracks = new ArrayList<Song>();
		 akonTracks = akon.getAkonSongs();
		 assertEquals(3, akonTracks.size());	//Corrected by Christopher Roelle. There are now 3 Akon tracks. (checks 3 instead of 2 now).
	}
	
	@Test
	public void testgetTwentyOnePilotsAlbumSize() throws NoSuchFieldException, SecurityException {
		 TwentyOnePilots twentyonpilots = new TwentyOnePilots();
		 ArrayList<Song> twentyonpilotsTracks = new ArrayList<Song>();
		 twentyonpilotsTracks = twentyonpilots.getTwentyOnePilotsSongs();
		 assertEquals(3, twentyonpilotsTracks.size());
	}
	
	@Test
	public void testGetJonBellionsAlbumSize() throws NoSuchFieldException, SecurityException {
		 JonBellion jonbellion = new JonBellion();
		 ArrayList<Song> jonbellionsTracks = new ArrayList<Song>();
		 jonbellionsTracks = jonbellion.getJonBellionSongs();
		 assertEquals(2, jonbellionsTracks.size());
	}
	
	@Test
	public void testGetGallantsAlbumSize() throws NoSuchFieldException, SecurityException {
		Gallant Gallant = new Gallant();
		 ArrayList<Song> GallantTracks = new ArrayList<Song>();
		 GallantTracks = Gallant.getGallantSongs();
		 assertEquals(2, GallantTracks.size());
	}
	
	@Test
	public void testGetPanicsAlbumSize() throws NoSuchFieldException, SecurityException {
		PanicAtTheDisco Panic = new PanicAtTheDisco();
		 ArrayList<Song> panicTracks = new ArrayList<Song>();
		 panicTracks = Panic.getPanicAtTheDiscoSongs();
		 assertEquals(2, panicTracks.size());
	}
	
	@Test
	public void testGetINABAKUMORIAlbumSize() throws NoSuchFieldException, SecurityException {
		INABAKUMORI INA = new INABAKUMORI();
		 ArrayList<Song> INABAKUMORITracks = new ArrayList<Song>();
		 INABAKUMORITracks = INA.getINABAKUMORISongs();
		 assertEquals(2, INABAKUMORITracks.size());
	}
	
	//Christopher Roelle test for AiC and Badflower. AiC has 3 tracks, Badflower has 2.
	@Test
	public void testGetAliceInChainsAlbumSize() throws NoSuchFieldException, SecurityException{
		AliceInChains aliceInChainsBand = new AliceInChains();
		ArrayList<Song> aliceInChainsTracks = new ArrayList<Song>();
		aliceInChainsTracks = aliceInChainsBand.getAliceInChainsSongs();
		assertEquals(3, aliceInChainsTracks.size());
	}
	
	@Test
	public void testGetBadflowerAlbumSize() throws NoSuchFieldException, SecurityException{
		Badflower badflowerBand = new Badflower();
		ArrayList<Song> badflowerTracks = new ArrayList<Song>();
		badflowerTracks = badflowerBand.getBadflowerSongs();
		assertEquals(2, badflowerTracks.size());
	}
	
	//test added by Jeremy Cukrowski for Radiohead and DIIV
	@Test
	public void testGetDiivAlbumSize() throws NoSuchFieldException, SecurityException{
		Diiv diivBand = new Diiv();
		ArrayList<Song> diivTracks = new ArrayList<Song>();
		diivTracks = diivBand.getDiivSongs();
		assertEquals(2, diivTracks.size());
	}
	
	@Test
	public void testGetRadioheadAlbumSize() throws NoSuchFieldException, SecurityException{
		Radiohead radioheadBand = new Radiohead();
		ArrayList<Song> radioheadTracks = new ArrayList<Song>();
		radioheadTracks = radioheadBand.getRadioheadSongs();
		assertEquals(3, radioheadTracks.size());
	}
}
