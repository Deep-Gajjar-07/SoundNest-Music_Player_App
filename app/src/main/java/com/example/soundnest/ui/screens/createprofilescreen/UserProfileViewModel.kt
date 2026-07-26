package com.example.soundnest.ui.screens.createprofilescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundnest.data.local.SoundNestDatabase
import com.example.soundnest.data.local.UserProfile
import com.example.soundnest.data.repository.UserProfileRepository
import kotlinx.coroutines.launch

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = SoundNestDatabase.getDatabase(application).userProfileDao()
    private val repository = UserProfileRepository(dao)

    val username = repository.getUser()

    fun insertUser(userProfile: UserProfile) {
        viewModelScope.launch {
            repository.insertUser(userProfile)
        }
    }

    fun updateUser(userProfile: UserProfile) {
        viewModelScope.launch {
            repository.updateUser(userProfile)
        }
    }

}