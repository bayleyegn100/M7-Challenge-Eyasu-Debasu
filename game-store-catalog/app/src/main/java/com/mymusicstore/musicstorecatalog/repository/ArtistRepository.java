package com.mymusicstore.musicstorecatalog.repository;

import com.mymusicstore.musicstorecatalog.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}