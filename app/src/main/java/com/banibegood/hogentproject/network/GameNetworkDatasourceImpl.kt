package com.banibegood.hogentproject.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.banibegood.hogentproject.helper.exception.NetworkOfflineException
import timber.log.Timber

class GameNetworkDatasourceImpl(
    private val gameApiService: GameApiService
) : GameNetworkDatasource {

    private val _downloadedGames = MutableLiveData<List<ApiGame>>()
    override val downloadedGames: LiveData<List<ApiGame>>
        get() = _downloadedGames

    override suspend fun fetchGames() {
        try {
            Timber.d("FETCHING GAMES")
            val fetchedData = gameApiService.getData().await()

            _downloadedGames.postValue(fetchedData)
        }
        catch (e: NetworkOfflineException) {
            Timber.tag("NETWORK").e("Internet is offline")
        }
    }
}