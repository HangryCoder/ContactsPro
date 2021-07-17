package com.hangrycoder.contactspro.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hangrycoder.contactspro.Contacts
import com.hangrycoder.contactspro.ContactsController
import com.hangrycoder.contactspro.ContactsUtil
import com.hangrycoder.contactspro.R
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactsFragment : Fragment() {

    private lateinit var contactsController: ContactsController

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

            val contacts = ContactsUtil.getAllContacts(requireContext()).also {
                it.sortBy { contact ->
                    contact.name
                }
            }

            contactsController = ContactsController()
            contactsController.contactsList = contacts
            contactsController.requestModelBuild()

            contacts_recycler_view.setController(contactsController)
        }
    }
}