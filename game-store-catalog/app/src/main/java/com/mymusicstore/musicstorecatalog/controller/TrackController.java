package com.mymusicstore.musicstorecatalog.controller;

import com.mymusicstore.musicstorecatalog.model.Track;
import com.mymusicstore.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TrackController {
    @Autowired
    TrackRepository trackRepository;

    @GetMapping("/tracks")
    public List<Track> getAllTracks() {
     return trackRepository.findAll();
    }

    @GetMapping("/track/{id}")
    public Track getTrackById(@PathVariable("id") Long id) {
        Optional<Track> returnVal = trackRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.CREATED)
    public Track addTrack(@RequestBody Track track) {
        return trackRepository.save(track);
    }

    @PutMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackById(@PathVariable("id") Long id, @RequestBody Track track) {
        trackRepository.save(track);
    }

    @DeleteMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackById(@PathVariable("id") Long id) {
        trackRepository.deleteById(id);
    }
}
