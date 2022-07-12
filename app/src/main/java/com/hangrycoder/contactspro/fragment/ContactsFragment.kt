package com.hangrycoder.contactspro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hangrycoder.contactspro.*
import com.hangrycoder.contactspro.fastscrollrecyclerview.FastScrollAdapter
import com.hangrycoder.contactspro.fastscrollrecyclerview.FastScrollRecyclerViewItemDecoration
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import java.util.*


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

        val myDataset = ArrayList<String>()
        for (i in 0..25) {
            myDataset.add((65 + i).toChar().toString() + " Row item")
        }
        val mapIndex: HashMap<String, Int> = calculateIndexesForName(myDataset)

        view.contacts_recycler_view.setHasFixedSize(true)

        val adapter = FastScrollAdapter(myDataset, mapIndex)
        view.contacts_recycler_view.adapter = adapter
        val decoration = FastScrollRecyclerViewItemDecoration(requireContext())
        view.contacts_recycler_view.addItemDecoration(decoration)
        view.contacts_recycler_view.itemAnimator = DefaultItemAnimator()

        /*  contactsController = ContactsController()
          view.contacts_recycler_view.setController(contactsController)

          viewModel.fetchContacts()
          viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->
              contactsController.contactsList = contacts
              contactsController.requestModelBuild()
          }*/
    }

    private fun calculateIndexesForName(items: ArrayList<String>): HashMap<String, Int> {
        val mapIndex: HashMap<String, Int> = LinkedHashMap()
        for (i in 0 until items.size) {
            val name = items[i]
            var index = name.substring(0, 1)
            index = index.uppercase(Locale.getDefault())
            if (!mapIndex.containsKey(index)) {
                mapIndex[index] = i
            }
        }
        return mapIndex
    }
}