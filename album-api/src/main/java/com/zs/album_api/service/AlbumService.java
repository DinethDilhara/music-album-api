package com.zs.album_api.service;

import com.zs.album_api.dto.albumdto.AlbumResponseDto;
import com.zs.album_api.dto.albumdto.CreateAlbumDto;
import com.zs.album_api.dto.albumdto.UpdateAlbumDto;

import java.util.List;

public interface AlbumService {

    List<AlbumResponseDto> getAllAlbums() ;

    AlbumResponseDto getAlbumById(Long albumId) ;

    void createAlbum(CreateAlbumDto createAlbumDto) ;

    void updateAlbumById(Long albumId, UpdateAlbumDto updateAlbumDto) ;

    void deleteAlbumById(Long albumId) ;
}
