package com.contactsapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.contactsapp.R
import java.lang.reflect.Field

val fontFamily = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Contacts",
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        },
        modifier = Modifier.shadow(6.dp),
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color(0xFF323232),
                    contentDescription = "Search contact"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    tint = Color(0xFF323232),
                    contentDescription = "More actions"
                )
            }
        }
    )
}


@Composable
fun ScrollContent(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.empty_box), contentDescription = "Empty box")
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "You have no contacts yet",
        color = Color(0xFF666666),
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TextField(title: String, fieldLabel: String){
    var name by rememberSaveable { mutableStateOf("") }
    Column {
        Text(
            text = title,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            placeholder = {
                Text(
                    text = fieldLabel,
                    color = Color(0xFF9E9E9E),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            },
            modifier = Modifier.fillMaxWidth().background(Color(0xFFFAFAFA)),
            shape = RoundedCornerShape(0.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}