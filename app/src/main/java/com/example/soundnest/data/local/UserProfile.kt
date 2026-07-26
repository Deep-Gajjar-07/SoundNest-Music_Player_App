package com.example.soundnest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String
)
