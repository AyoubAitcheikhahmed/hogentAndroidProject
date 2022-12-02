package com.banibegood.hogentproject.fragments.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banibegood.hogentproject.adapter.HomeHeaderAdapter
import com.banibegood.hogentproject.adapter.HomeHeaderListAdapter
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.databinding.FragmentHomeBinding
import com.banibegood.hogentproject.network.ConnectivityInterceptorImpl
import com.banibegood.hogentproject.network.GameApiService
import com.banibegood.hogentproject.network.GameNetworkDatasourceImpl
import com.banibegood.hogentproject.repository.UlteamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class HomeFragment() : Fragment(), KodeinAware {


    override val kodein by closestKodein()
    private val fragmentHomeViewModelFactory: FragmentHomeViewModelFactory by instance ()
    private val ulteamRepository: UlteamRepository by instance()



    private lateinit var viewModel: FragmentHomeViewModel

    private lateinit var home_popular_recycler : RecyclerView
    private lateinit var home_free_recycler : RecyclerView
    private lateinit var binding: FragmentHomeBinding
    private var layoutManagerFree : RecyclerView.LayoutManager? = null
    private var layoutManagerPopular : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<HomeHeaderListAdapter.ListViewHolder> ? = null
    private lateinit var listAdapter : HomeHeaderListAdapter

//    private var games : MutableList<Game> = null
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View {
        //setup
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, fragmentHomeViewModelFactory).get(FragmentHomeViewModel::class.java)

        listAdapter = HomeHeaderListAdapter()
        binding.popularBanner.adapter = listAdapter

        //TEST
        binding.lifecycleOwner = this
        layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        home_popular_recycler = binding.popularBanner
        home_popular_recycler.layoutManager = layoutManagerPopular
        home_popular_recycler.setHasFixedSize(true)
        home_popular_recycler.adapter = listAdapter
        //TEST2
//        binding.popularBanner.apply {
//            layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            adapter = listAdapter
//        }
    //

        viewModel.repositoryGames.observe(viewLifecycleOwner,Observer{
            Timber.tag("OBSERVING").i(it.get(1).toString())
            listAdapter.submitList(it)

        })
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel = ViewModelProvider(this, fragmentHomeViewModelFactory).get(FragmentHomeViewModel::class.java)
//        loadData()
////        setGames()
//
////

//
//    }
//
    private fun loadData(): LiveData<List<Game>> {
       return viewModel.repositoryGames

    }
//
    private fun bindView() {


    layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//    layoutManagerFree = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    //popular banner
    home_popular_recycler = binding.popularBanner
    home_popular_recycler.layoutManager = layoutManagerPopular
    home_popular_recycler.setHasFixedSize(true)
    home_popular_recycler.adapter = HomeHeaderListAdapter()

//    //free banner
//    home_free_recycler = binding.freeBanner
//    home_free_recycler.layoutManager = layoutManagerFree
//    home_free_recycler.setHasFixedSize(true)
//    home_free_recycler.adapter = HomeHeaderListAdapter()
}



}


