package com.mymusicstore.musicstorecatalog.controller;

import com.mymusicstore.musicstorecatalog.model.Artist;
import com.mymusicstore.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {
    @Autowired
    ArtistRepository artistRepository;

    @GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/artist/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        Optional<Artist> returnVal = artistRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist addArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @PutMapping("/artists")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody Artist artist) {
        artistRepository.save(artist);
    }

    @DeleteMapping("/artist/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistById(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }
}
