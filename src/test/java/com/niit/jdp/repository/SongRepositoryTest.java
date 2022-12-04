package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongRepositoryTest {

    SongRepository songRepository;
    Song song;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        songRepository = new SongRepository();
        song = new Song();

    }

    @AfterEach
    void tearDown() {
        songRepository = null;
        song = null;
    }

    @Test
    void displayAllSong() {
        assertEquals(7, songRepository.displayAllSong().size());
    }

    @Test
    void getSongByName() throws SQLException, SongNotFoundException {
        assertEquals("iphone", songRepository.getSongByName("iphone").getSongName());
    }

    @Test
    void getSongByArtistName() throws SQLException, SongNotFoundException {
        //act
        List<Song> expectedOutput = songRepository.getSongByArtistName("maan");
        //arrange
        Song song1 = songRepository.getSongByArtistName("maan").get(0);
        //assert
        assertEquals("maan", song1.getArtistName());
    }

    @Test
    void getSongByGenre() throws SQLException, SongNotFoundException {
        Song song1 = songRepository.getSongByGenre("sad").get(0);
        assertEquals(101, song1.getSongId());
    }

    @Test
    void getSongBySongId() {
        Song songBySongId = songRepository.getSongBySongId(104);
        assertEquals("kehte hai", songBySongId.getSongName());
    }
}