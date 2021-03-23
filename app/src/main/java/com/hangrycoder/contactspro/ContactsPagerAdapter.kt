package com.hangrycoder.contactspro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hangrycoder.contactspro.fragment.CallHistoryFragment
import com.hangrycoder.contactspro.fragment.ContactsFragment
import com.hangrycoder.contactspro.fragment.SpeedDialFragment

class ContactsPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SpeedDialFragment()
            1 -> CallHistoryFragment()
            2 -> ContactsFragment()
            else -> CallHistoryFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Speed Dial"
            1 -> "Call History"
            2 -> "Contacts"
            else -> "Contacts"
        }
    }

    override fun getCount(): Int = 3
}