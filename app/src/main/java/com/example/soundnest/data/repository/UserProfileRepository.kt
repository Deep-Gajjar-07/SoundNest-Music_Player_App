package com.example.soundnest.data.repository

import com.example.soundnest.data.local.UserProfile
import com.example.soundnest.data.local.UserProfileDao
import kotlinx.coroutines.flow.Flow

// Repository = acts as a bridge between Data layer (Dao) & Viewmodel.
class UserProfileRepository(private val userDao: UserProfileDao) {

    suspend fun insertUser(userProfile: UserProfile) = userDao.insertUser(userProfile)

    suspend fun updateUser(userProfile: UserProfile) = userDao.updateUser(userProfile)

    fun getUser(): Flow<UserProfile?> {
        return userDao.getUser()
    }

}