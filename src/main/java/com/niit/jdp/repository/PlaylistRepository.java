/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    Connection connection;
    DatabaseService databaseService;
    Song song = new Song();
    Playlist playlist = new Playlist();
    List<Playlist> songList = new ArrayList<Playlist>();

    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        connection = databaseService.getConnection();
    }

    public Playlist createPlaylist(String playlistName) {
        String query = "INSERT INTO `songdatabase`.`song`(`playlist_Name`) VALUES (?);";
        try {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlist;
    }


}
