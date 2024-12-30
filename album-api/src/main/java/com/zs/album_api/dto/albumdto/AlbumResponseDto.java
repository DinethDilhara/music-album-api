package com.zs.album_api.dto.albumdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zs.album_api.entity.Song;

import java.time.LocalDate;
import java.util.List;

public class AlbumResponseDto {

    private Long id;
    private String albumName;
    private String artistName;
    private LocalDate releaseDate;
    private String genre;
    private List<Song> songs;

    public AlbumResponseDto(){}

    public AlbumResponseDto(Long id, String albumName, String artistName,
                            LocalDate releaseDate, String genre, List<Song> songs) {
        this.id = id;
        this.albumName = albumName;
        this.artistName = artistName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
