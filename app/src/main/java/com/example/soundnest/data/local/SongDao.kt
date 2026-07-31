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

    // getting total songs count in table.
    @Query("SELECT COUNT(*) FROM Song")
    fun getSongCount(): Flow<Int>

    // query for search songs by title & artist.
    @Query(
        "SELECT * FROM song WHERE LOWER(title) LIKE '%'|| LOWER(:query) ||'%' " +
                "OR LOWER(artist) LIKE '%'|| LOWER(:query) ||'%'"
    )
    fun searchSongs(query: String): Flow<List<Song>>

}