package com.example.soundnest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.soundnest.data.local.SoundNestDatabase
import com.example.soundnest.data.repository.SongRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class LibraryViewModel
    (application: Application) : AndroidViewModel(application) {

    private val dao = SoundNestDatabase.Companion.getDatabase(application).songDao()
    private val repository = SongRepository(dao)
    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    // update automatically if new value updates and search that word(query).
    val songs = _searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            repository.getAllSongs()
        } else {
            repository.searchSongs(query)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query.trim()
    }

}