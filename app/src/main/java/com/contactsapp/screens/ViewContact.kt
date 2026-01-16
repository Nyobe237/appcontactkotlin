package com.contactsapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.contactsapp.app.Routes
import com.contactsapp.components.CircularIcon
import com.contactsapp.components.fontFamily
import com.contactsapp.mvvm.Contact
import com.contactsapp.mvvm.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContact(
    navController: NavHostController,
    contactViewModel: ContactViewModel,
    contactId: Int
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    // 1. Gestion de l'état du contact pour plus de robustesse
    var contact by remember { mutableStateOf<Contact?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Charge les données du contact de manière asynchrone et une seule fois
    LaunchedEffect(key1 = contactId) {
        isLoading = true
        val result = contactViewModel.getContactById(contactId)
        contact = result
        isLoading = false
    }

    if (showDeleteDialog) {
        // 2. Remplacement de Popup par AlertDialog pour un look & feel standard
        DeleteConfirmationDialog(
            onConfirm = {
                contactViewModel.deleteContactById(contactId)
                showDeleteDialog = false
                // Navigation sécurisée après suppression
                navController.navigate(Routes.ContactList.route) {
                    popUpTo(navController.graph.startDestinationId)
                }
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Contact",
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                modifier = Modifier.shadow(4.dp),
                // Couleurs cohérentes avec le thème
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        // 3. Gestion des états de chargement et d'erreur
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    // Affiche une roue de chargement pendant la récupération des données
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                contact == null -> {
                    // Affiche un message si le contact n'a pas été trouvé
                    Text(
                        "Contact not found.",
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
                else -> {
                    // Affiche le contenu du contact une fois chargé
                    ContactDetails(
                        navController = navController,
                        contact = contact!!,
                        onDeleteClick = { showDeleteDialog = true }
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactDetails(
    navController: NavHostController,
    contact: Contact,
    onDeleteClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Permet le défilement sur les petits écrans
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile picture",
            modifier = Modifier.size(150.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete contact",
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(
                        Routes.EditContact.createRoute(contact.id)
                    )
                },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit contact",
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${contact.name} ${contact.surname}",
            fontSize = 25.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Section Téléphone
        DetailRow(
            text = contact.phone,
            primaryIcon = {
                CircularIcon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Call",
                    background = Color(0xFF08AE2D)
                )
            },
            secondaryIcon = {
                CircularIcon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "Send message",
                    background = Color(0xFFE9AD13)
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Section Email
        if (contact.email.isNotBlank()) {
            DetailRow(
                text = contact.email,
                primaryIcon = {
                    CircularIcon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Send email",
                        background = Color(0xFF434040)
                    )
                }
            )
        }
    }
}

@Composable
private fun DetailRow(
    text: String,
    primaryIcon: @Composable () -> Unit,
    secondaryIcon: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f) // Prend l'espace disponible
        )
        Spacer(modifier = Modifier.width(16.dp))
        primaryIcon()
        if (secondaryIcon != null) {
            Spacer(modifier = Modifier.width(16.dp))
            secondaryIcon()
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // Utilisation de Dialog pour une popup standard de Material 3
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Delete contact",
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        },
        text = {
            Text(
                "Are you sure you want to delete this contact? This action cannot be undone.",
                fontFamily = fontFamily
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes", color = Color.Red, fontWeight = FontWeight.Bold, fontFamily = fontFamily)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No", fontWeight = FontWeight.Bold, fontFamily = fontFamily)
            }
        }
    )
}
