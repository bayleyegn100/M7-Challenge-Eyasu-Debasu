package com.mymusicstore.musicstorecatalog.serviceLayer;

import com.mymusicstore.musicstorecatalog.model.Album;
import com.mymusicstore.musicstorecatalog.model.Artist;
import com.mymusicstore.musicstorecatalog.model.Label;
import com.mymusicstore.musicstorecatalog.model.Track;
import com.mymusicstore.musicstorecatalog.repository.AlbumRepository;
import com.mymusicstore.musicstorecatalog.repository.ArtistRepository;
import com.mymusicstore.musicstorecatalog.repository.LabelRepository;
import com.mymusicstore.musicstorecatalog.repository.TrackRepository;
import com.mymusicstore.musicstorecatalog.viewmodel.AlbumViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class AlbumServiceTest {

    AlbumService albumService;
    AlbumRepository albumRepository;
    ArtistRepository artistRepository;
    LabelRepository labelRepository;
    TrackRepository trackRepository;

    @Before
    public void setUp() throws Exception {
        setUpAlbumRepositoryMock();
        setUpArtistRepositoryMock();
        setUpLabelRepositoryMock();
        setUpTrackRepositoryMock();

        albumService = new AlbumService(albumRepository, artistRepository, labelRepository, trackRepository);

    }

    // Helper methods
    private void setUpAlbumRepositoryMock() {
        albumRepository = mock(AlbumRepository.class);
        Album album = new Album();
        album.setId(1L);
        album.setArtistId(45L);
        album.setLabelId(10L);
        album.setTitle("Greatest Hits");
        album.setListPrice(new BigDecimal("14.99"));
        album.setReleaseDate(LocalDate.of(1999, 05, 15));

        Album album2 = new Album();
        album2.setArtistId(45L);
        album2.setLabelId(10L);
        album2.setTitle("Greatest Hits");
        album2.setListPrice(new BigDecimal("14.99"));
        album2.setReleaseDate(LocalDate.of(1999, 05, 15));

        List<Album> aList = new ArrayList<>();
        aList.add(album);

        doReturn(album).when(albumRepository).save(album2);
        doReturn(Optional.of(album)).when(albumRepository).findById(1L);
        doReturn(aList).when(albumRepository).findAll();
    }

    private void setUpArtistRepositoryMock() {
        artistRepository = mock(ArtistRepository.class);
        Artist artist = new Artist();
        artist.setId(45L);
        artist.setInstagram("@RockStar");
        artist.setName("The GOAT");
        artist.setTwitter("@TheRockStar");

        Artist artist2 = new Artist();
        artist2.setInstagram("@RockStar");
        artist2.setName("The GOAT");
        artist2.setTwitter("@TheRockStar");

        List aList = new ArrayList();
        aList.add(artist);

        doReturn(artist).when(artistRepository).save(artist2);
        doReturn(Optional.of(artist)).when(artistRepository).findById(45L);
        doReturn(aList).when(artistRepository).findAll();
    }

    private void setUpLabelRepositoryMock() {
        labelRepository = mock(LabelRepository.class);
        Label label = new Label();
        label.setId(10L);
        label.setName("Blue Note");
        label.setWebsite("www.bluenote.com");

        Label label2 = new Label();
        label2.setName("Blue Note");
        label2.setWebsite("www.bluenote.com");

        List lList = new ArrayList<>();
        lList.add(label);

        doReturn(label).when(labelRepository).save(label2);
        doReturn(Optional.of(label)).when(labelRepository).findById(10L);
        doReturn(lList).when(labelRepository).findAll();
    }

    private void setUpTrackRepositoryMock() {
        trackRepository = mock(TrackRepository.class);
        Track track = new Track();
        track.setId(1L);
        track.setAlbumId(1L);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        Track track2 = new Track();
        track.setAlbumId(1L);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        List tList = new ArrayList<>();
        tList.add(track);

        doReturn(track).when(trackRepository).save(track2);
        doReturn(Optional.of(track)).when(trackRepository).findById(1L);
        doReturn(tList).when(trackRepository).findAll();
        doReturn(tList).when(trackRepository).findAllTracksByAlbumId(1L);
    }

    @Test
    public void shouldAddANewAlbum() {
        Album albumToBeSave = new Album();
        albumToBeSave.setArtistId(45L);
        albumToBeSave.setLabelId(10L);
        albumToBeSave.setTitle("Greatest Hits");
        albumToBeSave.setListPrice(new BigDecimal("14.99"));

        Album albumWithId = new Album();
        albumWithId.setId(1L);
        albumWithId.setArtistId(45L);
        albumWithId.setLabelId(10L);
        albumWithId.setTitle("Greatest Hits");
        albumWithId.setListPrice(new BigDecimal("14.99"));
        albumWithId.setReleaseDate(LocalDate.of(1999, 05, 15));

        Album actualResult = albumRepository.save(albumToBeSave);

//        assertEquals(albumWithId, actualResult);

    }

    @Test
    public void findAllAlbumsShouldReturnAListOfAlbuns() {
        List<AlbumViewModel> actualResult = albumService.findAllAlbums();

        assertEquals(1, actualResult.size());
    }

    @Test
    public void shouldFindAlbumAndProperlyBuildViewModel() {
        AlbumViewModel expectedResult = new AlbumViewModel();
        expectedResult.setId(1L);
        expectedResult.setTitle("Greatest Hits");
        expectedResult.setListPrice(new BigDecimal("14.99"));
        expectedResult.setReleaseDate(LocalDate.of(1999, 05, 15));

        Artist expectedArtist = new Artist();
        expectedArtist.setId(45L);
        expectedArtist.setInstagram("@RockStar");
        expectedArtist.setName("The GOAT");
        expectedArtist.setTwitter("@TheRockStar");

        Label expectedLabel = new Label();
        expectedLabel.setId(10L);
        expectedLabel.setName("Blue Note");
        expectedLabel.setWebsite("www.bluenote.com");

        Track expectedTrack = new Track();
        expectedTrack.setId(1L);
        expectedTrack.setAlbumId(1L);
        expectedTrack.setRunTime(180);
        expectedTrack.setTitle("Number 1 Hit!");
        List<Track> expectedTrackList = new ArrayList<>();

        expectedResult.setArtist((expectedArtist));
        expectedResult.setLabel(expectedLabel);
        expectedResult.setTracks((expectedTrackList));


        AlbumViewModel actualResult = albumService.findAlbumById(1L);

        assertEquals(expectedResult, actualResult);
    }


//
//        @Test
//        public void saveAlbum() {
//            Album album = new Album();
//            album.setInstagram("@RockStar");
//            album.setName("The GOAT");
//            album.setTwitter("@TheRockStar");
//            album = albumService.saveAlbum(album);
//
//            Album fromService = albumService.findAlbum(album.getId());
//            assertEquals(album, fromService);
//        }
}


