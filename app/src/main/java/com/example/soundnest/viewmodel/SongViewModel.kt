package com.example.soundnest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundnest.data.local.Song
import com.example.soundnest.data.local.SoundNestDatabase
import com.example.soundnest.data.repository.SongRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = SoundNestDatabase.Companion.getDatabase(application).songDao()
    private val repository = SongRepository(dao)

    // Channel is used to show snackbar message only once,
    // not re-shown everytime screen recompose.
    private val _snackbarMessage = Channel<String>()

    // receiveAsFlow() converts channel to flow to screen observe.
    val snackbarMessage = _snackbarMessage.receiveAsFlow()

    val totalSongs = repository.getSongCount()

    fun insertSong(song: Song) {
        viewModelScope.launch {
            repository.insertSong(song)
        }
    }

    fun insertSongs(songs: List<Song>) {
        viewModelScope.launch {
            repository.insertSongs(songs)
            _snackbarMessage.send("Song imported!")
        }
    }

    fun deleteSongs() {
        viewModelScope.launch {
            repository.deleteAllSongs()
        }
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch {
            repository.deleteSong(song)
            _snackbarMessage.send("Song removed from library.")
        }
    }

}