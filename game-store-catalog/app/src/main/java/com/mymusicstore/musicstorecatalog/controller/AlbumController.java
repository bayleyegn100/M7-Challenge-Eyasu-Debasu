package com.mymusicstore.musicstorecatalog.controller;

import com.mymusicstore.musicstorecatalog.serviceLayer.AlbumService;
import com.mymusicstore.musicstorecatalog.viewmodel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumViewModel addAlbum(@RequestBody AlbumViewModel albumViewModel){
        return albumService.saveAlbum(albumViewModel);
    }
    @PutMapping("/album/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumViewModel getAlbumById(@PathVariable Long id){
        return albumService.findAlbumById(id);
    }
    @GetMapping("/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumViewModel> getAllAlbums(){
        return albumService.findAllAlbums();
    }
    @PutMapping("/album")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum( @RequestBody AlbumViewModel albumViewModel){
        albumService.updateAlbumById(albumViewModel);
    }
    @DeleteMapping("/album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumById(@PathVariable Long id){
        albumService.deleteById(id);
    }
}
