package com.niit.jdp;

import com.niit.jdp.exception.PlayListNotFoundException;
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFoundException, PlayListNotFoundException {
        Scanner scanner = new Scanner(System.in);
        SongRepository songRepository = new SongRepository();
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        PlaylistRepository playlistRepository = new PlaylistRepository();
        System.out.println("=====================================================================================");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Music Player<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("=====================================================================================");
        int choice = 0;
        do {
            System.out.println("1. Song Category \n2. Playlist Category\n0. to Exit!");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    int choice1 = 0;

                    do {
                        System.out.println("**************************************************************************************");
                        System.out.println("1. Display the Song list and Play the Song\n" +
                                "2. Search song  by Name and Play the Song\n" +
                                "3. Search the song by Artist Name and Play\n" +
                                "4. Search Song By Genre and Play\n" +
                                "5. Search Song by Song Id and Play\n0. Exit!");
                        System.out.println("***************************************************************************************");
                        choice1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice1) {
                            case 1:
                                System.out.println("**************************************************************************************************************");
                                System.out.format("%-10s%-20s%-20s%-11s", "songId", "songName", "genre", "artistName\n");
                                System.out.println("----------------------------------------------------------------------------------------------------------------");
                                List<Song> songs = songRepository.displayAllSong();
                                for (Song song : songs) {
                                    System.out.println(song);
                                }
                                System.out.println("");
                                System.out.println(">>>Enter the Song Id to play the song<<<");
                                int song = scanner.nextInt();
                                Song songBySongId1 = songRepository.getSongBySongId(song);

                                if (!(song == songBySongId1.getSongId())) {
                                    System.out.println("song id not found");
                                } else {
                                    System.out.println(songBySongId1);
                                    musicPlayerService.play(songBySongId1.getSongPath());
                                }
                                break;
                            case 2:
                                String name;
                                System.out.println("****************************");
                                System.out.println(" >>>Enter the Song name <<< ");
                                System.out.println("****************************");
                                name = scanner.nextLine();
                                Song songByName = songRepository.getSongByName(name);


                                if (!(name.equals(songByName.getSongName()))) {
                                    System.out.println("invalid song Name");
                                } else {
                                    System.out.println(songByName);
                                    musicPlayerService.play(songByName.getSongPath());
                                }
                                break;
                            case 3:
                                System.out.println("**********************************");
                                System.out.println("  >>>Enter the Artist name<<<  ");
                                System.out.println("***********************************");
                                String name1 = scanner.nextLine();
                                List<Song> songByArtistName = songRepository.getSongByArtistName(name1);
                                for (Song song1 : songByArtistName) {
                                    System.out.println(song1);
                                    musicPlayerService.play(song1.getSongPath());
                                }
                                break;
                            case 4:
                                String name3;
                                System.out.println("*****************************");
                                System.out.println("  >>>Enter the Genre<<<   ");
                                System.out.println("******************************");
                                name3 = scanner.nextLine();
                                List<Song> songByGenre = songRepository.getSongByGenre(name3);
                                for (Song songsGenre : songByGenre) {
                                    System.out.println(songsGenre);
                                    musicPlayerService.play(songsGenre.getSongPath());
                                }
                                break;
                            case 5:
                                int id;
                                System.out.println("******************************");
                                System.out.println("   >>>Enter the Song Id <<<   ");
                                System.out.println("******************************");
                                id = scanner.nextInt();
                                Song songBySongId = songRepository.getSongBySongId(id);
                                if (!(id == songBySongId.getSongId())) {
                                    System.out.println("Invalid song Id");
                                } else {
                                    System.out.println(songBySongId);
                                    musicPlayerService.play(songBySongId.getSongPath());
                                }
                                break;
                            case 0:
                                System.out.println("Exit Done!!");
                                break;
                            default:
                                System.out.println("******************");
                                System.err.println("Invalid Choice!!");
                                System.out.println("******************");
                                break;
                        }
                    } while (choice1 > 0);
                    break;
                case 2:
                    int choice3 = 0;
                    do {
                        System.out.println("================playlist menu==================");
                        System.out.println("1. To Create Playlist\n2. To Add Songs To  The Playlist\n" +
                                "3. To Display the song list and play the song \n4. To Display Playlist\n0. to Exit! ");
                        System.out.println("================================================");
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
                                System.out.println("**************************************************");
                                System.out.println("  >>>Enter the Name of Playlist to be Created<<<  ");
                                System.out.println("**************************************************");
                                String playlistName = scanner.nextLine();
                                //scanner.nextLine();
                                Playlist playlist = playlistRepository.createPlaylist(playlistName);
                                System.out.println("playlist Id = " + playlist.getPlaylistId());
                                System.out.println(">>>>>>>>>>>>>PlayList Created<<<<<<<<<<<<<<<<<<<");
                                break;
                            case 2:
                                System.out.format("%-10s%-9s", "playlistId", " playlistName\n");
                                playlistRepository.displayDetailsOfPlaylist().forEach(System.out::println);
                                System.out.println("**********************************************");
                                System.out.println("  >>>Enter the Playlist id to add Songs To<<<  ");
                                System.out.println("********************************************");
                                int playlistId = scanner.nextInt();
                                scanner.nextLine();
                                System.out.format("%-10s%-20s%-20s%-15s%-9s", "songId", "songName", "genre", "artistName", "songPath\n");
                                songRepository.displayAllSong().forEach(System.out::println);
                                System.out.println("*****************************************************************");
                                System.out.println(">>>Enter the Song Ids to Add to the Playlist separated by Comma<<<");
                                System.out.println("******************************************************************");
                                String songIds = scanner.nextLine();
                                boolean songAdded = playlistRepository.addSongsToPlaylist(playlistId, songIds);
                                if (songAdded) {
                                    System.out.println("-------------------");
                                    System.out.println("Song Added#");
                                    System.out.println("-------------------");
                                } else {
                                    System.out.println("-------------------");
                                    System.err.println("Not Added !");
                                    System.out.println("-------------------");
                                }
                                break;
                            case 3:
                                System.out.println("****************************************************************");
                                System.out.format("%-10s%-9s", "playlistId", " playlistName\n");
                                System.out.println("-----------------------------------------------------------------");
                                playlistRepository.displayDetailsOfPlaylist().forEach(System.out::println);
                                System.out.println("***********************************************************");
                                System.out.println("  >>>Enter the Playlist Id to get Songs From Playlist<<<  ");
                                System.out.println("***********************************************************");
                                int playlistIdToGetSongsFrom = scanner.nextInt();
                                List<Song> songFromPlaylist = playlistRepository.getSongFromPlaylist(playlistIdToGetSongsFrom);
                                for (Song playlistSongs : songFromPlaylist) {
                                    System.out.println(playlistSongs);
                                    musicPlayerService.play(playlistSongs.getSongPath());
                                }

                                break;
                            case 0:
                                System.out.println("Exit Done#");
                                break;
                            default:
                                System.out.println("-----------------");
                                System.err.println("Invalid choice!");
                                System.out.println("-----------------");
                                break;
                        }
                    } while (choice3 > 0);
                    break;
                case 0:
                    System.exit(0);
                    break;

                default:
                    System.out.println("------------------");
                    System.err.println("Invalid choice!");
                    System.out.println("------------------");
                    break;

            }
        } while (choice > 0);
    }
}