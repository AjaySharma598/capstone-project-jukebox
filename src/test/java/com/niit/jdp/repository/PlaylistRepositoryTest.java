package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    void addSongsToPlaylistFailure() throws SQLException {
        assertEquals(false, playlistRepository.addSongsToPlaylist(0, null));
    }

    @Test
    void addSongsToPlaylist() throws SQLException {
        assertEquals(true, playlistRepository.addSongsToPlaylist(18, null));
    }

    @Test
    void getSongFromPlaylistFailure() {
        List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(15);
        String expectedResult = "iphone";
        String actualResult = songFromPlaylist.get(0).getSongName();
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void getSongFromPlaylist() {
        List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(15);
        String expectedResult = "Aaja";
        String actualResult = songFromPlaylist.get(0).getSongName();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void displayDetailsOfPlaylistFailure() {
        List<Playlist> playlistList = playlistRepository.displayDetailsOfPlaylist();
        int expectedResult = 16;
        int actualResult = playlistList.get(0).getPlaylistId();
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void displayDetailsOfPlaylist() {
        List<Playlist> playlistList = playlistRepository.displayDetailsOfPlaylist();
        int expectedResult = 15;
        int actualResult = playlistList.get(0).getPlaylistId();
        assertEquals(expectedResult, actualResult);
    }
}