package com.banibegood.hogentproject.database.friend

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.banibegood.hogentproject.database.game.Game

@Database(entities = [Game::class,Friend::class], version = 1, exportSchema = false)
abstract class UlteamDatabase : RoomDatabase() {

    abstract val friendsDao: FriendsDao

    companion object {

        @Volatile
        private var INSTANCE: UlteamDatabase? = null

        fun getInstance(context: Context): UlteamDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UlteamDatabase::class.java,
                        "ulteam.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}