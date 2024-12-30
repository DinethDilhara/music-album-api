package com.zs.album_api.controller;

import com.zs.album_api.dto.songdto.*;
import com.zs.album_api.entity.*;
import com.zs.album_api.exception.*;
import com.zs.album_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/albums/{albumId}/songs")
    public List<SongResponseDto> getAllSongs(@PathVariable Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        List<Song> songs = album.getSongs() ;
        List<SongResponseDto> responseDto = new ArrayList<>();

        for (Song song : songs) {
            SongResponseDto dto = new SongResponseDto(
                    song.getId(),
                    song.getSongName(),
                    song.getDuration()
            );
            responseDto.add(dto);
        }
        return responseDto ;
    }

    @GetMapping("/albums/{albumId}/songs/{songId}")
    public SongResponseDto getSong(@PathVariable Long albumId, @PathVariable Long songId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song with ID " + songId + " not found"));

        return new SongResponseDto(
                song.getId(),
                song.getSongName(),
                song.getDuration()
        );
    }

    @PostMapping("/albums/{albumId}/songs")
    public void addSong(@PathVariable Long albumId, @RequestBody AddSongDto songDto) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = new Song();
        song.setSongName(songDto.getSongName());
        song.setDuration(songDto.getDuration());
        song.setAlbum(album);

        songRepository.save(song);
    }

    @PutMapping("/albums/{albumId}/songs/{songId}")
    public void updateSong(@PathVariable Long albumId, @PathVariable Long songId, @RequestBody AddSongDto songDto) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song with ID " + songId + " not found"));

        song.setSongName(songDto.getSongName());
        song.setDuration(songDto.getDuration());

        songRepository.save(song);
    }


    @DeleteMapping("/albums/{albumId}/songs/{songId}")
    public void deleteSong(@PathVariable Long albumId, @PathVariable Long songId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song with ID " + songId + " not found"));

        songRepository.delete(song);
    }
}
