package com.contactsapp.app

sealed class Routes(val route: String) {

    // Utilisons une convention de nommage standard (snake_case)
    data object ContactList : Routes("contact_list")
    data object AddContact : Routes("add_contact") // Route pour lancer la création
    data object ViewContact : Routes("view_contact/{contactId}") {
        fun createRoute(contactId: Int) = "view_contact/$contactId"
    }

    // --- CORRECTION MAJEURE ICI ---
    // La route doit définir le placeholder {contactId}
    data object EditContact : Routes("edit_contact/{contactId}") {
        fun createRoute(contactId: Int) = "edit_contact/$contactId"
    }
}
