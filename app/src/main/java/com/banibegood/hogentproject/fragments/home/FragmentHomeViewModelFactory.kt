package com.banibegood.hogentproject.fragments.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.banibegood.hogentproject.repository.UlteamRepository

class FragmentHomeViewModelFactory (
    val app: Application,
    val ulteamRepository: UlteamRepository
    ) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentHomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentHomeViewModel(app,ulteamRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}