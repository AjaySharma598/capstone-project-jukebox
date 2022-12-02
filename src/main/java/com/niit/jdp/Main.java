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
        System.out.println("==============Music Player=================");
        System.out.println("Enter the 1 to display all song\nEnter 2 to search song\nEnter 3 to create playlist\nEnter 0 to exit ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                int choice1;
                List<Song> songs = songRepository.displayAllSong();
                for (Song song : songs) {
                    System.out.println(song);
                }
                System.out.println("Enter song Id to play the specific song");
                choice1 = scanner.nextInt();
                songRepository.getSongBySongId(choice1);
                break;
            }
            case 2: {

                System.out.println("1 to search by name\n2 To search artist\n3 to search by genre");
                int choice3 = scanner.nextInt();
                scanner.nextLine();
                switch (choice3) {
                    case 1: {
                        String name;
                        System.out.println("Enter the song name : ");
                        name = scanner.nextLine();
                        songRepository.getSongByName(name);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter the artist name : ");
                        String name1 = scanner.nextLine();
                        System.out.println(songRepository.getSongByArtistName(name1));
                        break;
                    }
                    case 3: {
                        String name3;
                        System.out.println("Enter the genre");
                        name3 = scanner.nextLine();
                        List<Song> songByGenre = songRepository.getSongByGenre(name3);
                        for (Song songsGenre : songByGenre) {
                            System.out.println(songsGenre);
                        }
                        break;

                    }
                    default: {
                        System.out.println("invalid");
                        break;
                    }
                }
            }
            case 3: {
                System.out.println("Enter the name of playlist to be created :");
                String playlistName = scanner.nextLine();
                scanner.nextLine();
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
                    musicPlayerService.play(playlistSongs.getSongPath());
                }

            }
        }
    }
}