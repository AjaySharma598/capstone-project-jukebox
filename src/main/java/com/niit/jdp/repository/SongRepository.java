/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                Song song1 = new Song(songId, songName, genre, artist);
                songList.add(song1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songList;
    }
}
