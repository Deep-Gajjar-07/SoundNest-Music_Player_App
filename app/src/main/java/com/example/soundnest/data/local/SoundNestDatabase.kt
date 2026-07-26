package com.example.soundnest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
abstract class SoundNestDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    companion object {

        @Volatile
        private var INSTANCE: SoundNestDatabase? = null

        fun getDatabase(context: Context): SoundNestDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = SoundNestDatabase::class.java,
                    name = "soundnest_database",
                ).build()

                INSTANCE = instance
                instance

            }

        }

    }

}