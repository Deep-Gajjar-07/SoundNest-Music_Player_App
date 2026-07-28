package com.example.soundnest.data.media

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.example.soundnest.data.local.Song

// object to read metadata from the selected audio files and
// return its data in Song entity.
object SongMetaDataReader {

    fun getSongMetadata(
        context: Context, uri: Uri
    ): Song {

        // for read metadata.
        val retriever = MediaMetadataRetriever()
        // for metadata of selected audio files only.
        retriever.setDataSource(context, uri)

        // reading song title
        val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            ?: "Title Not Found"

        // reading song artist
        val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            ?: "Unknown Artist"

        // reading song album
        val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            ?: "Unknow Album"

        // reading song duration
        val duration =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull()
                ?: 0L

        // reading embedded album artwork
        val artworkBytes = retriever.embeddedPicture
        val albumBitmap: Bitmap? =
            artworkBytes?.let {
                BitmapFactory.decodeByteArray(it, 0, it.size)
            }

        retriever.release()

        val albumArtUri: String? = albumBitmap?.let {
            ImageStorage.saveAlbumArt(context, it, uri.toString())
        }

        return Song(
            title = title,
            artist = artist,
            album = album,
            uri = uri.toString(),
            albumArtUri = albumArtUri,
            duration = duration,
        )

    }

}