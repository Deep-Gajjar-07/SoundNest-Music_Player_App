package com.example.soundnest.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["uri"], unique = true)])
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val artist: String,
    val album: String,
    val albumArtUri: String?,
    val uri: String,
    val duration: Long,
)
