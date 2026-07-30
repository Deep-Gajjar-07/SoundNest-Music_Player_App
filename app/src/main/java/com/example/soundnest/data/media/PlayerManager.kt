package com.example.soundnest.data.media

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.soundnest.data.local.Song

// single object for create ExoPlayer instance, play, pause, resume song(audio).
object PlayerManager {

    private var player: ExoPlayer? = null
    private val _currentSong = mutableStateOf<Song?>(null)
    val currentSong: State<Song?> = _currentSong
    private val _isPlaying = mutableStateOf(false)
    val isPlaying: State<Boolean> = _isPlaying
    private var originalQueue: List<Song> = emptyList()
    private var currentIndex = -1
    private val _repeatMode = mutableStateOf(RepeatMode.OFF)
    val repeatMode: State<RepeatMode> = _repeatMode
    private val _isShuffleEnabled = mutableStateOf(false)
    val isShuffleEnabled: State<Boolean> = _isShuffleEnabled
    private var playbackQueue: List<Song> = emptyList()

    // function for creating player if it doesn't exist.
    fun initialize(context: Context) {
        if (player == null) {
            player = ExoPlayer.Builder(context.applicationContext).build()

            player?.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_ENDED) {
                        onSongCompleted()
                    }
                }
            })
        }
    }

    // to change the repeat mode.
    fun changeRepeatMode() {
        _repeatMode.value = when (_repeatMode.value) {
            RepeatMode.OFF -> RepeatMode.ALL
            RepeatMode.ALL -> RepeatMode.ONE
            RepeatMode.ONE -> RepeatMode.OFF
        }
    }

    // function to get all list of songs in PlayerManager queue for next/previous.
    fun setQueue(songs: List<Song>) {
        originalQueue = songs

        if (!_isShuffleEnabled.value) {
            playbackQueue = songs
        }
    }

    // function to play a song(audio) from its URI.
    fun playSong(song: Song) {
        _currentSong.value = song
        // getting index of current playing song(audio).
        currentIndex = playbackQueue.indexOf(song)

        // convert Uri to MediaItem.
        val mediaItem = MediaItem.fromUri(song.uri)

        // set the song(audio) to play.
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()

        _isPlaying.value = true
    }

    // this function called when the song is finished.
    fun onSongCompleted() {
        when (_repeatMode.value) {
            // play only current song on repeat mode.
            RepeatMode.ONE -> {
                playSong(playbackQueue[currentIndex])
            }

            // play all songs in playlist to repeat mode.
            RepeatMode.ALL -> {
                if (currentIndex < playbackQueue.lastIndex) {
                    currentIndex++
                } else {
                    currentIndex = 0
                }
                playSong(playbackQueue[currentIndex])
            }

            // play all playlist song 1 time only repeat off.
            RepeatMode.OFF -> {
                if (currentIndex < playbackQueue.lastIndex) {
                    currentIndex++
                    playSong(playbackQueue[currentIndex])
                } else {
                    stopSong()
                }
            }
        }
    }

    // function to play next audio(song).
    fun playNext() {
        // checks if queue is empty (no audio files in room).
        if (playbackQueue.isEmpty()) return

        // checks if current song plays is not last.
        if (currentIndex < playbackQueue.lastIndex) {
            currentIndex++
        } else {
            // starts again if last song in queue.
            currentIndex = 0
        }
        playSong(playbackQueue[currentIndex])
    }

    // function to play a previous song.
    fun playPrevious() {
        if (playbackQueue.isEmpty()) return

        if (currentIndex > 0) {
            currentIndex--
        } else {
            // play last song of queue if the current song is first in queue.
            currentIndex = playbackQueue.lastIndex
        }

        playSong(playbackQueue[currentIndex])

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

    fun toggleShuffle() {
        val currentSong = _currentSong.value
        if (_isShuffleEnabled.value) {
            // shuffle turned off
            playbackQueue = originalQueue
            currentSong?.let {
                currentIndex = playbackQueue.indexOf(it)
            }
        } else {
            // shuffle turned on
            playbackQueue = originalQueue.shuffled()
            currentSong?.let {
                currentIndex = playbackQueue.indexOf(it)
            }
        }

        _isShuffleEnabled.value = !_isShuffleEnabled.value
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