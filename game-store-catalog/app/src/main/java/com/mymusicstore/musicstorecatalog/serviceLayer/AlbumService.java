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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AlbumService {
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private LabelRepository labelRepository;
    private TrackRepository trackRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository, LabelRepository labelRepository, TrackRepository trackRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.labelRepository = labelRepository;
        this.trackRepository = trackRepository;
    }
    @Transactional
    //If the whole method does not work successfully none the changes are applied. In this case I made some changes in side the database by adding new album and track list.
    public AlbumViewModel saveAlbum(AlbumViewModel albumViewModel) {
        Album album = new Album();                   //empty album
        album.setTitle(albumViewModel.getTitle());                                 //get the title form albumviewmodel and set it to the new album.
        album.setReleaseDate(albumViewModel.getReleaseDate());                   //get the release date form albumviewmodel and set it to the new album.
        album.setListPrice(albumViewModel.getListPrice());                       //get the list price form albumviewmodel and set it to the new album.
        album.setArtistId(albumViewModel.getArtist().getId());                  //get the artist id form albumviewmodel first by getting the artist and then the id and set it to the new album.
        album.setLabelId(albumViewModel.getLabel().getId());                   //get the label id form albumviewmodel first by getting the label and then the id and set it to the new album.

        album = albumRepository.save(album);                                 //it puts album info in the db. Artist and label are already in the database.
        albumViewModel.setId(album.getId());                                //Based on the id that i get for the album I am setting id for album view model.

        List<Track> tracklist = albumViewModel.getTracks();                //For the track i am going to do two things set the id based on the id that i got to the new album as shown above. And put it in to a db.
        tracklist.stream()
                .forEach((t -> {
                    t.setAlbumId(albumViewModel.getId());               //set album id for each truck
                    trackRepository.save(t);                           //Let track to show up in the db
                }));

        tracklist = trackRepository.findAllTracksByAlbumId(albumViewModel.getId()); //read them again in the db
        albumViewModel.setTracks(tracklist);                        //Based on the id that I got for the track list I am setting id for album view model.
        return albumViewModel;
    }

    // Assemble the AlbumViewModel
    private AlbumViewModel buildAlbumViewModel(Album album) {

        Optional<Artist> artist = artistRepository.findById(album.getArtistId());
        Optional<Label> label = labelRepository.findById(album.getLabelId());
        List<Track> track = trackRepository.findAllTracksByAlbumId(album.getArtistId());

        AlbumViewModel albumViewModel = new AlbumViewModel();
        albumViewModel.setId(album.getId());
        albumViewModel.setTitle(album.getTitle());
        albumViewModel.setReleaseDate(album.getReleaseDate());
        albumViewModel.setListPrice(album.getListPrice());
        albumViewModel.setArtist(artist.get());
        albumViewModel.setLabel(label.get());
        albumViewModel.setTracks(track);

        return albumViewModel;
    }

    public AlbumViewModel findAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            return buildAlbumViewModel((album.get())); // if an album is available build an album view model.
        }
        return null;
    }

    public List<AlbumViewModel> findAllAlbums() {
//        List<Album> albumList = albumRepository.findAll();
//        List<AlbumViewModel> albumViewModelList = albumList.stream()
//                .map(album -> buildAlbumViewModel(album))
//                .collect(Collectors.toList());
//        return albumViewModelList;
        List<Album> aList = albumRepository.findAll();
        List<AlbumViewModel> avmList = new ArrayList<>();

        for(int i = 0; i < aList.size(); i++){
            avmList.add(buildAlbumViewModel(aList.get(i)));
        }

        return avmList;
    }
    @Transactional
    public void updateAlbumById(AlbumViewModel albumViewModel){
//        First let me update the album information by creating a new empty album.
        Album album = new Album();
        album.setLabelId(albumViewModel.getLabel().getId());
        album.setId(albumViewModel.getId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId((albumViewModel.getArtist().getId()));
        album.setListPrice(albumViewModel.getListPrice());
        albumRepository.save(album);

        List<Track> tracklist = trackRepository.findAllTracksByAlbumId(album.getId());
        tracklist.stream()
                .forEach((track -> {
                    trackRepository.deleteById((track.getId()));
                }));
        List<Track> trackList = albumViewModel.getTracks();
        trackList.stream()
                .forEach(track -> {
                    track.setAlbumId(albumViewModel.getId());
                    track = trackRepository.save(track);

                });

    }
    @Transactional
    public void deleteById(Long id){
        List<Track> trackList = trackRepository.findAllTracksByAlbumId((id));
        trackList.stream()
                .forEach((track -> trackRepository.deleteById(track.getId())));
        albumRepository.deleteById((id));
    }


}
