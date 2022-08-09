package com.mymusicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusicstore.musicstorecatalog.model.Artist;
import com.mymusicstore.musicstorecatalog.repository.ArtistRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest  {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistRepository artistRepository;

    private Artist artist;
    private ObjectMapper mapper = new ObjectMapper();
    private String artistJson;
    private List<Artist> artistList = new ArrayList<>();

    @Before
    public void setup() {
        this.artistRepository = mock(ArtistRepository.class);
        artist = new Artist();
        artist.setName("Teddy Afro");
        artist.setId(4L);
        artist.setInstagram("@afro");
        artist.setTwitter("@teddyafro");

        Artist artist1 = new Artist();
        artist1.setName("Teddy Afro");
        artist1.setInstagram("@afro");
        artist1.setTwitter("@teddyafro");

        List<Artist> artistList1 = Arrays.asList(artist);
        Optional<Artist> findArtistById = Optional.of(artist);

        doReturn(artistList1).when(artistRepository).findAll();
        doReturn(artist).when(artistRepository).save(artist1);
        doReturn(findArtistById).when(artistRepository).findById(4L);

    }

    @Test
    public void getArtistByIdShouldReturnAArtist() throws Exception {
        Optional<Artist> actualRecommendedArtist = artistRepository.findById(4L);
        Artist expectedRecommendedArtist = artist;
        assertEquals(expectedRecommendedArtist, actualRecommendedArtist.get());

    }

    @Test
    public void getArtistByIdStatusShouldReturnOkForNonExistingId() throws Exception {

        doReturn(Optional.empty()).when(artistRepository).findById(22L);

        mockMvc.perform(
                        get("/artist/22"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void updateArtistByIdsShouldUpdateAArtistAndReturn200StatusCode() throws Exception {
        artist = new Artist();
        artist.setName("Teddy Afro");
        artist.setId(4L);
        artist.setInstagram("@afro");
        artist.setTwitter("@teddyafro");

        artistJson = mapper.writeValueAsString(artist);
        mockMvc.perform(
                put("/artists")
                        .content(artistJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );

    }

    @Test
    public void deleteArtistByIdShouldDeleteAArtistAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/artist/4")).andExpect(status().isNoContent());
    }

    @Test
    public void getAllArtistShouldReturnAListAnd200() throws Exception {

        List<Artist> actualRecommendedArtist = artistRepository.findAll();
        assertEquals(1, actualRecommendedArtist.size());

    }

    @Test
    public void addArtistShouldReturnNewArtistAnd201() throws Exception {

        Artist expectedResult = new Artist();
        expectedResult.setName("Teddy Afro");
        expectedResult.setInstagram("@afro");
        expectedResult.setTwitter("@teddyafro");

        Artist artistWithId = new Artist();
        artistWithId.setId(4L);
        artistWithId.setName("Teddy Afro");
        artistWithId.setInstagram("@afro");
        artistWithId.setTwitter("@teddyafro");

        Artist actualRecommendedArtist = artistRepository.save(expectedResult);
        assertEquals(artistWithId, actualRecommendedArtist);
    }

}