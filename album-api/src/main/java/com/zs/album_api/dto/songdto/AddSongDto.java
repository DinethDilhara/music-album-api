package com.zs.album_api.dto.songdto;

public class AddSongDto {

    private String songName;
    private Integer duration;

    public AddSongDto() {
    }

    public AddSongDto(String songName, Integer duration) {
        this.songName = songName;
        this.duration = duration;
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
