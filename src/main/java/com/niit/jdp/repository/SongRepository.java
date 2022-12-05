/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    MusicPlayerService musicPlayerService;
    Connection connection;
    DatabaseService databaseService;

    public SongRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        connection = databaseService.getConnection();
        musicPlayerService = new MusicPlayerService();
    }

    Song song = new Song();

    /**
     * This method is used to display the list of song
     *
     * @return - list of song type
     */
    public List<Song> displayAllSong() {
        List<Song> songList = new ArrayList<>();
        String query = "Select * from `songdatabase`.`song`";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                String songPath = resultSet.getString("songPath");
                song = new Song(songId, songName, genre, artist, songPath);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songList;
    }

    /**
     * This method is used to get song by Name
     *
     * @param -songName
     * @return -Song
     */
    public Song getSongByName(String songName) throws SQLException, SongNotFoundException {
        if (songName == null) {
            throw new SongNotFoundException("song not found");
        }
        String query = "SELECT * FROM `songdatabase`.`song` WHERE `songName` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, songName);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            int songId = set.getInt("songId");
            String songName1 = set.getString("songName");
            String genre = set.getString("genre");
            String artist = set.getString("artist");
            String songPath = set.getString("songPath");
            song = new Song(songId, songName1, genre, artist, songPath);
        }
        //musicPlayerService.play(set.getString("songPath"));
        return song;
    }

    /**
     * This method is used to get song by artist name
     *
     * @param -artistName
     * @return-List of Song
     */
    public List<Song> getSongByArtistName(String artistName) throws SQLException, SongNotFoundException {
        List<Song> songList = new ArrayList<>();
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
                String songPath = resultSet.getString("songPath");
                song = new Song(songId, songName1, genre, artist, songPath);
                songList.add(song);
            }
        } else {
            throw new SongNotFoundException("Artist not found ");
        }
        return songList;
    }

    /**
     * This method is used to get song by genre name
     *
     * @param -genreName
     * @throws -SQLException
     * @return-List of song
     */
    public List<Song> getSongByGenre(String genreName) throws SQLException, SongNotFoundException {
        List<Song> songList = new ArrayList<>();
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
                String songPath = resultSet.getString("songPath");
                song = new Song(songId, songName1, genre, artist, songPath);
                songList.add(song);
            }
        } else {
            throw new SongNotFoundException("genre is not found in song list");
        }
        return songList;
    }

    /**
     * This method is used to get song by song id
     *
     * @param songId
     * @return -Song
     */
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
                String songPath = resultSet.getString("songPath");
                song = new Song(songId1, songName, genre, artist, songPath);
            }
            //  musicPlayerService.play(resultSet.getString("songPath"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return song;
    }
}
