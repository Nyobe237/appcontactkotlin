package com.contactsapp.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
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
    var scrollState = rememberScrollState()
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()


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
                        if (name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()){
                        contactViewModel.addContact(
                            Contact(
                                id = System.currentTimeMillis().toInt(),
                                name = name,
                                surname = surname,
                                phone = phone,
                                email = email
                            )
                        )}
                        navController.popBackStack()

                    }, enabled = name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
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
                .fillMaxSize().verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(
                    horizontal = 15.dp,
                )
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(250.dp) // Taille du bouton
                    .padding(top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Photo de profile",
                    modifier = Modifier.fillMaxSize().size(25.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Row {
                    Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name",
                    tint = Color.Gray
                )
                        Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Name")
                }},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true



            )
            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = {
                    Row {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Photo de profile",
                        tint = Color.Gray
                    )
                        Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Surname")
                }},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true


            )
            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Photo de profile",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Phone number")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                    ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true


                )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Row {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Photo de profile",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Email")
                }},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email),
                isError = !isEmailValid && email.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                singleLine = true



            )

        }
    }
}
