package com.hangrycoder.contactspro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel: ViewModel() {

    private var contactsList =  MutableLiveData<List<Contacts>>()

   // fun fetchContacts = ContactsUtil.getAllContacts()
}