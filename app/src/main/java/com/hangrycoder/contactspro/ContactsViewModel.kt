package com.hangrycoder.contactspro

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel(context: Context) : ViewModel() {

    private val repository = ContactsRepository(context)

    private var _contactsList = MutableLiveData<List<Contacts>>()
    val contactsList by lazy {
        _contactsList
    }

    fun fetchContacts() {
        _contactsList.value = repository.getAllContacts()
    }
}