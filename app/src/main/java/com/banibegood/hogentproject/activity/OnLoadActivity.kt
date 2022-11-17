package com.banibegood.hogentproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.banibegood.hogentproject.R

class OnLoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_on_load)
    }


    fun onGetStarted(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }


}