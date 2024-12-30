package com.zs.album_api.repository;

import com.zs.album_api.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Long> {
}
