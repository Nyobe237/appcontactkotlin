package com.contactsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.contactsapp.R
import com.contactsapp.app.Routes
import com.contactsapp.components.TextField
import com.contactsapp.components.fontFamily
import com.contactsapp.mvvm.Contact
import com.contactsapp.mvvm.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(navController: NavHostController, contactViewModel: ContactViewModel) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Contact",
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Routes.ContactList.route)}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Color(0xFF323232),
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        contactViewModel.addContact(
                            Contact(
                                id = System.currentTimeMillis().toInt(),
                                name = name,
                                surname = surname,
                                phone = phone,
                                email = email
                            )
                        )
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            tint = Color(0xFF323232),
                            contentDescription = "Validation"
                        )
                    }
                },
                modifier = Modifier.shadow(6.dp),
            )
        }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = 15.dp,
                )
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(100.dp) // Taille du bouton
                    .padding(top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Photo de profile",
                    modifier = Modifier.fillMaxSize().size(10.dp),
                    tint = Color.Gray
                )
            }
            TextField(
                title = "Name",
                fieldLabel = "Enter name",
                value = name,
                onValueChange = { name = it }
            )

            TextField(
                title = "Surname",
                fieldLabel = "Enter surname",
                value = surname,
                onValueChange = { surname = it }
            )

            TextField(
                title = "Phone number",
                fieldLabel = "+237 ___ ___ ___",
                value = phone,
                onValueChange = { phone = it }
            )

            TextField(
                title = "Email",
                fieldLabel = "example@gmail.com",
                value = email,
                onValueChange = { email = it }
            )

        }
    }
}