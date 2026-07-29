package com.example.soundnest.data.media

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.soundnest.data.local.Song

// single object for create ExoPlayer instance, play, pause, resume song(audio).
object PlayerManager {

    private var player: ExoPlayer? = null
    private val _currentSong = mutableStateOf<Song?>(null)
    val currentSong: State<Song?> = _currentSong
    private val _isPlaying = mutableStateOf(false)
    val isPlaying: State<Boolean> = _isPlaying
    private var songQueue: List<Song> = emptyList()
    private var currentIndex = -1

    // function for creating player if it doesn't exist.
    fun initialize(context: Context) {
        if (player == null) {
            player = ExoPlayer.Builder(context.applicationContext).build()
        }
    }

    // function to get all list of songs in PlayerManager queue for next/previous.
    fun setQueue(songs: List<Song>) {
        songQueue = songs
    }

    // function to play a song(audio) from its URI.
    fun playSong(song: Song) {
        _currentSong.value = song
        // getting index of current playing song(audio).
        currentIndex = songQueue.indexOf(song)

        // convert Uri to MediaItem.
        val mediaItem = MediaItem.fromUri(song.uri)

        // set the song(audio) to play.
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()

        _isPlaying.value = true
    }

    // function to play next audio(song).
    fun playNext() {
        // checks if queue is empty (not audio files in room).
        if (songQueue.isEmpty()) return

        // checks if current song plays is not last.
        if (currentIndex < songQueue.lastIndex) {
            currentIndex++
        } else {
            // starts again if last song in queue.
            currentIndex = 0
        }

        playSong(songQueue[currentIndex])


    }

    // function to play a previous song.
    fun playPrevious() {
        if (songQueue.isEmpty()) return

        if (currentIndex > 0) {
            currentIndex--
        } else {
            // play last song of queue if the current song is first in queue.
            currentIndex = songQueue.lastIndex
        }

        playSong(songQueue[currentIndex])

    }

    // function for pause the current song.
    fun pauseSong() {
        player?.pause()
        _isPlaying.value = false
    }

    // function for resume current song.
    fun resumeSong() {
        player?.play()
        _isPlaying.value = true
    }

    // function for stop playback completely.
    fun stopSong() {
        player?.stop()
    }

    // return player instance.
    fun getPlayer(): ExoPlayer? {
        return player
    }

    // return current position of audio files.
    fun getCurrentPosition(): Long {
        return player?.currentPosition ?: 0L
    }

    // get the total duration time of audio file.
    fun getDuration(): Long {
        val duration = player?.duration ?: 0L
        return if (duration > 0) duration else 0L
    }

    // for audio file time jump to play to specific time.
    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    // releasing player instance after app closes.
    fun releasePlayer() {
        player?.release()
        player = null
    }

}