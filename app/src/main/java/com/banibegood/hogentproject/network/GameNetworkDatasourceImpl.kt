package com.banibegood.hogentproject.network

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.banibegood.hogentproject.helper.exception.NetworkOfflineException
import timber.log.Timber

class GameNetworkDatasourceImpl(
    private val gameApiService: GameApiService,
) : GameNetworkDatasource {


    private val _downloadedGames = MutableLiveData<List<ApiGame>>()
    override val downloadedGames: LiveData<List<ApiGame>>
        get() = _downloadedGames

    override suspend fun fetchGames() {
        try {
            Timber.d("FETCHING GAMES")
            val fetchedData = gameApiService.getData().await()
            Log.i(ContentValues.TAG, "DATA FETCHED_________________________________")
            for(gameItem in fetchedData){
                Log.i(ContentValues.TAG, gameItem.title)
            }

            print(fetchedData.size)
//            _downloadedGames.postValue(fetchedData)
        }
        catch (e: NetworkOfflineException) {
            Timber.tag("NETWORK").e("Internet is offline")
        }
    }
}