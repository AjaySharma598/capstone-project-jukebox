/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    List<Song> songList = new ArrayList<>();

    public List<Song> displayAllSong() {


        return songList;
    }
}
