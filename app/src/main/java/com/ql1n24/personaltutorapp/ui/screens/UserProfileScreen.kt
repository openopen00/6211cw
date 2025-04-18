package com.ql1n24.personaltutorapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ql1n24.personaltutorapp.model.User
import androidx.compose.ui.draw.clip


@Composable
fun UserProfileScreen(user: User, onSave: (User) -> Unit) {
    var name by remember { mutableStateOf(user.displayName) }
    var bio by remember { mutableStateOf(user.bio) }
    var imageUrl by remember { mutableStateOf(user.profileImageUrl ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("编辑个人资料", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("昵称") }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("简介") }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("头像图片链接") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val updatedUser = user.copy(
                displayName = name,
                bio = bio,
                profileImageUrl = imageUrl
            )
            onSave(updatedUser)
        }) {
            Text("保存")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (imageUrl.isNotBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}
