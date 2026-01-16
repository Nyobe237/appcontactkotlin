package com.contactsapp.app

sealed class Routes(val route: String) {

    // Route pour la liste des contacts, rien à changer.
    data object ContactList : Routes("contact_list") // Convention : utiliser le snake_case pour les routes

    // Route pour ajouter un contact.
    data object AddContact : Routes("add_contact")

    // Route pour voir un contact spécifique.
    // Votre implémentation était déjà bonne, juste une petite uniformisation.
    data object ViewContact : Routes("view_contact/{contactId}") {
        fun createRoute(contactId: Int) = "view_contact/$contactId"
    }

    // --- ERREUR CORRIGÉE ICI ---
    // La route pour éditer un contact doit aussi définir un placeholder pour l'ID.
    data object EditContact : Routes("edit_contact/{contactId}") {
        fun createRoute(contactId: Int) = "edit_contact/$contactId"
    }
}
