package com.banibegood.hogentproject.database.friend

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.database.game.GamesDao

@Database(entities = [Game::class,Friend::class], version = 2, exportSchema = false)
abstract class UlteamDatabase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao

    companion object {
        @Volatile private var instance: UlteamDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UlteamDatabase::class.java, "ulteam.db")
                .build()
    }
}