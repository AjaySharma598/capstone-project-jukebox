package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;
    Playlist playlist;


    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        playlistRepository = new PlaylistRepository();
        playlist = new Playlist();
    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        playlist = null;
    }

    @Test
    void createPlaylist() {

    }

    @Test
    void addSongsToPlaylist() {
    }

    @Test
    void getSongFromPlaylist() {
    }

    @Test
    void displayDetailsOfPlaylist() {

    }
}