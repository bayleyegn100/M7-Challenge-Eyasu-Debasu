package com.mymusicstore.musicstorecatalog.repository;

import com.mymusicstore.musicstorecatalog.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findAllTracksByAlbumId(Long albumId); //spring.jpa. Good example of Abstraction
}