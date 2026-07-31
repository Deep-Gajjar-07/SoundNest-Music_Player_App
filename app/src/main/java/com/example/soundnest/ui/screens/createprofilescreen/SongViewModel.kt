package com.example.soundnest.ui.screens.createprofilescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundnest.data.local.Song
import com.example.soundnest.data.local.SoundNestDatabase
import com.example.soundnest.data.repository.SongRepository
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = SoundNestDatabase.getDatabase(application).songDao()
    private val repository = SongRepository(dao)

    val totalSongs = repository.getSongCount()

    fun insertSong(song: Song) {
        viewModelScope.launch {
            repository.insertSong(song)
        }
    }

    fun insertSongs(songs: List<Song>) {
        viewModelScope.launch {
            repository.insertSongs(songs)
        }
    }

    fun deleteSongs() {
        viewModelScope.launch {
            repository.deleteAllSongs()
        }
    }

}