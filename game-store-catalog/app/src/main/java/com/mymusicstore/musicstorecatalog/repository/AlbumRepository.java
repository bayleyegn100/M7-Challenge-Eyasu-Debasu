package com.mymusicstore.musicstorecatalog.repository;

import com.mymusicstore.musicstorecatalog.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
