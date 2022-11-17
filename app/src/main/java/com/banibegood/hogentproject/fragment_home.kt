package com.banibegood.hogentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banibegood.hogentproject.adapter.HomeHeaderAdapter
import com.banibegood.hogentproject.databinding.FragmentHomeBinding
import com.banibegood.hogentproject.model.Game


class fragment_home : Fragment() {

    private lateinit var home_popular_recycler : RecyclerView
    private lateinit var home_free_recycler : RecyclerView
    private lateinit var binding: FragmentHomeBinding
    private var layoutManagerFree : RecyclerView.LayoutManager? = null
    private var layoutManagerPopular : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<HomeHeaderAdapter.ViewHolder> ? = null

    private var games = ArrayList<Game>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGames()
        layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        layoutManagerFree = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

        //popular banner
        home_popular_recycler = binding.popularBanner
        home_popular_recycler.layoutManager = layoutManagerPopular
        home_popular_recycler.setHasFixedSize(true)
        home_popular_recycler.adapter = HomeHeaderAdapter(games)

        //free banner
        home_free_recycler = binding.freeBanner
        home_free_recycler.layoutManager = layoutManagerFree
        home_free_recycler.setHasFixedSize(true)
        home_free_recycler.adapter = HomeHeaderAdapter(games)

    }

    private fun setGames(){
        val overwatch = Game("Overwatch","FPS",
            " is a free-to-play, team-based action game set in the optimistic future, where every match is the ultimate 5v5 battlefield brawl.",
            R.drawable.overwatch,33)
        games.add(overwatch)
        games.add(overwatch)
    }


}


