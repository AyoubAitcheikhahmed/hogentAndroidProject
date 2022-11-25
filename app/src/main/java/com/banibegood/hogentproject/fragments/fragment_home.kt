package com.banibegood.hogentproject.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banibegood.hogentproject.R
import com.banibegood.hogentproject.adapter.HomeHeaderAdapter
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.databinding.FragmentHomeBinding
import com.banibegood.hogentproject.network.ConnectivityInterceptorImpl
import com.banibegood.hogentproject.network.GameApiService
import com.banibegood.hogentproject.network.GameNetworkDatasourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


class fragment_home : Fragment() {

    private lateinit var home_popular_recycler : RecyclerView
    private lateinit var home_free_recycler : RecyclerView
    private lateinit var binding: FragmentHomeBinding
    private var layoutManagerFree : RecyclerView.LayoutManager? = null
    private var layoutManagerPopular : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<HomeHeaderAdapter.ViewHolder> ? = null

//    private var games : MutableList<Game> = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setGames()
//        layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
//        layoutManagerFree = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
//
//        //popular banner
//        home_popular_recycler = binding.popularBanner
//        home_popular_recycler.layoutManager = layoutManagerPopular
//        home_popular_recycler.setHasFixedSize(true)
//        home_popular_recycler.adapter = HomeHeaderAdapter(games)
//
//        //free banner
//        home_free_recycler = binding.freeBanner
//        home_free_recycler.layoutManager = layoutManagerFree
//        home_free_recycler.setHasFixedSize(true)
//        home_free_recycler.adapter = HomeHeaderAdapter(games)
        Log.i(TAG, "Hello World1")
        val apiService = GameApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val gameNetworkDatasource = GameNetworkDatasourceImpl(apiService)
        gameNetworkDatasource.downloadedGames.observe(viewLifecycleOwner, Observer{
            print(it.toString())
        })
        GlobalScope.launch (Dispatchers.Main){
            Log.i(TAG, "FETCHING GAMES_________________________________GOLBALSCOP")
//            Timber.tag(TAG).i("FETCHING GAMES_________________________________GOLBALSCOP")
            gameNetworkDatasource.fetchGames()
        }

    }

    private fun setGames(){

        val overwatch = Game(
            "Activision Blizzard",
            "https://www.freetogame.com/g/540/thumbnail.jpg",
            "https://www.freetogame.com/overwatch-2",
            "Shooter",
            540,"Blizzard Entertainment",
            "PC (Windows)",
            "2022-10-04",
            "A hero-focused first-person team shooter from Blizzard Entertainment.",
            "https://www.freetogame.com/open/overwatch-2",
            "Overwatch 2",
        )
//        games.add(overwatch)
//        games.add(overwatch)
//        games.add(overwatch)
    }


}


