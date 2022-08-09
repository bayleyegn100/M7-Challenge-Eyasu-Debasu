package com.mymusicstore.musicstorecatalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album")
public class Album  implements Serializable {
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "albumId")
    private Set<Track> tracks = new HashSet<>();
    private String title;
//    @Column(name = "artist_id")
    private Long artistId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
//    @Column(name = "label_id")
    private Long labelId;
//    @Column(name = "list_price")
    private BigDecimal listPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(tracks, album.tracks) && Objects.equals(title, album.title) && Objects.equals(artistId, album.artistId) && Objects.equals(releaseDate, album.releaseDate) && Objects.equals(labelId, album.labelId) && Objects.equals(listPrice, album.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tracks, title, artistId, releaseDate, labelId, listPrice);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", tracks=" + tracks +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releasedDate=" + releaseDate +
                ", labelId=" + labelId +
                ", listPrice=" + listPrice +
                '}';
    }
}