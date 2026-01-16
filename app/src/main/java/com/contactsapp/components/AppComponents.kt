package com.contactsapp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.contactsapp.R
import com.contactsapp.app.Routes
import com.contactsapp.mvvm.Contact
import com.contactsapp.mvvm.ContactViewModel

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

    )
}


@Composable
fun ScrollContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.empty_box),
            contentDescription = "Empty box"
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "You have no contacts yet",
            color = Color(0xFF666666),
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun TextField(
    title: String,
    fieldLabel: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = fieldLabel,
                    color = Color(0xFF9E9E9E),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAFAFA)),
            shape = RoundedCornerShape(0.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun CircularIcon(imageVector: ImageVector, contentDescription: String, background: Color) {
    IconButton(onClick = {}) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(40.dp)
                .background(background)
                .clip(CircleShape)
                .padding(7.dp),
            tint = Color.White,
        )
    }
}

@Composable
fun ContactItem(contact: Contact, navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable(onClick = {
                navController.navigate(
                    Routes.ViewContact.createRoute(contact.id)
                )
            })
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Photo",
                modifier = Modifier.size(100.dp, 100.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(16.dp)
        ) {
            Text(
                text = "${contact.name} ${contact.surname}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Text(
                text = contact.phone,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                modifier = Modifier
                    .size(25.dp, 25.dp)
            )
        }
    }

}


