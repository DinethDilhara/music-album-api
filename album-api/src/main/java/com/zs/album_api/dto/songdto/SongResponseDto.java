package com.zs.album_api.dto.songdto;

public class SongResponseDto {

    private Long id;
    private String songName;
    private Integer duration;

    public SongResponseDto(Long id, String songName, Integer duration) {
        this.id = id;
        this.songName = songName;
        this.duration = duration;
    }

    public void SongResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
