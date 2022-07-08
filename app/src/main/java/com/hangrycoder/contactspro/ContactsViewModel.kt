package com.hangrycoder.contactspro

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel(context: Context) : ViewModel() {

    private val repository = ContactsRepository(context)

    private var _contactsList = MutableLiveData<List<Contacts>>()
    val contactsList: LiveData<List<Contacts>> by lazy {
        _contactsList
    }


    fun fetchContacts() {
        _contactsList.value = repository.getAllContacts().sortedBy { it.name.lowercase() }
    }
}