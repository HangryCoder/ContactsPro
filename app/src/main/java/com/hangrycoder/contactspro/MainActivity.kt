package com.hangrycoder.contactspro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ContactsUtil.getAllContacts(this).forEach { name ->
            Log.d("CONTACTS PRO", name)
        }
    }
}