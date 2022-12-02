package com.banibegood.hogentproject.database.game

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("SELECT * FROM game WHERE id = :key")
    suspend fun getGame(key: Long): Game

    @Query("SELECT * FROM game ORDER BY id")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM game ORDER BY id")
    suspend fun getAllGamesSuspend(): List<Game>




}