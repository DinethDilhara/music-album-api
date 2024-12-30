package com.zs.album_api.dto.albumdto;

import com.zs.album_api.entity.Song;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class UpdateAlbumDto {

    @NotNull
    private String albumName;

    @NotNull
    private String artistName;

    private String genre;

    @DateTimeFormat
    private LocalDate releaseDate;

    private List<Song> songs;

    public UpdateAlbumDto() {
    }

    public UpdateAlbumDto(String albumName, String artistName, String genre, LocalDate releaseDate, List<Song> songs) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
