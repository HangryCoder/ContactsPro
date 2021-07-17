package com.hangrycoder.contactspro

data class Contacts(
    val id: String,
    val name: String,
    val profilePhoto: String?,
    val phoneNumber: List<String>
)