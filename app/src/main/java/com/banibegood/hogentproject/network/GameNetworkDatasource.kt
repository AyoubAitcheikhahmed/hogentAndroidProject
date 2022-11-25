package com.banibegood.hogentproject.network

import androidx.lifecycle.LiveData
import com.banibegood.hogentproject.database.game.Game

interface GameNetworkDatasource {
    val downloadedGames: LiveData<List<ApiGame>>

    suspend fun fetchGames()
}