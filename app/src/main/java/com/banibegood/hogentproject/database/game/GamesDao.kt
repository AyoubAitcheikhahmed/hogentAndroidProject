package com.banibegood.hogentproject.database.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banibegood.hogentproject.database.friend.Friend

@Dao
interface GamesDao {



    @Query("SELECT * FROM game WHERE id = :key")
    suspend fun getGame(key: Long): Game

    @Query("SELECT * FROM game ORDER BY id")
    suspend fun getAllGames(): List<Friend>


}