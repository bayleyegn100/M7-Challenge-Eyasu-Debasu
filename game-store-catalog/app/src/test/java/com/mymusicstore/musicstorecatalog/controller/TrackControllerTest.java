package com.mymusicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusicstore.musicstorecatalog.model.Track;
import com.mymusicstore.musicstorecatalog.repository.TrackRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrackRepository trackRepository;
    private Track track;
    private ObjectMapper mapper = new ObjectMapper();
    private String trackJson;
    private List<Track> trackList = new ArrayList<>();

    @Before
    public void setup() {
        this.trackRepository = mock(TrackRepository.class);
        track = new Track();
        track.setAlbumId(5L);
        track.setId(14L);
        track.setTitle("Love");
        track.setRunTime(129);

        Track track1 = new Track();
        track1.setAlbumId(5L);
        track1.setTitle("Love");
        track1.setRunTime(129);

        List<Track> trackList1 = Arrays.asList(track);
        Optional<Track> findTrackById = Optional.of(track);

        doReturn(trackList1).when(trackRepository).findAll();
        doReturn(track).when(trackRepository).save(track1);
        doReturn(findTrackById).when(trackRepository).findById(14L);

    }

    @Test
    public void getTrackByIdShouldReturnATrack() throws Exception {
        Optional<Track> actualTrack = trackRepository.findById(14L);
        Track expectedTrack = track;
        assertEquals(expectedTrack, actualTrack.get());

    }

    @Test
    public void getTrackByIdStatusShouldReturnOkForNonExistingId() throws Exception {

        doReturn(Optional.empty()).when(trackRepository).findById(22L);

        mockMvc.perform(
                        get("/track/22"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void updateTrackByIdsShouldUpdateATrackAndReturn200StatusCode() throws Exception {
        track = new Track();
        track.setAlbumId(5L);
        track.setId(14L);
        track.setTitle("Love");
        track.setRunTime(129);

        trackJson = mapper.writeValueAsString(track);
        mockMvc.perform(
                put("/tracks")
                        .content(trackJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );

    }

    @Test
    public void deleteTrackByIdShouldDeleteATrackAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/track/14")).andExpect(status().isNoContent());
    }

    @Test
    public void getAllTrackShouldReturnAListAnd200() throws Exception {

        List<Track> actualRecommendedTrack = trackRepository.findAll();
        assertEquals(1, actualRecommendedTrack.size());

    }

    @Test
    public void addTrackShouldReturnNewTrackAnd201() throws Exception {

        Track expectedResult = new Track();
        expectedResult.setAlbumId(5L);
        expectedResult.setTitle("Love");
        expectedResult.setRunTime(129);

        Track trackWithId = new Track();
        trackWithId.setId(14L);
        trackWithId.setAlbumId(5L);
        trackWithId.setTitle("Love");
        trackWithId.setRunTime(129);

        Track actualTrack = trackRepository.save(expectedResult);
        assertEquals(trackWithId, actualTrack);
    }

}


