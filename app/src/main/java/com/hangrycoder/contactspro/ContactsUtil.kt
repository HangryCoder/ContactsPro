package com.hangrycoder.contactspro

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

object ContactsUtil {
     fun getAllContacts(context: Context): ArrayList<String> {
        val nameList = arrayListOf<String>()
        val cr = context.contentResolver
        val cur = cr.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null) as Cursor

        if (cur.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME))
                nameList.add(name)
                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id), null);
                    while (pCur!!.moveToNext()) {
                        val phoneNo = pCur.getString(pCur.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER))
                    }
                    pCur.close()
                }
            }
        }
        cur?.close()
        return nameList
    }
}