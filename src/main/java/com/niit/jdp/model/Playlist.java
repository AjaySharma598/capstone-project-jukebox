/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.model;

import java.util.Objects;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private int SongId;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, int songId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        SongId = songId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistName='" + playlistName + '\'' +
                ", SongId=" + SongId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist playlist)) return false;
        return getPlaylistId() == playlist.getPlaylistId() && getSongId() == playlist.getSongId() && Objects.equals(getPlaylistName(), playlist.getPlaylistName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistId(), getPlaylistName(), getSongId());
    }
}
