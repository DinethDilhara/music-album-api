package com.zs.album_api.service;

import com.zs.album_api.dto.songdto.AddSongDto;
import com.zs.album_api.dto.songdto.SongResponseDto;

import java.util.List;

public interface SongService {

    List<SongResponseDto> getAllSongs(Long albumId);

    SongResponseDto getSong(Long albumId, Long songId);

    void addSong(Long albumId, AddSongDto songDto);

    void updateSong(Long albumId, Long songId, AddSongDto songDto);

    void deleteSong(Long albumId, Long songId) ;
}
