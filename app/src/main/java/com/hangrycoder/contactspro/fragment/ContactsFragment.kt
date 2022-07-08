package com.hangrycoder.contactspro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hangrycoder.contactspro.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactsFragment : Fragment() {

    private lateinit var contactsController: ContactsController

    private val viewModel by viewModels<ContactsViewModel> {
        ContactsViewModelFactory(requireContext())
    }

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

        contactsController = ContactsController()
        view.contacts_recycler_view.setController(contactsController)

        viewModel.fetchContacts()
        viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->
            contactsController.contactsList = contacts
            contactsController.requestModelBuild()
        }
    }
}