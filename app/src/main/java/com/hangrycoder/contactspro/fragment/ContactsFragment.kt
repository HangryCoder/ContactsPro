package com.hangrycoder.contactspro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyRecyclerView
import com.hangrycoder.contactspro.Contacts
import com.hangrycoder.contactspro.ContactsController
import com.hangrycoder.contactspro.R
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactsFragment : Fragment() {

    private var contactsController = ContactsController()

    private val contactsList = listOf(
        Contacts(id = 1, name = "Krupa Bhat", phoneNumber = "8698073911", profilePhoto = ""),
        Contacts(id = 2, name = "Momma", phoneNumber = "9922108586", profilePhoto = ""),
        Contacts(id = 3, name = "Daddy", phoneNumber = "9822589850", profilePhoto = ""),
        Contacts(id = 4, name = "Shilpa Wadji", phoneNumber = "9822589864", profilePhoto = "")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)

        /*val recyclerView = view.findViewById<EpoxyRecyclerView>(R.id.contacts_recycler_view)
        contactsController.contactsList = contactsList
        recyclerView.setController(contactsController)
        recyclerView.requestModelBuild()*/

        //view.recycler_view_fast_scroller.setRecyclerView(recyclerView)

        return view
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         with(view) {
             contactsController.contactsList = contactsList
             contacts_recycler_view.setController(contactsController)
             contacts_recycler_view.requestModelBuild()
         }
     }
}