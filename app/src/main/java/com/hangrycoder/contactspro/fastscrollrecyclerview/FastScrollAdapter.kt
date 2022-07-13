package com.hangrycoder.contactspro.fastscrollrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hangrycoder.contactspro.Contacts
import com.hangrycoder.contactspro.R

class FastScrollAdapter(
    private val contacts: List<Contacts>,
    override val mapIndex: HashMap<String, Int>
) : RecyclerView.Adapter<FastScrollAdapter.ViewHolder?>(), FastScrollRecyclerViewInterface {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder) {
            contactName.text = contact.name
            alphabetIndex.text = contact.name[0].toString()

            alphabetIndex.visibility =
                if (mapIndex[contact.name[0].toString()] == position) View.VISIBLE else View.INVISIBLE

            Glide.with(contactProfilePhoto.context)
                .load(contact.profilePhoto ?: R.drawable.ic_launcher_background)
                .circleCrop()
                .into(contactProfilePhoto)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var alphabetIndex: AppCompatTextView
        var contactName: AppCompatTextView
        var contactProfilePhoto: AppCompatImageView

        init {
            with(itemView) {
                alphabetIndex = findViewById(R.id.alphabet_index)
                contactName = findViewById(R.id.contact_name)
                contactProfilePhoto = findViewById(R.id.contact_profile_photo)
            }
        }
    }

    override fun getItemCount(): Int = contacts.size
}