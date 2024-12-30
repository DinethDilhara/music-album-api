package com.zs.album_api.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
