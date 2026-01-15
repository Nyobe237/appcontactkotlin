package com.contactsapp.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.contactsapp.app.Routes
import com.contactsapp.components.CircularIcon
import com.contactsapp.components.fontFamily
import com.contactsapp.mvvm.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContact(
    navController: NavHostController,
    contactViewModel: ContactViewModel,
    contactId: Int
)
 {
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
                    IconButton(onClick = {navController.navigate(Routes.ContactList.route)}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Color(0xFF323232),
                            contentDescription = "Go back"
                        )
                    }
                },
                modifier = Modifier.shadow(6.dp),

                )
        }
    ) { innerPadding ->
         val contact = contactViewModel.getContactById(contactId)
         Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(200.dp) // Taille du bouton
                    .padding(top = 30.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Photo de profil",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Gray
                )
            }
            Row() {
                IconButton(
                    onClick = { contactViewModel.deleteContactById(contactId); navController.navigate(Routes.ContactList.route)},
                    modifier = Modifier
                        .size(60.dp) // Taille du bouton
                        .padding(top = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "supprimer le contact",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {navController.navigate(Routes.AddContact.route) },
                    modifier = Modifier
                        .size(60.dp) // Taille du bouton
                        .padding(top = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "editer le contact",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "${contact?.name} ${contact?.surname}",
                fontSize = 25.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    contact?.phone ?: "000 000 000",
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(90.dp))
                CircularIcon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Téléphone",
                    background = Color(0xFF08AE2D)
                )
                Spacer(modifier = Modifier.width(20.dp))
                CircularIcon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "Send message",
                    background = Color(0xFFE9AD13)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    contact?.email ?: "No email",
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium
                )

                CircularIcon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "Send mail",
                    background = Color(0xFF434040)
                )
            }
        }
    }
}