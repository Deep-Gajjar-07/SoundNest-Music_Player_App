package com.example.soundnest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    // for inserting only one song
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(song: Song)

    // for inserting multiple songs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongs(songList: List<Song>)

    // getting every imported songs to display in library screen.
    @Query("SELECT * FROM Song ORDER BY id DESC")
    fun getAllSongs(): Flow<List<Song>>

}