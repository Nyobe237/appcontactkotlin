package com.contactsapp.app

sealed class Routes(val route: String){
    data object ContactList : Routes("contactList")
    data object AddContact : Routes("addContact")
    object ViewContact : Routes("view_contact/{contactId}") {
        fun createRoute(contactId: Int) = "view_contact/$contactId"
    }
}