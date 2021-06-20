package com.hangrycoder.contactspro

import com.airbnb.epoxy.EpoxyController

class ContactsController : EpoxyController() {

    var contactsList: List<Contacts> = emptyList()

    override fun buildModels() {
        contactsList.forEach {
            ContactsModel_()
                .id(it.id)
                .name(it.name)
                .profilePhoto(it.profilePhoto)
                .alphabetIndex(it.name.first().toString())
        }
    }
}