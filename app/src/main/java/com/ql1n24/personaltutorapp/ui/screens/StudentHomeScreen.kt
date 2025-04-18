package com.ql1n24.personaltutorapp.ui.screens


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Student 课程浏览") })
        }
    ) { innerPadding -> // ✅ 正确写法
        Text(
            text = "欢迎来到学生首页！",
            modifier = Modifier
                .padding(innerPadding) // ✅ 使用 Scaffold 提供的 padding
                .padding(16.dp)
        )
    }
}

