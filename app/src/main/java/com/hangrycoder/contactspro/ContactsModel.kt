package com.hangrycoder.contactspro

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder

abstract class ContactsModel : EpoxyModelWithHolder<ContactsModel.ContactsHolder>() {

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    lateinit var profilePhoto: String

    @EpoxyAttribute
    lateinit var alphabetIndex: String

    override fun bind(holder: ContactsHolder) {
        super.bind(holder)
        with(holder) {
            contactName.text = name
            alphabetIndex.text = "A"
        }
    }

    inner class ContactsHolder : EpoxyHolder() {
        lateinit var alphabetIndex: AppCompatTextView
        lateinit var contactName: AppCompatTextView
        lateinit var contactProfilePhoto: AppCompatImageView

        override fun bindView(itemView: View) {
            with(itemView) {
                alphabetIndex = findViewById(R.id.alphabet_index)
                contactName = findViewById(R.id.contact_name)
                contactProfilePhoto = findViewById(R.id.contact_profile_photo)
            }
        }
    }

    override fun getDefaultLayout(): Int = R.layout.contact_item
}