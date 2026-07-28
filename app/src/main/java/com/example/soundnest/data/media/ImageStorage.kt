package com.example.soundnest.data.media

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

// object to save album images to device Internal Storage
object ImageStorage {

    fun saveAlbumArt(
        context: Context,
        bitmap: Bitmap,
        songUri: String
    ): String? {

        return try {

            // creating folder
            val folder = File(context.filesDir, "album_art")

            if (!folder.exists()) {
                folder.mkdirs()
            }

            // unique image file name
            val imageFile = File(folder, "${songUri.hashCode()}.jpg")

            FileOutputStream(imageFile).use {
                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    90, it
                )
            }

            imageFile.absolutePath

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

}
