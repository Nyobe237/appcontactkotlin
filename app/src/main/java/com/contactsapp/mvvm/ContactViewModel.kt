package com.contactsapp.mvvm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }
    fun getContactById(id: Int): Contact? {
        return contacts.find { it.id == id }
    }
    fun deleteContactById(id: Int){
            _contacts.removeAll { it.id == id }
    }
}
