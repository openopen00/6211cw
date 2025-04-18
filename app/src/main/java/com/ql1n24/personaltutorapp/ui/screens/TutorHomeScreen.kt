package com.ql1n24.personaltutorapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ql1n24.personaltutorapp.Screen
import com.ql1n24.personaltutorapp.model.Course
import com.ql1n24.personaltutorapp.model.CourseViewModel
import com.ql1n24.personaltutorapp.model.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorHomeScreen(
    navController: NavController,
    courseViewModel: CourseViewModel,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    val courses = courseViewModel.courses
    val user = userViewModel.currentUser

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tutor 控制台") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // ✅ 顶部显示头像、昵称、简介
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (!user.profileImageUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = user.profileImageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                    }
                    Column {
                        Text(text = user.displayName, style = MaterialTheme.typography.titleMedium)
                        Text(text = user.bio, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            item {
                Button(
                    onClick = { navController.navigate(Screen.CreateCourse.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("➕ 创建新课程")
                }
            }

            item {
                Button(
                    onClick = { navController.navigate(Screen.Profile.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("✏️ 编辑个人资料")
                }
            }

            item {
                Text("📚 已创建课程", style = MaterialTheme.typography.titleMedium)
            }

            items(courses) { course ->
                CourseCard(
                    course = course,
                    courses = courses,
                    navController = navController,
                    onEdit = {
                        Toast.makeText(context, "编辑功能暂未实现", Toast.LENGTH_SHORT).show()
                    },
                    onDelete = {
                        courseViewModel.removeCourse(course)
                        Toast.makeText(context, "课程已删除", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseCard(
    course: Course,
    courses: List<Course>,
    navController: NavController,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = { showMenu.value = true }
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(course.title, style = MaterialTheme.typography.titleLarge)
            Text("分类：${course.subject}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(course.description, style = MaterialTheme.typography.bodySmall)

            Button(
                onClick = {
                    navController.navigate(Screen.CourseDetail.routeWithId(course.id))
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("查看内容")
            }
        }

        DropdownMenu(
            expanded = showMenu.value,
            onDismissRequest = { showMenu.value = false }
        ) {
            DropdownMenuItem(
                text = { Text("编辑课程") },
                onClick = {
                    showMenu.value = false
                    onEdit()
                }
            )
            DropdownMenuItem(
                text = { Text("删除课程") },
                onClick = {
                    showMenu.value = false
                    onDelete()
                }
            )
        }
    }
}
