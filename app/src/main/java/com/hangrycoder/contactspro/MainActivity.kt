package com.hangrycoder.contactspro

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
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