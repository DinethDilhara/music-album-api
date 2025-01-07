package com.zs.album_api.exception;

public class AlbumAlreadyExistsException extends RuntimeException {

    public AlbumAlreadyExistsException(String message) {
        super(message);
    }

}
