package com.banibegood.hogentproject.database.game

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.banibegood.hogentproject.database.friend.Friend

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("SELECT * FROM game WHERE id = :key")
    suspend fun getGame(key: Long): Game

    @Query("SELECT * FROM game ORDER BY id")
    suspend fun getAllGames(): LiveData<List<Game>>


}