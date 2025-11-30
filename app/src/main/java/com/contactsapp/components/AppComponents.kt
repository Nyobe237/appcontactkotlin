package com.contactsapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.contactsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Contacts", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        modifier = Modifier.shadow(6.dp),
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search contact"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More actions"
                )
            }
        },
    )
}


@Composable
fun ScrollContent(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.empty_box), contentDescription = "Empty box")
    Spacer(modifier = Modifier.height(15.dp))
    Text(text = "You have no contacts yet", color = Color(0xFF666666), fontWeight = FontWeight.Bold)
}