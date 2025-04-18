package com.ql1n24.personaltutorapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ql1n24.personaltutorapp.model.Course
import com.ql1n24.personaltutorapp.model.Lesson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    course: Course,
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("课程内容：${course.title}") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // ✅ 占满剩余空间，才能滚动
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (course.lessons.isEmpty()) {
                item {
                    Text("⚠️ 暂无课节内容。")
                }
            } else {
                items(course.lessons) { lesson ->
                    LessonCard(
                        lesson = lesson,
                        onComplete = {
                            lesson.isCompleted = true
                            Toast.makeText(context, "已标记为完成", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }


}

@Composable
fun LessonCard(
    lesson: Lesson,
    onComplete: () -> Unit
) {
    var editedText by remember { mutableStateOf(lesson.content) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(lesson.title, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            // 图片（可选）
            lesson.imageUrl?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(vertical = 8.dp)
                )
            }

            // ✅ 可编辑学习材料输入框
            OutlinedTextField(
                value = editedText,
                onValueChange = { editedText = it },
                label = { Text("学习材料内容") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                lesson.content = editedText
            }) {
                Text("保存")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 完成状态
            Text(
                text = if (lesson.isCompleted) "✅ 已完成" else "📘 未完成",
                style = MaterialTheme.typography.bodySmall
            )

            if (!lesson.isCompleted) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onComplete) {
                    Text("标记为完成")
                }
            }
        }
    }
}

