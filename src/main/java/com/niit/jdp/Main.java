package com.niit.jdp;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFoundException {
        SongRepository songRepository = new SongRepository();
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
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
        do {

        } while (choice != 9);
    }
}