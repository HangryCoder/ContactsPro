package com.hangrycoder.contactspro

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(context: Context) : ViewModel() {

    private val repository = ContactsRepository(context)

    private var _contactsList = MutableLiveData<List<Contacts>>()
    val contactsList: LiveData<List<Contacts>> by lazy {
        _contactsList
    }

    fun fetchContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val contacts = repository.getAllContacts().sortedBy { it.name.lowercase() }
            withContext(Dispatchers.Main) {
                _contactsList.value = contacts
            }
        }
    }
}