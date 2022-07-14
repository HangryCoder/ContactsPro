package com.hangrycoder.contactspro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Temporary hack
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val contactsPagerAdapter = ContactsPagerAdapter(supportFragmentManager)
        viewPager.adapter = contactsPagerAdapter

        setSupportActionBar(toolbar)

        smartTabLayout.setViewPager(viewPager)
    }
}