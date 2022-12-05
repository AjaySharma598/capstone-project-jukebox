package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        int expectedValue = 7;
        List<Song> songs = songRepository.displayAllSong();
        int actualValue = songs.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void displayAllSongFailure() {
        int expectedValue = 6;
        List<Song> songs = songRepository.displayAllSong();
        int actualValue = songs.size();
        assertNotEquals(expectedValue, actualValue);

    }

    @Test
    void getSongByName() throws SQLException, SongNotFoundException {
        Song song1 = songRepository.getSongByName("iphone");
        String expectedResult = "iphone";
        String actualResult = song1.getSongName();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getSongByNameFailure() throws SQLException, SongNotFoundException {
        assertNotEquals("Aaja", songRepository.getSongByName("iphone").getSongName());
    }

    @Test
    void getSongByArtistName() throws SQLException, SongNotFoundException {
        //act
        List<Song> songs = songRepository.getSongByArtistName("maan");
        String expectedOutput = "Aaja";
        //arrange
        String actualOutput = songs.get(0).getSongName();
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getSongByArtistNameFailure() throws SQLException, SongNotFoundException {
        //act
        List<Song> songs = songRepository.getSongByArtistName("maan");
        String expectedOutput = "iphone";
        //arrange
        String actualOutput = songs.get(0).getSongName();
        //assert
        assertNotEquals(expectedOutput, actualOutput);
    }

    @Test
    void getSongByGenre() throws SQLException, SongNotFoundException {
        Song song1 = songRepository.getSongByGenre("sad").get(0);
        assertEquals(101, song1.getSongId());
    }

    @Test
    void getSongByGenreFailure() throws SQLException, SongNotFoundException {
        Song song1 = songRepository.getSongByGenre("sad").get(0);
        assertNotEquals(100, song1.getSongId());
    }

    @Test
    void getSongBySongId() {
        Song songBySongId = songRepository.getSongBySongId(104);
        assertEquals("kehte hai", songBySongId.getSongName());
    }

    @Test
    void getSongBySongIdFailure() {
        Song songBySongId = songRepository.getSongBySongId(104);
        assertNotEquals("iphone", songBySongId.getSongName());
    }
}