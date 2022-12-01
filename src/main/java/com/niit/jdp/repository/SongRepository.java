/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFoundException;
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
    public Song getSongByName(String songName) throws SQLException, SongNotFoundException {
        if (songName != null) {
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
            } else {

                throw new SongNotFoundException("Song Not Found with this song name");
            }
        }
        return song;
    }

    public List<Song> getSongByArtistName(String artistName) throws SQLException, SongNotFoundException {
        if (artistName != null) {
            String query = "SELECT * FROM `songdatabase`.`song` WHERE `artist` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName1 = resultSet.getString("songName");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                song = new Song(songId, songName1, genre, artist);
                songList.add(song);
            }
        } else {
            throw new SongNotFoundException("Artist not found ");
        }
        return songList;
    }

    public List<Song> getSongByGenre(String genreName) throws SQLException, SongNotFoundException {
        if (genreName != null) {
            String query = "SELECT * FROM `songdatabase`.`song` WHERE `genre` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, genreName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName1 = resultSet.getString("songName");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                song = new Song(songId, songName1, genre, artist);
                songList.add(song);
            }
        } else {
            throw new SongNotFoundException("genre is not found in song list");
        }
        return songList;
    }

    public Song getSongBySongId(int songId) {
        String query = "SELECT * FROM `songdatabase`.`song` WHERE `songId`=? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int songId1 = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                song = new Song(songId1, songName, genre, artist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return song;
    }
}
