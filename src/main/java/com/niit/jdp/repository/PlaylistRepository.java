/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    Connection connection;
    DatabaseService databaseService;
    Song song = new Song();
    Playlist playlist;

    MusicPlayerService musicPlayerService;

    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        connection = databaseService.getConnection();
        playlist = new Playlist();
        musicPlayerService = new MusicPlayerService();
    }

    public Playlist createPlaylist(String playlistName) throws SQLException {
        String query = "INSERT INTO `songdatabase`.`playlist`(`playlist_Name`) VALUES (?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, playlistName);
        int set = preparedStatement.executeUpdate();
        if (set > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                playlist.setPlaylistId(generatedKeys.getInt(1));
                playlist.setPlaylistName(playlistName);
                }
            }

        return playlist;
    }
    public boolean addSongsToPlaylist(int playlistId, String songIds) throws SQLException {
        String updateQuery = "update `songdatabase`.`playlist` set `songId` = ? where `playlist_Id` = ?;";
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, songIds);
        statement.setInt(2, playlistId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public List<Song> getSongFromPlaylist(int playlistId) {
        List<Song> songList = new ArrayList<>();
        String query = "SELECT * FROM `songdatabase`.`playlist` WHERE `playlist_Id`=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String songIds = resultSet.getString("songId");
                String[] songIdArray = songIds.split(",");
                for (String songId : songIdArray) {
                    song = new SongRepository().getSongBySongId(Integer.parseInt(songId));
                    songList.add(song);
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //musicPlayerService.play(song.getSongPath());
        return songList;
    }

    public List<Playlist> displayDetailsOfPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM `songdatabase`.`playlist`;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int playlist_id = resultSet.getInt("playlist_Id");
                String playlist_name = resultSet.getString("playlist_Name");
                playlist = new Playlist(playlist_id, playlist_name);
                playlists.add(playlist);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
}
