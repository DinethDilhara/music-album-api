package com.zs.album_api.service.impl;

import com.zs.album_api.dto.albumdto.*;
import com.zs.album_api.entity.*;
import com.zs.album_api.exception.*;
import com.zs.album_api.repository.*;
import com.zs.album_api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository ;

    @Autowired
    private SongRepository songRepository ;

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

    public AlbumResponseDto getAlbumById(Long albumId) {

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

    public void createAlbum(CreateAlbumDto createAlbumDto) {

        Optional<Album> albumOne = albumRepository.findByAlbumName(createAlbumDto.getAlbumName());

        if (albumOne.isPresent()) {
            throw new AlbumAlreadyExistsException("Album with name " + createAlbumDto.getAlbumName() + " already exists");
        }

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

    public void updateAlbumById(Long albumId , UpdateAlbumDto updateAlbumDto) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        Optional<Album> albumOne = albumRepository.findByAlbumName(updateAlbumDto.getAlbumName());

        if (albumOne.isPresent()) {
            throw new AlbumAlreadyExistsException("Album with name " + updateAlbumDto.getAlbumName() + " already exists");
        }

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

    public void deleteAlbumById(Long albumId) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with ID " + albumId + " not found"));

        albumRepository.delete(album);
    }

}
