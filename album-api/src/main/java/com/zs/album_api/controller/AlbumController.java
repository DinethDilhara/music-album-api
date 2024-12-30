 package com.zs.album_api.controller;

import com.zs.album_api.dto.albumdto.AlbumResponseDto;
import com.zs.album_api.dto.albumdto.CreateAlbumDto;
import com.zs.album_api.dto.albumdto.UpdateAlbumDto;
import com.zs.album_api.entity.*;
import com.zs.album_api.exception.AlbumNotFoundException;
import com.zs.album_api.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/albums")
    public List<AlbumResponseDto> getAllAlbums() {

        List<Album> albums = albumRepository.findAll();
        List<AlbumResponseDto> responseDto = new ArrayList<>();

        for (Album album : albums) {
            AlbumResponseDto dto = new AlbumResponseDto(
                    album.getId(),
                    album.getAlbumName(),
                    album.getArtistName(),
                    album.getReleaseDate(),
                    album.getGenre(),
                    album.getSongs()
            );
            responseDto.add(dto);
        }

        return responseDto;
    }

    @GetMapping("/albums/{albumId}")
    public AlbumResponseDto getAlbum(@PathVariable Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        return new AlbumResponseDto(
                album.getId(),
                album.getAlbumName(),
                album.getArtistName(),
                album.getReleaseDate(),
                album.getGenre(),
                album.getSongs()
        );
    }

    @PostMapping("/albums")
    public void createAlbum(@RequestBody CreateAlbumDto createAlbumDto) {

        Album album = new Album(
                createAlbumDto.getAlbumName(),
                createAlbumDto.getArtistName(),
                createAlbumDto.getGenre(),
                createAlbumDto.getReleaseDate(),
                createAlbumDto.getSongs()
        );

        albumRepository.save(album);

        List<Song> songs = album.getSongs();

        for (Song song : songs) {
            song.setAlbum(album);
        }

        songRepository.saveAll(songs);

    }

    @PutMapping("/albums/{albumId}")
    public void updateAlbumById(@PathVariable Long albumId ,@RequestBody UpdateAlbumDto updateAlbumDto) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        album.setAlbumName(updateAlbumDto.getAlbumName());
        album.setArtistName(updateAlbumDto.getArtistName());
        album.setGenre(updateAlbumDto.getGenre());
        album.setReleaseDate(updateAlbumDto.getReleaseDate());

        // delete songs in db with album id
        List<Song> songsToDelete = album.getSongs();

        for (Song song : songsToDelete){
            Long songId = song.getId() ;
            System.out.println("songId: " + songId);

            songRepository.delete(song);
        }

        album.setSongs(updateAlbumDto.getSongs());

        List<Song> songs = album.getSongs();

        for (Song song : songs) {
            song.setAlbum(album);
        }

        albumRepository.save(album);
        songRepository.saveAll(songs);
    }


    @DeleteMapping("/albums/{albumId}")
    public void deleteAlbumById(@PathVariable Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        albumRepository.delete(album);
    }

}
