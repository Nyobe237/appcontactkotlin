package com.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.contactsapp.app.Routes
import com.contactsapp.mvvm.ContactViewModel
import com.contactsapp.screens.AddContact
import com.contactsapp.screens.ContactList
import com.contactsapp.screens.EditContact
import com.contactsapp.screens.ViewContact
import com.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
                ScreenMain()
            }
        }
    }
}

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    val contactViewModel: ContactViewModel = viewModel()
    NavHost(
        navController = navController, startDestination = Routes.ContactList.route
    ) {
        composable(Routes.ContactList.route) {
            ContactList(navController = navController, contactViewModel)
        }
        composable(Routes.AddContact.route) {
            AddContact(navController = navController, contactViewModel)
        }
        composable(
            route = Routes.ViewContact.route,
            arguments = listOf(
                navArgument("contactId") {
                    type = NavType.IntType
                }
            )

        ) { backStackEntry ->

            val contactId = backStackEntry.arguments?.getInt("contactId") ?: return@composable

            ViewContact(
                navController = navController,
                contactViewModel = contactViewModel,
                contactId = contactId
            )
        }
        composable(
            route = Routes.EditContact.route,
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId")

            EditContact(
                navController = navController,
                contactViewModel = contactViewModel,
                contactId = contactId
            )
        }


    }
}