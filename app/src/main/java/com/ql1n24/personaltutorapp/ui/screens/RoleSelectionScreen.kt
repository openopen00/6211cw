package com.ql1n24.personaltutorapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ql1n24.personaltutorapp.Screen

@Composable
fun RoleSelectionScreen(navController: NavController) {
    val context = LocalContext.current

    var selectedRole by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("请选择你的身份", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    selectedRole = "Tutor"
                    Toast.makeText(context, "已选择：导师", Toast.LENGTH_SHORT).show()
                    // 可跳转主页或其他逻辑
                    navController.navigate(Screen.TutorHome.route) // 在导师按钮中

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("我是导师（Tutor）")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    selectedRole = "Student"
                    Toast.makeText(context, "已选择：学生", Toast.LENGTH_SHORT).show()
                    // 可跳转主页或其他逻辑
                    navController.navigate(Screen.StudentHome.route) // 在学生按钮中

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("我是学生（Student）")
            }
        }
    }
}
