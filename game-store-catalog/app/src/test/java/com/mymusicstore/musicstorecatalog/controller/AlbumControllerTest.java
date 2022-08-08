package com.mymusicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusicstore.musicstorecatalog.model.Album;
import com.mymusicstore.musicstorecatalog.serviceLayer.AlbumService;
import com.mymusicstore.musicstorecatalog.viewmodel.AlbumViewModel;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumControllerTest.class)
public class AlbumControllerTest {

    @MockBean
    private AlbumService albumService;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper= new ObjectMapper();

    @Before
    public void setUp()throws Exception{
//        setupAlbumServiceMock();
    }
//    private void setupAlbumServiceMock() {
//        AlbumViewModel albumWithId = new Album();
//        albumWithId.setId(1L);
//        albumWithId.getArtist().setId(45L);
//        albumWithId.getLabel().setId(10L);
//        albumWithId.setTitle("Greatest Hits");
//        albumWithId.setListPrice(new BigDecimal("14.99"));
//        albumWithId.setReleaseDate(LocalDate.of(1999, 05, 15));
//
//        AlbumViewModel albumWithOutId = new Album();
//        albumWithOutId.getArtist().setId(45L);
//        albumWithOutId.getLabel().setId(10L);
//        albumWithOutId.setTitle("Greatest Hits");
//        albumWithOutId.setListPrice(new BigDecimal("14.99"));
//        albumWithOutId.setReleaseDate(LocalDate.of(1999, 05, 15));
//
//        List<AlbumViewModel> AlbumList = Arrays.asList(albumWithId);
//
//        doReturn(AlbumList).when(albumService).findAllAlbums();
//        doReturn(albumWithId).when(albumService).saveAlbum(albumWithOutId);
//    }

//}
}