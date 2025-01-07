package com.zs.album_api.service.impl;

import com.zs.album_api.dto.songdto.*;
import com.zs.album_api.entity.*;
import com.zs.album_api.exception.*;
import com.zs.album_api.repository.*;
import com.zs.album_api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    public List<SongResponseDto> getAllSongs(Long albumId) {

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

    public SongResponseDto getSong(Long albumId, Long songId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new AlbumNotFoundException("Song with ID " + songId + " not found"));

        return new SongResponseDto(
                song.getId(),
                song.getSongName(),
                song.getDuration()
        );
    }

    public void addSong(Long albumId, AddSongDto songDto) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Optional<Song> songOne = songRepository.findBySongName(songDto.getSongName());

        if (songOne.isPresent()) {
            throw new SongAlreadyExistsException("Song with name " + songDto.getSongName() + " already exists");
        }

        Song song = new Song();
        song.setSongName(songDto.getSongName());
        song.setDuration(songDto.getDuration());
        song.setAlbum(album);

        songRepository.save(song);

    }

    public void updateSong(Long albumId, Long songId, AddSongDto songDto) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new AlbumNotFoundException("Song with ID " + songId + " not found"));

        Optional<Song> songOne = songRepository.findBySongName(songDto.getSongName());

        if (songOne.isPresent()) {
            throw new SongAlreadyExistsException("Song with name " + songDto.getSongName() + " already exists");
        }

        song.setSongName(songDto.getSongName());
        song.setDuration(songDto.getDuration());

        songRepository.save(song);
    }

    public void deleteSong(Long albumId, Long songId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new AlbumNotFoundException("Song with ID " + songId + " not found"));

        songRepository.delete(song);
    }
}
