package com.banibegood.hogentproject.activity

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.banibegood.hogentproject.*
import com.banibegood.hogentproject.adapter.HomeHeaderAdapter
import com.banibegood.hogentproject.databinding.ActivityMainBinding
import com.banibegood.hogentproject.model.Game


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)




        setContentView(binding.root)

        val pager = PageAdapter(supportFragmentManager)
        pager.adds(fragment_home(),fragment_cart(),fragment_friends())

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