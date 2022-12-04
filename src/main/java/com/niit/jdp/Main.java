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
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        PlaylistRepository playlistRepository = new PlaylistRepository();
        int choice = 0;
        do {
            System.out.println("1 for song \n2 for playlist\n3 to Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    int choice1 = 0;

                    do {
                        System.out.println("1 to display\n2 to search by song name\n3 to artist name\n4 to genre name\n 0 to exit");
                        choice1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice1) {
                            case 1:

                                List<Song> songs = songRepository.displayAllSong();
                                for (Song song : songs) {
                                    System.out.println(song);
                                }
                                break;
                            case 2:
                                String name;
                                System.out.println("Enter the Song name : ");
                                name = scanner.nextLine();
                                Song songByName = songRepository.getSongByName(name);
                                System.out.println(songByName);
                                musicPlayerService.play(songByName.getSongPath());
                                break;
                            case 3:
                                System.out.println("Enter the Artist name : ");
                                String name1 = scanner.nextLine();
                                List<Song> songByArtistName = songRepository.getSongByArtistName(name1);
                                for (Song song1 : songByArtistName) {
                                    System.out.println(song1);
                                    musicPlayerService.play(song1.getSongPath());
                                }
                                break;
                            case 4:
                                String name3;
                                System.out.println("Enter the Genre : ");
                                name3 = scanner.nextLine();
                                List<Song> songByGenre = songRepository.getSongByGenre(name3);
                                for (Song songsGenre : songByGenre) {
                                    System.out.println(songsGenre);
                                    musicPlayerService.play(songsGenre.getSongPath());
                                }
                                break;
                            case 5:
                                int id;
                                System.out.println("Enter the Song Id : ");
                                id = scanner.nextInt();
                                Song songBySongId = songRepository.getSongBySongId(id);
                                System.out.println(songBySongId);
                                musicPlayerService.play(songBySongId.getSongPath());
                                break;
                            case 0:
                                System.exit(0);
                                break;
                            default:
                                System.err.println("Invalid choice");
                                break;
                        }
                    } while (choice1 > 0);
                    break;
                case 2:
                    int choice3 = 0;
                    do {
                        System.out.println("================playlist menu==================");
                        System.out.println("1 to create Playlist\n2 to add song to Playlist\n3 to Display Song list \n4 to Display Playlist ");
                        choice3 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice3) {

                            case 4:
                                List<Playlist> playlists = playlistRepository.displayDetailsOfPlaylist();
                                for (Playlist playlist1 : playlists) {
                                    System.out.println(playlist1);
                                }
                                break;

                            case 1:
                                System.out.println("Enter the name of playlist to be created :");
                                String playlistName = scanner.nextLine();
                                //scanner.nextLine();
                                Playlist playlist = playlistRepository.createPlaylist(playlistName);
                                // System.out.println("playlist.getPlaylistId() = " + playlist.getPlaylistId());
                                break;
                            case 2:
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
                                break;
                            case 3:

                                System.out.println("Enter the playlist id to get songs from: ");
                                int playlistIdToGetSongsFrom = scanner.nextInt();
                                List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(playlistIdToGetSongsFrom);
                                for (Song playlistSongs : songFromPlaylist) {
                                    System.out.println(playlistSongs);
                                    musicPlayerService.play(playlistSongs.getSongPath());
                                }

                                break;
                            case 0:
                                System.exit(0);
                                break;
                            default:
                                System.err.println("Invalid choice");
                                break;
                        }
                    } while (choice3 > 0);
                    break;
                case 0:
                    System.exit(0);
                    break;

                default:
                    System.err.println("Invalid choice");
                    break;

            }
        } while (choice > 0);

    }
//    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//        SongRepository songRepository = new SongRepository();
//        PlaylistRepository playlistRepository = new PlaylistRepository();
//        MusicPlayerService musicPlayerService = new MusicPlayerService();
//        System.out.println("==============Music Player=================");
//        int choice = 0;
//        do {
//            System.out.println("Enter the 1 to display all song\nEnter 2 to search song\nEnter 3 to create playlist\nEnter 0 to exit ");
//            choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    int choice1;
//                    List<Song> songs = songRepository.displayAllSong();
//                    for (Song song : songs) {
//                        System.out.println(song);
//                    }
//
//                        break;
//
//                case 2: {
//                    int choice2 = 0;
//                    do {
//                        System.out.println("1 to search by Name\n2 To search artist\n3 to search by genre\n 0 to exit");
//                        choice2 = scanner.nextInt();
//                        scanner.nextLine();
//                        switch (choice2) {
//                            case 1:
//                                String name;
//                                System.out.println("Enter the song name : ");
//                                name = scanner.nextLine();
//                                songRepository.getSongByName(name);
//                                break;
//
//
//                            case 2:
//                                System.out.println("Enter the artist name : ");
//                                String name1 = scanner.nextLine();
//                                System.out.println(songRepository.getSongByArtistName(name1));
//                                break;
//
//
//                            case 3:
//                                String name3;
//                                System.out.println("Enter the genre");
//                                name3 = scanner.nextLine();
//                                List<Song> songByGenre = songRepository.getSongByGenre(name3);
//                                for (Song songsGenre : songByGenre) {
//                                    System.out.println(songsGenre);
//                                }
//                                break;
//
//
//                            case 0:
//                                break;
//
//                            default:
//                                System.out.println("invalid");
//                                break;
//
//                        }
//
//                    } while (choice2 > 0);
//                }
//                break;
//                case 3: {
//                    int choice3 = 0;
//                    do {
//                        System.out.println("1 to create a playlist\n2 to add song in playlist\n3 to show display the playlist\n0 to Exit ");
//                        choice3 = scanner.nextInt();
//                        switch (choice3) {
//
//                            case 1:
//                                System.out.println("Enter the name of playlist to be created :");
//                                String playlistName = scanner.nextLine();
//                                scanner.nextLine();
//                                Playlist playlist = playlistRepository.createPlaylist(playlistName);
//                                System.out.println("playlist.getPlaylistId() = " + playlist.getPlaylistId());
//                                break;
//
//                            case 2:
//                                List<Playlist> playlists = playlistRepository.displayDetailsOfPlaylist();
//                                for (Playlist playlist1 : playlists) {
//                                    System.out.println(playlist1);
//                                }
//                                System.out.println("Enter the playlist id to add songs to: ");
//                                int playlistId = scanner.nextInt();
//                                scanner.nextLine();
//                                System.out.println("Enter the song ids to add to the playlist separated by comma: ");
//                                String songIds = scanner.nextLine();
//                                boolean songAdded = playlistRepository.addSongsToPlaylist(playlistId, songIds);
//                                if (songAdded) {
//                                    System.out.println("song added");
//                                } else {
//                                    System.out.println("Not added");
//                                }
//                                break;
//
//                            case 3:
//                                System.out.println("Enter the playlist id to get songs from: ");
//                                int playlistIdToGetSongsFrom = scanner.nextInt();
//                                List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(playlistIdToGetSongsFrom);
//                                for (Song playlistSongs : songFromPlaylist) {
//                                    System.out.println(playlistSongs);
//                                    musicPlayerService.play(playlistSongs.getSongPath());
//                                }
//                                break;
//                        }
//                    } while (choice3 > 0);
//                }
//            }
//        } while (choice > 0);
//    }
}