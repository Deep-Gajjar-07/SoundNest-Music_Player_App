package com.example.soundnest.data.repository

import com.example.soundnest.data.local.UserProfile
import com.example.soundnest.data.local.UserProfileDao
import kotlinx.coroutines.flow.Flow

// Repository = acts as a bridge between Data layer (Dao) & Viewmodel.
class UserProfileRepository(private val userDao: UserProfileDao) {

    suspend fun insertUser(userProfile: UserProfile) = userDao.insertUser(userProfile)

    suspend fun updateUser(userProfile: UserProfile) = userDao.updateUser(userProfile)

    suspend fun deleteUser(userProfile: UserProfile) = userDao.deleteUser(userProfile)

    fun getUser(): Flow<UserProfile?> {
        return userDao.getUser()
    }

    suspend fun getUserOnce() = userDao.getUserOnce()

}