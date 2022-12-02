package com.banibegood.hogentproject.fragments.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.viewModelScope
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.repository.UlteamRepository
import com.banibegood.hogentproject.repository.UlteamRepositoryImpl
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance

class FragmentHomeViewModel(
    app : Application,
    ulteamRepository: UlteamRepository
) : AndroidViewModel(app) {

//    lateinit var repositoryGames : LiveData<List<Game>>
//    lateinit var repositoryGames : LiveData<List<Game>>
//    lateinit var repositoryGamesSuspend : MutableLiveData<List<Game>>

    private var _repositoryGames = MutableLiveData<List<Game>>()
            val repositoryGames : LiveData<List<Game>>
            get() = _repositoryGames

//    fun getItemAtIndex(index : Int): LiveData<Game>{
//        return _repositoryGames.observe()
//    }

    init {
     viewModelScope.launch {
         _repositoryGames.postValue(ulteamRepository.getRepositoryGamesSuspend())

     }

 }



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}