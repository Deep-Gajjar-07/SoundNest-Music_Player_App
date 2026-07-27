package com.example.soundnest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userProfile: UserProfile)

    @Update
    suspend fun updateUser(userProfile: UserProfile)

    @Delete
    suspend fun deleteUser(userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile LIMIT 1")
    fun getUser(): Flow<UserProfile?>

    @Query("SELECT * FROM UserProfile LIMIT 1")
    // Used by the Splash screen to check if a UserProfile already exists
    suspend fun getUserOnce(): UserProfile?

}