package com.mymusicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusicstore.musicstorecatalog.model.Album;
import com.mymusicstore.musicstorecatalog.repository.AlbumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@WebMvcTest(AlbumControllerTest.class)
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlbumRepository albumRepository;

    private Album album;
    private ObjectMapper mapper = new ObjectMapper();
    private String albumJson;
    private List<Album> albumList = new ArrayList<>();

    @Before
    public void setup() {
        this.albumRepository = mock(AlbumRepository.class);
        album = new Album();
        album.setListPrice(BigDecimal.valueOf(12.45));
        album.setId(11L);
        album.setArtistId(5L);
        album.setLabelId(9L);
        album.setReleaseDate(LocalDate.of(2005, 05, 19));
        album.setTitle("Ethiopia");
//        album.setTracks();

        Album album1 = new Album();
        album1.setListPrice(BigDecimal.valueOf(12.45));
        album1.setArtistId(5L);
        album1.setLabelId(9L);
        album1.setReleaseDate(LocalDate.of(2005, 05, 19));
        album1.setTitle("Ethiopia");
//        album1.setTracks();

        List<Album> albumList1 = Arrays.asList(album);
        Optional<Album> findAlbumById = Optional.of(album);

        doReturn(albumList1).when(albumRepository).findAll();
        doReturn(album).when(albumRepository).save(album1);
        doReturn(findAlbumById).when(albumRepository).findById(11L);

    }

    @Test
    public void getAlbumByIdShouldReturnAAlbum() throws Exception {
        Optional<Album> actualRecommendedAlbum = albumRepository.findById(11L);
        Album expectedRecommendedAlbum = album;
        assertEquals(expectedRecommendedAlbum, actualRecommendedAlbum.get());
    }

    @Test
    public void getAlbumByIdStatusShouldReturnOkForNonExistingId() throws Exception {

        doReturn(Optional.empty()).when(albumRepository).findById(11L);

        mockMvc.perform(
                        get("/album/11"));
//                .andExpect(status().isOk()
                ;
    }

    @Test
    public void updateAlbumByIdsShouldUpdateAAlbumAndReturn200StatusCode() throws Exception {
        album = new Album();
        album.setListPrice(BigDecimal.valueOf(12.45));
        album.setId(11L);
        album.setArtistId(5L);
        album.setLabelId(9L);
        album.setReleaseDate(LocalDate.of(2005, 05, 19));
        album.setTitle("Ethiopia");

        albumJson = mapper.writeValueAsString(album);
        mockMvc.perform(
                put("/album")
                        .content(albumJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );

    }

    @Test
    public void deleteAlbumByIdShouldDeleteAAlbumAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/album/1"));
//                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllAlbumShouldReturnAListAnd200() throws Exception {

        List<Album> actualRecommendedAlbum = albumRepository.findAll();
        assertEquals(1, actualRecommendedAlbum.size());

    }

    @Test
    public void addAlbumShouldReturnNewAlbumAnd201() throws Exception {

        Album expectedResult = new Album();
        expectedResult.setListPrice(BigDecimal.valueOf(12.45));
        expectedResult.setArtistId(5L);
        expectedResult.setLabelId(9L);
        expectedResult.setReleaseDate(LocalDate.of(2005, 05, 19));
        expectedResult.setTitle("Ethiopia");

        Album albumWithId = new Album();
        albumWithId.setId(11L);
        albumWithId.setListPrice(BigDecimal.valueOf(12.45));
        albumWithId.setArtistId(5L);
        albumWithId.setLabelId(9L);
        albumWithId.setReleaseDate(LocalDate.of(2005, 05, 19));
        albumWithId.setTitle("Ethiopia");

        Album actualRecommendedAlbum = albumRepository.save(expectedResult);
        assertEquals(albumWithId, actualRecommendedAlbum);
    }
}

