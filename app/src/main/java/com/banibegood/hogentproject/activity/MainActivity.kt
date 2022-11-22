package com.banibegood.hogentproject.activity

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.banibegood.hogentproject.*
import com.banibegood.hogentproject.database.game.Game
import com.banibegood.hogentproject.databinding.ActivityMainBinding
import com.banibegood.hogentproject.fragments.fragment_cart
import com.banibegood.hogentproject.fragments.fragment_friends
import com.banibegood.hogentproject.fragments.fragment_home
import com.banibegood.hogentproject.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


//TODO {
// change this to MVVm
// }
class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        val apiService = GameApiService(ConnectivityInterceptorImpl(this.applicationContext!!))
        val gameNetworkDatasource = GameNetworkDatasourceImpl(apiService)
        gameNetworkDatasource.downloadedGames.observe(this, Observer{
            print(it.toString())
        })
        GlobalScope.launch (Dispatchers.Main){
            gameNetworkDatasource.fetchGames()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)




        setContentView(binding.root)

        val pager = PageAdapter(supportFragmentManager)
        pager.adds(fragment_home(), fragment_cart(), fragment_friends())

        binding.viewPager.adapter = pager
        //off screen limit ?

        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.menuNavbar.menu.getItem(position).isChecked =true
                //scroll up
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.menuNavbar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> binding.viewPager.currentItem = 0
                R.id.nav_cart -> binding.viewPager.currentItem = 1
                R.id.nav_friends -> binding.viewPager.currentItem = 2
            }
            true
        }




    }




}