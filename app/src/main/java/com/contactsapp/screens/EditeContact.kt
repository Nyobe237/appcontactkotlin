package com.contactsapp.screens

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.contactsapp.components.fontFamily
import com.contactsapp.mvvm.Contact
import com.contactsapp.mvvm.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContact(
    navController: NavHostController,
    contactViewModel: ContactViewModel,
    contactId: Int? // Id du contact à éditer
) {
    // 1. Utilisation de LaunchedEffect pour charger les données une seule fois
    // Cela évite de recharger les données à chaque recomposition de l'écran.
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // On charge les données du contact uniquement si contactId n'est pas null.
    // LaunchedEffect s'exécute une seule fois quand l'écran est affiché (ou si contactId change).
    LaunchedEffect(key1 = contactId) {
        if (contactId != null) {
            val contact = contactViewModel.getContactById(contactId)
            if (contact != null) {
                name = contact.name
                surname = contact.surname
                phone = contact.phone
                email = contact.email
            }
        }
    }

    // 2. Validation améliorée
    // L'e-mail est valide s'il est vide OU s'il correspond au format.
    val isEmailValid = email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()
    // Le formulaire est valide si les champs obligatoires sont remplis et l'email est correct.
    val isFormValid = name.isNotBlank() && surname.isNotBlank() && phone.isNotBlank() && isEmailValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        // Le titre s'adapte en fonction du mode (édition ou création)
                        text = if (contactId != null) "Edit Contact" else "New Contact",
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // popBackStack est plus fiable pour revenir en arrière
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Color(0xFF323232),
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            contactViewModel.deleteContactById(contactId)
                            val contactToSave = Contact(
                                id = contactId ?: System.currentTimeMillis().toInt(), // Réutilise l'ID existant ou en crée un nouveau
                                name = name.trim(), // trim() pour enlever les espaces inutiles
                                surname = surname.trim(),
                                phone = phone.trim(),
                                email = email.trim()
                            )
                            contactViewModel.addContact(contactToSave)
                            navController.popBackStack() // Revenir à l'écran précédent après la sauvegarde
                        },
                        enabled = isFormValid // Le bouton est activé uniquement si le formulaire est valide
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            tint = if (isFormValid) Color(0xFF323232) else Color.LightGray, // Couleur plus visible pour l'état désactivé
                            contentDescription = "Save"
                        )
                    }
                },
                modifier = Modifier.shadow(6.dp),
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Utilisation directe de rememberScrollState()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile picture",
                modifier = Modifier.size(150.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(30.dp))

            // -- Champs de texte optimisés --
            // J'ai ajouté ImeAction.Next pour améliorer la navigation avec le clavier.
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Surname") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone number") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email (optional)") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                isError = !isEmailValid, // Affiche une erreur si le format est incorrect
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true
            )
        }
    }
}
