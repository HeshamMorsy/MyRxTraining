package com.inova.hesham.myrxtraining.entities;

public class Song{
    // properties
    private String album_title;
    private String artist_name;
    private String cover_art;

    // constructor
    public Song(){}

    public Song(String album_title, String artist_name, String cover_art) {
        this.album_title = album_title;
        this.artist_name = artist_name;
        this.cover_art = cover_art;
    }

    // getters and setters
    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getCover_art() {
        return cover_art;
    }

    public void setCover_art(String cover_art) {
        this.cover_art = cover_art;
    }
}