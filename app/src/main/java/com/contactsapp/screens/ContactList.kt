package com.contactsapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.contactsapp.app.Routes
import com.contactsapp.components.ContactItem
import com.contactsapp.components.ScrollContent
import com.contactsapp.components.TopBar
import com.contactsapp.mvvm.ContactViewModel

@Composable
fun ContactList(
    navController: NavHostController,
    contactViewModel: ContactViewModel
) {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.AddContact.route) },
                shape = CircleShape,
                containerColor = Color(0xFF00B2FF),
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new contact"
                )
            }
        }
    ) { innerPadding ->

        if (contactViewModel.contacts.isEmpty()) {

            // EMPTY STATE
            ScrollContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

        } else {

            // LISTE DES CONTACTS
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(contactViewModel.contacts) { contact ->
                    ContactItem(contact, navController)
                }
            }
        }
    }
}

