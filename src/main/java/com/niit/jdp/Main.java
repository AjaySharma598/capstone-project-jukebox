package com.niit.jdp;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongNotFoundException;
import com.niit.jdp.repository.SongRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFoundException {
        SongRepository songRepository = new SongRepository();
//        List<Song> songs = songRepository.displayAllSong();
//        for (Song song : songs) {
//            System.out.println(song);
//        }
//
//        System.out.println("songRepository.getSongByName(\"Aaja\") = " + songRepository.getSongByName("Wangan"));
//
//        List<Song> songByArtist = songRepository.getSongByArtistName("Maan");
//        for (Song song : songByArtist) {
//            System.out.println(song);
//        }
        List<Song> sadSong = songRepository.getSongByGenre("sad");
        for (Song song : sadSong) {
            System.out.println(song);
        }

    }
}