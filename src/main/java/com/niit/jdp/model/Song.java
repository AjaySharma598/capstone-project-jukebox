/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int songId;
    private String songName;
    private String genre;
    private String artistName;

    private String songPath;

    public Song() {
    }

    public Song(int songId, String songName, String genre, String artistName, String songPath) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.artistName = artistName;
        this.songPath = songPath;
    }


    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Override
    public String toString() {
        // return String.format("%-10s%-20s%-20s%-100s",  songId,  songName, genre, artistName,  songPath);
        return String.format("%-10s%-20s%-20s%-15s%-15s", songId, songName, genre, artistName, songPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getSongId() == song.getSongId() && Objects.equals(getSongName(), song.getSongName()) && Objects.equals(getGenre(), song.getGenre()) && Objects.equals(getArtistName(), song.getArtistName()) && Objects.equals(getSongPath(), song.getSongPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getSongName(), getGenre(), getArtistName(), getSongPath());
    }
}
