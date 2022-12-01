package com.niit.jdp;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFoundException {
        Scanner scanner = new Scanner(System.in);
        SongRepository songRepository = new SongRepository();
        PlaylistRepository playlistRepository = new PlaylistRepository();
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        int choice = 0;
        List<Song> songs = songRepository.displayAllSong();
        for (Song song : songs) {
            System.out.println(song);
        }


        System.out.println("Enter the name of playlist to be created :");
        String playlistName = scanner.nextLine();
        Playlist playlist = playlistRepository.createPlaylist(playlistName);
        System.out.println("playlist.getPlaylistId() = " + playlist.getPlaylistId());

        System.out.println("Enter the playlist id to add songs to: ");
        int playlistId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the song ids to add to the playlist separated by comma: ");
        String songIds = scanner.nextLine();
        boolean songAdded = playlistRepository.addSongsToPlaylist(playlistId, songIds);
        if (songAdded) {
            System.out.println("song added");
        } else {
            System.out.println("Not added");
        }

        System.out.println("Enter the playlist id to get songs from: ");
        int playlistIdToGetSongsFrom = scanner.nextInt();
        List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(playlistIdToGetSongsFrom);
        for (Song playlistSongs : songFromPlaylist) {
            System.out.println(playlistSongs);
        }
    }
}