/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.model;

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

}
