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
            0 -> CallHistoryFragment()
            1 -> SpeedDialFragment()
            2 -> ContactsFragment()
            else -> CallHistoryFragment()
        }
    }

    override fun getCount(): Int = 3
}