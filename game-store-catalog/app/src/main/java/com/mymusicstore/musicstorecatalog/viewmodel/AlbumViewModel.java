package com.mymusicstore.musicstorecatalog.viewmodel;

import com.mymusicstore.musicstorecatalog.model.Artist;
import com.mymusicstore.musicstorecatalog.model.Label;
import com.mymusicstore.musicstorecatalog.model.Track;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlbumViewModel {
    private Long id;
    private String title;
    private Artist artist;
    private LocalDate releaseDate;
    private Label label;
    private BigDecimal listPrice;
    private List<Track> tracks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumViewModel that = (AlbumViewModel) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(label, that.label) && Objects.equals(listPrice, that.listPrice) && Objects.equals(tracks, that.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, releaseDate, label, listPrice, tracks);
    }

    @Override
    public String toString() {
        return "AlbumViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", releaseDate=" + releaseDate +
                ", label=" + label +
                ", listPrice=" + listPrice +
                ", tracks=" + tracks +
                '}';
    }
}
