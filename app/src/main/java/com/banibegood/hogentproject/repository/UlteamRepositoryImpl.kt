package com.banibegood.hogentproject.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.database.game.GamesDao
import com.banibegood.hogentproject.network.ApiGame
import com.banibegood.hogentproject.network.GameNetworkDatasourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class UlteamRepositoryImpl(
    private val gamesDao : GamesDao,
    private val gameDatasource : GameNetworkDatasourceImpl
) : UlteamRepository {

    init {
        gameDatasource.downloadedGames.observeForever { newData ->
            //Save loaded data into Repo
            // newData is a List<Games>
            persistLoadedData(transformData(newData))
        }
    }

    private fun persistLoadedData(loadedData : List<Game>) {
        GlobalScope.launch(Dispatchers.IO) {
            loadedData.map {
                gamesDao.addGame(it)
            }
        }
    }

    private fun transformData(dataList : List<ApiGame>):List<Game>{
        val transformed = mutableListOf<Game>()
        dataList.map {  transformed.add(it.transform())}
        return transformed
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun cachDataFromNetwork() {
        if (fetchDataTimeCheck(ZonedDateTime.now().minusHours(6))){
            gameDatasource.fetchGames()
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchDataTimeCheck(lastFetchTime: ZonedDateTime): Boolean {
        return lastFetchTime.isAfter(ZonedDateTime.now().minusHours(3))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getRepositoryGames(): LiveData<List<Game>> {
       return withContext(Dispatchers.IO){
           cachDataFromNetwork()
           return@withContext gamesDao.getAllGames()
       }
    }
}