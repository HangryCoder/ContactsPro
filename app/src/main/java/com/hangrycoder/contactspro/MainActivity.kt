package com.hangrycoder.contactspro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactsPagerAdapter = ContactsPagerAdapter(supportFragmentManager)
        viewPager.adapter = contactsPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        /* ContactsUtil.getAllContacts(this).forEach { name ->
             Log.d("CONTACTS PRO", name)
         }*/
    }
}