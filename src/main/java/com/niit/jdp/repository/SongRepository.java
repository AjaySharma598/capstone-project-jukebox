/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    Connection connection;
    DatabaseService databaseService;

    public SongRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        connection = databaseService.getConnection();
    }

    List<Song> songList = new ArrayList<>();
    Song song = new Song();

    public List<Song> displayAllSong() {
        String query = "Select * from `songdatabase`.`song`";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                song = new Song(songId, songName, genre, artist);
                songList.add(song);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songList;
    }

    public Song getSongByName(String songName) throws SQLException {
        String query = "SELECT * FROM `songdatabase`.`song` WHERE `songName` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, songName);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            int songId = set.getInt("songId");
            String songName1 = set.getString("songName");
            String genre = set.getString("genre");
            String artist = set.getString("artist");
            song = new Song(songId, songName1, genre, artist);
        }
        return song;
    }

    public List<Song> getSongArtistByName(String artistName) throws SQLException {
        String query = "SELECT * FROM `songdatabase`.`song` WHERE `artist` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, artistName);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            int songId = set.getInt("songId");
            String songName1 = set.getString("songName");
            String genre = set.getString("genre");
            String artist = set.getString("artist");
            song = new Song(songId, songName1, genre, artist);
            songList.add(song);
        }

        return songList;
    }
}
