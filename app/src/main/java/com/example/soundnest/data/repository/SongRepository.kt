package com.example.soundnest.data.repository

import com.example.soundnest.data.local.Song
import com.example.soundnest.data.local.SongDao
import kotlinx.coroutines.flow.Flow

class SongRepository(private val songDao: SongDao) {

    fun getAllSongs(): Flow<List<Song>> {
        return songDao.getAllSongs()
    }

    fun getSongCount(): Flow<Int> {
        return songDao.getSongCount()
    }

    fun searchSongs(query: String) = songDao.searchSongs(query)

    suspend fun insertSong(song: Song) = songDao.insertSong(song)

    suspend fun insertSongs(songs: List<Song>) = songDao.insertSongs(songs)

    suspend fun deleteAllSongs() = songDao.deleteAllSongs()

}