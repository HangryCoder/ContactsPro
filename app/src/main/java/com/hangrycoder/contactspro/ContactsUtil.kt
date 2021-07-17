package com.hangrycoder.contactspro

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

object ContactsUtil {

    fun getAllContacts(context: Context): ArrayList<Contacts> {
        val contactsList = arrayListOf<Contacts>()
        val cr = context.contentResolver
        val cur = cr.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        ) as Cursor

        if (cur.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name = cur.getString(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id), null
                    );
                    while (pCur!!.moveToNext()) {
                        val phoneNo = pCur.getString(
                            pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                        )
                        contactsList.add(Contacts(id, name, "", phoneNo))
                    }
                    pCur.close()
                }
            }
        }
        cur.close()
        return contactsList
    }
}