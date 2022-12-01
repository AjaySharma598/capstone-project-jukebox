package com.niit.jdp;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SongRepository songRepository = new SongRepository();
        List<Song> songs = songRepository.displayAllSong();
        for (Song song3 : songs) {
            System.out.println(song3);
        }
    }
}