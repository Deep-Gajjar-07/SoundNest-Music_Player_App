package com.example.soundnest.ui.screens.libraryscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.soundnest.data.local.Song
import com.example.soundnest.data.local.SoundNestDatabase
import com.example.soundnest.data.repository.SongRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel
    (application: Application) : AndroidViewModel(application) {

    private val dao = SoundNestDatabase.getDatabase(application).songDao()
    private val repository = SongRepository(dao)

    val songs = repository.getAllSongs()

}