package com.banibegood.hogentproject.repository

import androidx.lifecycle.LiveData
import com.banibegood.hogentproject.database.game.Game

interface UlteamRepository {
    suspend fun getRepositoryGames() : LiveData<List<Game>>
}